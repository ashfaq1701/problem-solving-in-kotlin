package ae.veryHard.palindromePartitioningMinCuts.solution1

fun palindromePartitioningMinCuts(str: String): Int {
    val cuts = MutableList(str.length) { 0 }

    for (i in 0 .. str.lastIndex) {
        if (isPalindrome(str, 0, i)) {
            cuts[i] = 0
        } else {
            cuts[i] = cuts[i - 1] + 1

            for (j in 1 until i) {
                // If str[j] to str[i] is a palindrome
                // And if we add a cut here and it becomes smaller than
                // current cut at i.
                if (isPalindrome(str, j, i) && cuts[j - 1] + 1 < cuts[i]) {
                    cuts[i] = cuts[j - 1] + 1
                }
            }
        }
    }

    return cuts.last()
}

fun isPalindrome(str: String, i: Int, j: Int): Boolean {
    var idx1 = i
    var idx2 = j

    while (idx1 < idx2) {
        if (str[idx1] != str[idx2]) {
            return false
        }

        idx1++
        idx2--
    }

    return true
}
