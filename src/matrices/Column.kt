package matrices

import java.util.ArrayList

open class Column {
    var cells = ArrayList<Double>()

    constructor()

    constructor(vararg arg : Double){
        for (x in  arg) cells.add(x)
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

    operator fun set(i: Int, value: Int) {
        cells[i] = value.toDouble()
    }

    operator fun set(i: Int, value: Double) {
        cells[i] = value
    }

    fun add(x : Double): Column {
        cells.add(x)
        return this
    }

    fun add(x : Int): Column {
        return add(x.toDouble())
    }

    fun size() : Int = cells.size

    fun dim() : Int = size()

    fun slice() {
        if (size()==0) throw EmptyException()
        cells.removeAt(0)
    }

    fun available() = cells.indices

    fun multi(x: Double): Column {
        if (size()==0) throw EmptyException()
        cells.withIndex().onEach { cells[it.index] = it.value * it.value }
        return this
    }

    fun multi(x: Int): Column{
        return multi(x.toDouble())
    }

    fun multi(x: Column) : Double{
        if (this.size()==x.size()&&size()!=0){
            var xOut : Double = 0.0
            for (i in this.available())
                xOut += (cells[i] * x[i])
            return xOut
        }
        else throw NotMultipliable()
    }

    fun tran() : Row = Row(this)

    fun firstNonZero() = cells.indexOfFirst { it != 0.0 }

    fun getCopy(): Column = Column(this)

    fun round(): Column {
        cells.withIndex().onEach { cells[it.index] = it.value * it.value }
        return this
    }

    fun toVector() : Vector = Vector(this.cells)

    override fun toString():String {
        return cells.toString()
    }
}