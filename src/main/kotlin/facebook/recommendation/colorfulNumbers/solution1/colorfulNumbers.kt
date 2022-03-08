package facebook.recommendation.colorfulNumbers.solution1

fun isColorful(n: Int): String {
    val digits = getDigits(n)
    val powerSet = getPowerSet(digits)

    val multiplications = mutableSetOf<Int>()
    for (currentSet in powerSet) {
        val multiplication = getMultiplication(currentSet)
        if (multiplication in multiplications) {
            return "Not Colorful"
        }

        multiplications.add(multiplication)
    }

    return "Colorful"
}

fun getDigits(n: Int): List<Int> {
    val digits = mutableListOf<Int>()

    var current = n
    while (current > 0) {
        digits.add(current % 10)
        current /= 10
    }

    return digits
}

fun getPowerSet(nums: List<Int>): List<List<Int>> {
    val powerSet = mutableListOf(listOf<Int>())

    for (num in nums) {
        val currentSize = powerSet.size

        for (i in 0 until currentSize) {
            val current = powerSet[i]
            powerSet.add(current.plus(num))
        }
    }

    return powerSet
}

fun getMultiplication(items: List<Int>): Int {
    var multiplication = 1

    for (item in items) {
        multiplication *= item
    }

    return multiplication
}

fun main() {
    println(isColorful(3245))
    println(isColorful(326))
}