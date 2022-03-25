package leetcode.validPalindrome.solution2

class Solution {
    fun isPalindrome(s: String): Boolean {
        var i = 0
        var j = s.lastIndex

        while (i < j) {
            while (i < j && !s[i].isLetterOrDigit()) {
                i += 1
            }

            while (i < j && !s[j].isLetterOrDigit()) {
                j -= 1
            }

            if (i < j && s[i].toLowerCase() != s[j].toLowerCase()) {
                return false
            }

            i += 1
            j -= 1
        }

        return true
    }
}