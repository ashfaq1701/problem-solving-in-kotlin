package ae.medium.arrayOfProducts.solution2

fun arrayOfProducts(array: List<Int>): List<Int> {
    val products = MutableList(array.size) { 0 }

    var leftRunningProduct = 1
    for (i in 0 .. array.lastIndex) {
        products[i] = leftRunningProduct
        leftRunningProduct *= array[i]
    }

    var rightRunningProduct = 1
    for (i in array.lastIndex downTo 0) {
        products[i] *= rightRunningProduct
        rightRunningProduct *= array[i]
    }

    return products
}
