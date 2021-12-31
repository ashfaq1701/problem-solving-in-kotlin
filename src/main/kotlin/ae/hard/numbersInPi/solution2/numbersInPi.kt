package ae.hard.numbersInPi.solution2

import kotlin.math.min

fun numbersInPi(pi: String, numbers: List<String>): Int {
    val numbersSet = mutableSetOf<String>()
    for (number in numbers) {
        numbersSet.add(number)
    }

    val cache = mutableMapOf<Int, Int>()

    for (idx in pi.lastIndex downTo 0) {
        val minSpaces = getMinSpaces(pi, numbersSet, cache, idx)
    }

    return if (cache[0]!! == Integer.MAX_VALUE) -1 else cache[0]!!
}

fun getMinSpaces(pi: String, numbersSet: Set<String>, cache: MutableMap<Int, Int>, idx: Int): Int {
    if (idx == pi.length) {
        return -1
    }

    if (cache.containsKey(idx)) {
        return cache[idx]!!
    }

    var minSpaces = Integer.MAX_VALUE

    for (i in idx .. pi.lastIndex) {
        // Substring inclusive both idx and i
        val prefix = pi.substring(idx, i + 1)

        if (numbersSet.contains(prefix)) {
            val minSpacesSuffix = getMinSpaces(pi, numbersSet, cache, i + 1)
            minSpaces = min(
                minSpaces,
                if (minSpacesSuffix == Integer.MAX_VALUE) minSpacesSuffix else minSpacesSuffix + 1
            )
        }
    }

    cache[idx] = minSpaces
    return cache[idx]!!
}