package facebook.recommendation.fibonacciNumbers.solution1

fun main() {
    println(fibonacciNumbers(3))
}

fun fibonacciNumbers(n: Int): Int {
    val cache = MutableList<Int>(n + 1) { -1 }
    cache[0] = 0
    cache[1] = 0
    cache[2] = 1
    return fibonacciHelper(n, cache)
}

fun fibonacciHelper(n: Int, cache: MutableList<Int>): Int {
    if (n <= 2) return cache[n]

    if (cache[n] != -1) return cache[n]

    cache[n] = fibonacciHelper(n - 1, cache) + fibonacciHelper(n - 2, cache)

    return cache[n]
}