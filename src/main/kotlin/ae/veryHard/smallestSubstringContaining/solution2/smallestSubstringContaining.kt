package ae.veryHard.smallestSubstringContaining.solution2

fun smallestSubstringContaining(bigString: String, smallString: String): String {
    val tMap = mutableMapOf<Char, Int>()
    for (c in smallString) {
        if (!tMap.containsKey(c)) {
            tMap[c] = 0
        }
        tMap[c] = tMap[c]!! + 1
    }

    val sMap = mutableMapOf<Char, Int>()
    var formed = 0

    var left = 0
    var right = 0
    var result = mutableListOf(0, Integer.MAX_VALUE)

    while (right < bigString.length) {

        val c = bigString[right]

        if (!sMap.containsKey(c)) {
            sMap[c] = 0
        }
        sMap[c] = sMap[c]!! + 1

        if (tMap.containsKey(c) && sMap[c]!! == tMap[c]!!) {
            formed += 1
        }

        while (left <= right && formed == tMap.size) {
            if (right - left < result[1] - result[0]) {
                result = mutableListOf(left, right)
            }

            val leftChar = bigString[left]

            if (sMap[leftChar] == tMap[leftChar]) {
                formed -= 1
            }
            sMap[leftChar] = sMap[leftChar]!! - 1

            left += 1
        }

        right += 1
    }

    return if (result[1] == Integer.MAX_VALUE) "" else bigString.substring(result[0] .. result[1])
}
