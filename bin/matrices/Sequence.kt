package matrices

class Sequence(private var roots: ArrayList<Root>) {

    internal constructor(arg: Sequence): this(ArrayList()){
        arg.roots.forEach { this.roots.add(Root(it)) }
    }

    internal constructor(vararg arg: Root) : this(ArrayList()){
        arg.forEach { add(it) }
    }

    operator fun get(i: Int) = roots[i]

    fun add(x: Root): Sequence {
        if (roots.size == 0) roots.add(x)
        else {
            var bool = false
            for (e in this.roots) {
                bool = e.add(x)
                if (bool) break
            }

            if(!bool) roots.add(x)
        }
        return this
    }

    fun multi(x: Root): Sequence {
        roots.forEach { it.multi(x) }
        return this
    }

    fun multi(x: Double): Sequence {
        roots.forEach { it.multi(x) }
        return this
    }

    fun add(x: Sequence): Sequence {
        x.roots.forEach { this.add(it)}
        return this
    }

    fun multi(x: Sequence): Sequence {
        var s = Sequence()
        x.roots.forEach { s.add(Sequence(this).multi(it)) }
        this.roots = s.roots
        return this
    }

    fun degree(): Int = roots.size - 1

    override fun toString():String {
        return roots.map { it.toString() }.reduce{s1, s2 -> if (s2[0]=='-') "$s1 $s2" else "$s1 +$s2" }
    }

}