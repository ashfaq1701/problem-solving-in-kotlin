package ae.veryHard.numberOfBinaryTreeTopologies.solution2

// O(n ^ 2) Time | O(n) Space
fun numberOfBinaryTreeTopologies(n: Int): Int {
    val memo = mutableMapOf<Int, Int>(0 to 1)
    return recurseHelper(n, memo)
}

fun recurseHelper(n: Int, memo: MutableMap<Int, Int>): Int {
    if (memo.containsKey(n)) {
        return memo[n]!!
    }

    var topologies = 0
    for (l in 0 until n) {
        val r = n - 1 - l
        val fl = recurseHelper(l, memo)
        val fr = recurseHelper(r, memo)
        topologies += (fl * fr)
    }
    memo[n] = topologies

    return memo[n]!!
}
