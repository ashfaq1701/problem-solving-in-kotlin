package ae.veryHard.palindromePartitioningMinCuts.solution2

fun palindromePartitioningMinCuts(str: String): Int {
    val palindromesArray = preparePalindromesArray(str)

    val cuts = MutableList(str.length) { 0 }

    for (i in 0 .. str.lastIndex) {
        if (palindromesArray[0][i]) {
            cuts[i] = 0
        } else {
            cuts[i] = cuts[i - 1] + 1

            for (j in 1 until i) {
                // If str[j] to str[i] is a palindrome
                // And if we add a cut here and it becomes smaller than
                // current cut at i.
                if (palindromesArray[j][i] && cuts[j - 1] + 1 < cuts[i]) {
                    cuts[i] = cuts[j - 1] + 1
                }
            }
        }
    }

    return cuts.last()
}

fun preparePalindromesArray(str: String): List<List<Boolean>> {
    val palindromes = MutableList(str.length) {
        MutableList(str.length) { false }
    }

    for (i in 0 .. str.lastIndex) {
        palindromes[i][i] = true
    }

    for (length in 2 .. str.length) {

        for (i in 0 .. str.length - length) {

            val j = i + length - 1

            if (length == 2) {
                palindromes[i][j] = str[i] == str[j]
            } else {
                palindromes[i][j] = str[i] == str[j] && palindromes[i + 1][j - 1]
            }
        }
    }

    return palindromes.map { it.toList() }
}