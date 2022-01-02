package ae.easy.productSum.solution1

fun productSum(array: List<*>, multiplier: Int = 1): Int {
    var sum = 0;

    for (e1 in array) {
        if (e1 is List<*>) {
            sum += productSum(e1, multiplier + 1)
        } else {
            sum += e1 as Int
        }
    }

    return sum * multiplier
}
