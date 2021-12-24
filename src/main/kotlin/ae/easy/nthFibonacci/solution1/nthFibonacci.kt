package ae.easy.nthFibonacci.solution1

fun getNthFib(n: Int): Int {
    if (n == 1) {
        return 0
    }

    if (n == 2) {
        return 1
    }

    return getNthFib(n - 1) + getNthFib(n - 2)
}
