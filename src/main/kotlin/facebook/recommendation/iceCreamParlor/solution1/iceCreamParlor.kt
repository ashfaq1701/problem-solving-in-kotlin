package facebook.recommendation.iceCreamParlor.solution1

fun icecreamParlor(m: Int, arr: Array<Int>): Array<Int> {
    val seen = mutableMapOf<Int, Int>()

    arr.forEachIndexed { idx, price ->
        if (m - price in seen) {
            return arrayOf(seen[m - price]!! + 1, idx + 1)
        }

        seen[price] = idx
    }

    return arrayOf()
}