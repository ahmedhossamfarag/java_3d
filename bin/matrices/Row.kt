package matrices

import java.util.ArrayList
import kotlin.math.pow
import kotlin.math.roundToInt

open class Row {
    var cells = ArrayList<Double>()

    constructor()

    constructor(vararg arg: Double) {
        for (x in arg) cells.add(x)
    }

    constructor(vararg arg : Int){
        for (x in  arg) cells.add(x.toDouble())
    }

    constructor(arg: Collection<Double>){
        for (x in  arg) cells.add(x)
    }

    constructor(arg: Iterable<Int>){
        for (x in  arg) cells.add(x.toDouble())
    }

    constructor(arg: Row) {
        for (i in arg.available()) this.add(arg[i])
    }

    constructor(arg: Column){
        for (i in arg.available()) this.add(arg[i])
    }

    operator fun get(i: Int): Double = cells[i]

    operator fun set(i: Int, value: Double) {
        cells[i] = value
    }

    operator fun set(i: Int, value: Int) {
        cells[i] = value.toDouble()
    }

    operator fun compareTo(r: Row): Int {
        val i = r.firstNonZero()
        val j = firstNonZero()
        if (i==-1&&j!=-1) return 1
        if (i!=-1&&j==-1) return -1
        return i-j
    }

    fun add(x : Double): Row {
        cells.add(x)
        return this
    }

    fun add(x : Int): Row {
        return add(x.toDouble())
    }

    fun add(arg: Row): Row {
        if (this.size() != arg.size())
            throw NotSameDimension()
        cells.withIndex().onEach { cells[it.index] = cells[it.index] + arg[it.index] }
        return this
    }

    fun merge(arg: Row): Row {
        cells.addAll(arg.cells)
        return this
    }

    fun size(): Int = cells.size

    fun slice(): Row {
        if (size()==0) throw EmptyException()
        cells.removeAt(0)
        return this
    }

    fun slice(i: Int): Row {
        cells = cells.slice(i..cells.lastIndex) as ArrayList<Double>
        return this
    }

    fun slice(i: Int, j: Int): Row {
        cells = cells.slice(i..j) as ArrayList<Double>
        return this
    }

    fun available() = cells.indices

    fun allZeros() = cells.all { it == 0.0 }

    fun multi(x: Double): Row {
        if (size()==0) throw EmptyException()
        cells.withIndex().onEach { cells[it.index] = it.value * x }
        return this
    }

    fun multi(x: Int): Row{
        return multi(x.toDouble())
    }

    fun multi(x: Column) : Double{
        if (this.size() != x.size() || size()==0)
            throw NotMultipliable()
        var xOut : Double = 0.0
        for (i in this.available())
            xOut += (cells[i] * x[i])
        return xOut
    }

    fun tran() : Column = Column(this)

    fun normalize(): Row {
        cells.find { e -> e != 0.0 }
            .let { x -> x!!.run {  cells.withIndex().onEach { cells[it.index] = it.value / x!! } }}
        return this
    }

    fun firstNonZero() = cells.indexOfFirst { it != 0.0 }

    fun getCopy(): Row = Row(this)

    fun round(): Row {
        cells.withIndex().onEach { cells[it.index] = round(it.value) }
        return this
    }

    override fun toString():String {
        return cells.map { round(it) }.toString()
    }

    internal fun reduce(arg: Row) {
        if (arg.firstNonZero() == this.firstNonZero() && !allZeros())
            this.add(cells.find { it != 0.0 }?.let { Row(arg).multi(-it) } as Row)
    }

    internal fun reduceInv(arg: Row){
        if (arg < this) {
            this.add(arg.cells.indexOfFirst { it != 0.0 }.let { Row(arg).multi(-this.cells[it]) })
        }
    }

}

fun round(x: Double): Double {
    var str = x.toString()
    str.indexOf('.').also { if (it+6 !in str.indices) return str.toDouble()
    else return (str.substring(0, it+5) +(
            if(str[it+6] < '5') str[it+5] else (str[it+5] + 1))).toDouble() }
}
