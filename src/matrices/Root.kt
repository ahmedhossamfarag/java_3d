package matrices

class Root(var cof: Double = 0.0, var pow: Double = 0.0) {

    internal constructor(cof: Int, pow: Int) : this(cof.toDouble(), pow.toDouble())

    internal constructor(arg: Root) : this(arg.cof, arg.pow)

    internal operator fun get(i: Int) = if (i==0) cof else pow

    fun add(x: Root): Boolean {
        if(this.pow == x.pow) {
            this.cof += x.cof
            return true
        }
        return false
    }
    fun multi(x: Double){
        this.cof *= x
    }

    fun multi(x: Root): Boolean {
        this.cof *= x.cof
        this.pow += x.pow
        return true
    }

    override fun toString():String {
        return ("" + (if (cof == 1.0 && pow > 0.0) "" else cof) +
                if (pow == 0.0) "" else {"X" +
                        if (pow == 1.0) "" else ("^" + pow.toInt())})
    }
}