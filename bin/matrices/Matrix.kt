package matrices

import java.util.ArrayList
import kotlin.math.pow

open class Matrix {
    var rows  =  ArrayList<Row>()

    private var status = Status.INITIAL

    constructor()

    constructor(arg: Matrix){
        arg.get().forEach { rows.add(Row(it)) }
    }

    constructor(vararg arg: Row){
        arg.forEach { add(it) }
    }

    constructor(vararg arg: Column){
        arg.forEach { add(it) }
    }

    constructor(arg: Set<Row>){
        arg.forEach { add(it) }
    }

    constructor(arg: List<Column>){
        arg.forEach { add(it) }
    }

    constructor(arg: Iterable<Iterable<Int>>){
        arg.forEach { add(Row(it)) }
    }

    constructor(arg: Collection<Collection<Double>>){
        arg.forEach { add(Row(it)) }
    }

    operator fun get(i: Int): Row = rows[i]

    operator fun get(i: Int, j: Int): Double = rows[i][j]

    operator fun set(i: Int, arg: Row){
        if (columnsNo() != arg.size())
            throw NotSuitableDimension()
        rows[i] = arg
        status = Status.INITIAL
    }

    operator fun set(i: Int, arg: Column){
        if (rowsNo() != arg.size())
            throw NotSuitableDimension()
        for(j in rows.indices)
            rows[j][i] = arg[j]
        status = Status.INITIAL
    }

    operator fun set(i: Int, j: Int, value: Double) {
        rows[i][j] = value
        status = Status.INITIAL
    }

    operator fun set(i: Int, j: Int, value: Int) {
        rows[i][j] = value
        status = Status.INITIAL
    }

    override operator fun equals(arg: Any?): Boolean{
        if (arg !is Matrix || this.dim() != arg.size())
            return false
        for (i in rows.indices)
            for (j in 0 until columnsNo())
                if (this[i][j] != arg[i][j])
                    return false
        return true
    }

    fun add(arg: Row): Matrix {
        if (rows.size != 0 && rows[0].size() != arg.size())
            throw NotSameDimension()
        rows.add(Row(arg))
        status = Status.INITIAL
        return this
    }

    fun add(arg: Column): Matrix {
        if (rows.size == 0) for (x in arg.available()) rows.add(Row())
        else if (arg.size() != rows.size) throw NotSameDimension()
        for (i in arg.available()) rows[i].add(arg[i])
        status = Status.INITIAL
        return this
    }

    fun getRow(i: Int): Row = rows[i]

    fun rowsNo() = rows.size

    fun columnsNo() = if (rows.isNotEmpty()) rows[0].size() else 0

    fun getColumn(i: Int): Column = Column().also{ for (row in rows) it.add(row[i]) }

    fun get(): List<Row> = rows

    fun available() = rows.indices

    fun tran(): Matrix {
        rows = ArrayList<Row>().also { for (i in rows[0].available()) it.add(Row(getColumn(i))) }
        status = Status.INITIAL
        return this
    }

    fun slice(): Matrix {
        rows.forEach { it.slice() }
        status = Status.INITIAL
        return this
    }

    fun slice(i: Int): Matrix {
        rows.forEach { it.slice(i) }
        status = Status.INITIAL
        return this
    }

    fun slice(i: Int, j: Int): Matrix {
        rows.forEach {it.slice(i, j) }
        status = Status.INITIAL
        return this
    }

    fun merge(arg: Matrix): Matrix {
        if (this.rows.size != arg.rows.size)
            throw NotSuitableDimension()
        for (i in rows.indices) rows[i].merge(arg.getRow(i))
        status = Status.INITIAL
        return this
    }

    fun multi(x: Double): Matrix {
        rows.forEach{ it.multi(x) }
        return this
    }

    fun multi(x: Int): Matrix {
        return multi(x.toDouble())
    }

    fun multi(arg: Matrix): Matrix {
        if (this.columnsNo() != arg.rowsNo())
            throw NotMultipliable()
         return  Matrix().also { m -> for (row in rows) m.add(Row().also { for (i in 0 until columnsNo()) it.add(row.multi(arg.getColumn(i))) }) }
    }

    fun add(arg: Matrix): Matrix {
        if (this.size() != arg.size())
            throw NotSameDimension()
        for (i in rows.indices) rows[i].add(arg.getRow(i))
        status = Status.INITIAL
        return this
    }

    fun deleteRow(i : Int): Matrix {
        rows.removeAt(i)
        status = Status.INITIAL
        return this
    }

    fun isSquare(): Boolean {
        if (size()=="0 X 0")
            throw EmptyException()
        return rows.size==rows[0].size()
    }

    fun isUpper(): Boolean {
        if (isSquare()) {
            for (i in rows.indices)
                for (j in i + 1 until rows[i].size())
                    if (rows[i][j] != 0.0)
                        return false
        }
        else return false
        return true
    }

    fun isLower(): Boolean {
        if (isSquare()) {
            for (i in rows.indices)
                for (j in 0 until i)
                    if (rows[i][j] != 0.0)
                        return false
        }
        else return false
        return true
    }

    fun isDiagonal(): Boolean = isUpper()&&isLower()

    fun isSymmetric(): Boolean {
        if (!isSquare())
            return false
        for (i in 0 until rows.size)
            for (j in i+1 until rows.size)
                if (rows[i][j] != rows[j][i])
                    return false
        return true
    }

    fun isAntiSymmetric(): Boolean {
        if (!isSquare())
            return false
        for (i in 0 until rows.size)
            for (j in i+1 until rows.size)
                if (rows[i][j] != -rows[j][i])
                    return false
        return true
    }

    tailrec fun det(): Double{
        if (this.isSquare() && this.size() != "2 X 2") {
            val x = Matrix(this).slice()
            var xOut = 0.0
            for (i in rows.indices)
                xOut += (-1.0).pow(i.toDouble()) * rows[i][0] * Matrix(x).deleteRow(i).det()
            return xOut
        }
        else if (this.isSquare() && this.size() == "2 X 2")
            return (rows[0][0] * rows[1][1]) - (rows[0][1] * rows[1][0])
        else throw NotASquareMatrix()
    }

    fun round(): Matrix{
        rows.forEach { it.round() }
        return this
    }

    fun echlon(): Matrix {
        if (status > Status.INITIAL) return this
        if (size() == "0 X 0") throw EmptyException()
        range()
        for (i in 1 until rows.size) {
            rows[i-1].normalize()
            for (j in i until rows.size)
                rows[j].reduce(rows[i - 1])
        }
        rows[rows.lastIndex].normalize()
        status = Status.ECHLON
        return this
    }

    fun reducedEchlon(): Matrix {
        if (status == Status.REDUCEDECHLON)
            return this
        if (status == Status.INITIAL)
            echlon();
        for (i in rows.lastIndex downTo 1)
            for (j in i-1 downTo 0)
                rows[j].reduceInv(rows[i])
        status = Status.REDUCEDECHLON
        return this
    }

    fun inverse(): Matrix {
        if (! isSquare())
            throw NotASquareMatrix()
        var x = this.getCopy().merge(identity(this.rowsNo()))
        x.echlon()
        if (x.rank(this.rowsNo()) != this.rowsNo())
            throw NotInvertable()
        x.reducedEchlon()
        x = x.slice(this.rowsNo())
        return x
    }

    fun rank(n: Int = this.columnsNo()): Int {
        var x = this.getCopy()
        if (x.status == Status.INITIAL)
            x.echlon()
        return rows.count { it.cells.indexOfFirst { e -> e != 0.0 }.let { e -> e != -1 && e < n }}
    }

    fun nullity() = columnsNo() - rank()

    fun leadingVars(): ArrayList<Int> {
        var outX = ArrayList<Int>()
        var x = this.getCopy().echlon()
        x.rows.forEach { row -> row.cells.indexOfFirst { e -> e != 0.0 }.let { if (it != -1) outX.add(it+1) } }
        return outX
    }

    fun freeVars(): ArrayList<Int> {
        var outX = ArrayList<Int>()
        var x = this.getCopy().echlon()
        var i = 0
        x.rows.forEach { row -> row.cells.indexOfFirst { e -> e != 0.0 }
            .let { if (it != i) outX.addAll(i+1 until if (it !=-1) it+1 else columnsNo()+1)
            i = if (it !=-1) it+1 else columnsNo()+1} }
        return outX
    }

    fun tr(): Double {
        var x = 0.0
        for(i in rows.indices)
            x += rows[i][i]
        return x
    }

    fun size() : String = if (rows.isNotEmpty()) "" + rows.size + " X " + rows[0].size() else "0 X 0"

    fun dim() : String = size()

    fun getCopy() : Matrix = Matrix(this)

    override fun toString():String {
        var str = "{"
        rows.forEach { str += it.toString() + '\n'  }
        return str.dropLast(1) + " }"
    }

    private fun range(): Matrix {
        for (i in rows.indices)
            for (j in i+1 until rowsNo())
                if (rows[j] > rows[i])
                    rows[i] = rows[j].also { rows[j] = rows[i]}
        return this
    }
}

enum class Status{
    INITIAL,
    ECHLON,
    REDUCEDECHLON
}

fun identity(n: Int): Matrix {
    return Matrix().also {m -> for (i in 1..n) m.add(Row().also { for (j in 1..n) if (i == j) it.add(1.0) else it.add(0.0) })}
    }
fun zeroMatrix(n: Int): Matrix {
    return Matrix().also {m -> for (i in 1..n) m.add(Row().also { for (i in 1..n) it.add(0.0) }) }
}
