package ae.easy.nthFibonacci.solution3

fun getNthFib(n: Int): Int {
    val lastTwo = mutableListOf(0, 1)

    var counter = 3

    while (counter <= n) {
        val nextFibonacci = lastTwo[0] + lastTwo[1]
        lastTwo[0] = lastTwo[1]
        lastTwo[1] = nextFibonacci

        counter += 1
    }

    return if (n > 1) lastTwo[1] else lastTwo[0]
}
