package matrices

import kotlin.math.pow
import kotlin.math.sqrt

open class Vector : Column {
    constructor() : super()

    constructor(vararg arg : Double) : super(arg.toList())

    constructor(vararg arg : Int) : super(arg.toList())

    constructor(arg: Collection<Double>) : super(arg)

    constructor(arg: Row) : super(arg)

    constructor(arg: Column) : super(arg)
}

fun areIndependant(vararg arg: Vector): Boolean {
    if (arg.size > arg[0].dim())
        return false
    for (x in arg)
        if (x.dim() != arg[0].dim())
            throw NotSameDimension()
    return Matrix(*arg).rank() == arg.size
}

fun areBasis(vararg vectors: Vector): Boolean {
    return areIndependant(*vectors) && vectors.size == vectors[0].dim()
}

fun areLinearCom(vector: Vector, vararg vectors: Vector): Boolean {
    vectors.forEach { if (it.dim() != vector.dim()) return false }
    return Matrix(*vectors).rank() == Matrix(*vectors).add(vector).rank()
}

fun solve(cooffitients: Matrix, constants: Matrix): Pair<Matrix, String> {
    var x = cooffitients.getCopy().merge(constants).reducedEchlon()
    var y = if (x.rank(cooffitients.columnsNo())== cooffitients.columnsNo()) "One Solution"
    else if (x.rank(cooffitients.columnsNo()) == x.rank()) "Infinite Solutions" else "No Solution"
    return x to y
}

fun nullBasis(arg: Matrix): ArrayList<Vector> {
    var x = arg.getCopy().reducedEchlon()
    var y = ArrayList<Vector>()
    x.freeVars().forEach { y.add(x.getColumn(it-1).multi(-1).toVector()) }
    return y
}

fun rowBasis(arg: Matrix): ArrayList<Row> {
    var x = arg.getCopy().echlon()
    var y = ArrayList<Row>()
    x.get().forEach { if (it.cells.any{e -> e != 0.0}) y.add(it) }
    return y
}

fun columnBasis(arg: Matrix): ArrayList<Column> {
    var x = arg.getCopy().echlon()
    var y = ArrayList<Column>()
    x.freeVars().forEach { y.add(arg.getColumn(it-1)) }
    return y
}

fun charEquation(matrix: Matrix): String {
    return charEquationSeq(matrix).toString()
}

private fun charEquationSeq(matrix: Matrix): Sequence {
    if (matrix.get().isEmpty() || !matrix.isSquare())
        throw NotASquareMatrix()
    return Process(matrix).det()
}

fun eigenValue(matrix: Matrix): Pair<Double, Double> {
    if (matrix.dim() != "2 X 2")
        throw Exception()
    charEquationSeq(matrix).also {
        return solveEquation2(it[2][1],it[2][1],it[2][1])
    }
}

fun eigenSpace(matrix: Matrix, double: Double): ArrayList<Vector> {
    return nullBasis(identity(matrix.rowsNo()).multi(-double).add(matrix))
}

fun eigenVector(matrix: Matrix, double: Double): Vector = eigenSpace(matrix, double)[0]

private fun solveEquation2(a: Double, b: Double, c:Double): Pair<Double, Double> {
    val k = sqrt(b.pow(2)-(4*a*c))
    return (-b + k)/(2*a) to (-b - k)/(2*a)
}