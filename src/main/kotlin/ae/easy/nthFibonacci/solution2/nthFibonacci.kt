package ae.easy.nthFibonacci.solution2

fun getNthFib(n: Int): Int {
    val memo = mutableMapOf(1 to 0, 2 to 1)
    return helper(n, memo)
}

fun helper(n: Int, memo: MutableMap<Int, Int>): Int {
    if (memo.containsKey(n)) {
        return memo[n]!!
    }

    memo[n] = helper(n - 1, memo) + helper(n - 2, memo)

    return memo[n]!!
}
