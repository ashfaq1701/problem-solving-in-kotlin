package ae.easy.palindromeCheck.solution3

fun isPalindrome(string: String): Boolean {
    return helper(string, 0)
}

fun helper(string: String, i: Int): Boolean {
    val j = string.lastIndex - i

    return if (j <= i) true else string[i] == string[j] && helper(string, i + 1)
}