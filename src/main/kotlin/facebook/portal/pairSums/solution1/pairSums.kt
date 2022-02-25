package facebook.portal.pairSums.solution1

fun main(args : Array<String>) {
    println(numberOfWays(arrayOf(1, 5, 3, 3, 3), 6))
}

fun numberOfWays(arr: Array<Int>, k: Int): Int {
    val seen = mutableMapOf<Int, Int>()
    var pairCount = 0

    for (element in arr) {
        if ((k - element) in seen) {
            pairCount += seen[k - element]!!
        }

        if (element !in seen) {
            seen[element] = 0
        }
        seen[element] = seen[element]!! + 1
    }

    return pairCount
}