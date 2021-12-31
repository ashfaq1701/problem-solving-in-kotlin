package ae.hard.numbersInPi.solution1

import kotlin.math.min

fun numbersInPi(pi: String, numbers: List<String>): Int {
    val numbersSet = mutableSetOf<String>()
    for (number in numbers) {
        numbersSet.add(number)
    }

    val cache = mutableMapOf<Int, Int>()
    val minSpaces = getMinSpaces(pi, numbersSet, cache, 0)

    return if (minSpaces == Integer.MAX_VALUE) -1 else minSpaces
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