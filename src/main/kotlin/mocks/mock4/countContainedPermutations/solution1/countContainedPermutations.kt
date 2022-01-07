package mocks.mock4.countContainedPermutations.solution1

fun countContainedPermutations(bigString: String, smallString: String): Int {

    var countPermutations = 0
    val smallStringCounts = getSmallStringCounts(smallString)
    var containedCharCounts = 0

    val bigStringCounts = mutableMapOf<Char, Int>()
    var leftIdx = 0
    var rightIdx = 0

    while (rightIdx < bigString.length) {

        val rightChar = bigString[rightIdx]
        addToCounts(bigStringCounts, rightChar)

        if (rightChar in smallStringCounts && bigStringCounts[rightChar] == smallStringCounts[rightChar]) {
            containedCharCounts += 1
        }

        while (containedCharCounts == smallStringCounts.size && leftIdx <= rightIdx) {
            val leftChar = bigString[leftIdx]

            if (rightIdx - leftIdx + 1 == smallString.length) {
                countPermutations += 1
            }

            if (leftChar in smallStringCounts && bigStringCounts[leftChar] == smallStringCounts[leftChar]) {
                containedCharCounts -= 1
            }

            removeFromCounts(bigStringCounts, leftChar)

            leftIdx += 1
        }

        rightIdx += 1
    }

    return countPermutations
}

fun getSmallStringCounts(string: String): Map<Char, Int> {
    val counts = mutableMapOf<Char, Int>()

    for (c in string) {
        if (c !in counts) {
            counts[c] = 0
        }

        counts[c] = counts[c]!! + 1
    }

    return counts
}

fun addToCounts(counts: MutableMap<Char, Int>, ch: Char) {
    if (ch !in counts) {
        counts[ch] = 0
    }

    counts[ch] = counts[ch]!! + 1
}

fun removeFromCounts(counts: MutableMap<Char, Int>, ch: Char) {
    if (ch !in counts) return

    counts[ch] = counts[ch]!! - 1
}
