package ae.medium.arrayOfProducts.solution1

fun arrayOfProducts(array: List<Int>): List<Int> {

    val leftProducts = MutableList<Int>(array.size) { 0 }
    val rightProducts = MutableList<Int>(array.size) { 0 }

    var leftRunningProduct = 1
    for (i in 0 .. array.lastIndex) {
        leftProducts[i] = leftRunningProduct
        leftRunningProduct *= array[i]
    }

    var rightRunningProduct = 1
    for (i in array.lastIndex downTo 0) {
        rightProducts[i] = rightRunningProduct
        rightRunningProduct *= array[i]
    }

    return leftProducts.mapIndexed { idx, leftProduct ->
        leftProduct * rightProducts[idx]
    }
}
