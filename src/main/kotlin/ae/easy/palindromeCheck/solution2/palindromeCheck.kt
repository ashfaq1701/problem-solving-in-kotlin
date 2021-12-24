package ae.easy.palindromeCheck.solution2

fun isPalindrome(string: String): Boolean {
    var left = 0
    var right = string.lastIndex

    while (left < right) {
        if (string[left] != string[right]) {
            return false
        }

        left += 1
        right -= 1
    }

    return true
}
