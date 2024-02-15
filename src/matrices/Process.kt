package matrices

import kotlin.math.pow

class Process(private var theQ: ArrayList<ArrayList<Sequence>>){

    internal constructor(matrix: Matrix) : this(ArrayList()){
        var y = arrayListOf<Sequence>()
        for (i in matrix.available()){
            for (j in matrix[0].available())
                if (i == j)
                    y.add(Sequence(arrayListOf(Root(matrix[i][j], 0.0), Root(-1.0, 1.0))))
                else
                    y.add(Sequence(arrayListOf(Root(matrix[i][j], 0.0))))
            theQ.add(y)
            y = arrayListOf()
        }
    }

    internal constructor(arg: Process) : this(ArrayList()){
        var x = arrayListOf<Sequence>()
        arg.theQ.forEach { row ->
            run {
                row.forEach { x.add(Sequence(it)) }
                this.theQ.add(x)
                x = arrayListOf()
            }
        }
    }

    private fun slice(): Process {
        theQ.forEach { it.removeAt(0) }
        return this
    }

    private fun isSquare(): Boolean = theQ.size == theQ[0].size

    private fun size() : String = if (theQ.isNotEmpty()) "" + theQ.size + " X " + theQ[0].size else "0 X 0"

    fun deleteRow(i: Int): Process {
        this.theQ.removeAt(i)
        return this
    }

    internal tailrec fun det(): Sequence{
        if (this.isSquare() && this.size() != "2 X 2") {
            val x = Process(this).slice()
            var xOut = Sequence()
            for (i in theQ.indices){
                xOut.add(Process(x).deleteRow(i).det().multi(theQ[i][0]).multi((-1.0).pow(i.toDouble())))
            }
            return xOut
        }
        else if (this.isSquare() && this.size() == "2 X 2")
            return theQ[0][0].multi(theQ[1][1]).add(theQ[0][1].multi(theQ[1][0]).multi(-1.0))
        else throw NotASquareMatrix()
    }

    override fun toString(): String {
        var str = "{"
        theQ.forEach { str += it.toString() + '\n'  }
        return str.removeRange(str.length -1,str.length) + " }"
    }
}