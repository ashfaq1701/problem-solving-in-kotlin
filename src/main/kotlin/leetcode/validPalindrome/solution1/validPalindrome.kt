package leetcode.validPalindrome.solution1

fun Char.isAlNum(): Boolean = isDigit() || isLetter()

class Solution {
    fun isPalindrome(s: String): Boolean {
        var left = 0
        var right = s.lastIndex

        while (left < right) {
            while (left < s.length && !s[left].isAlNum()) {
                left += 1
            }

            while (right >= 0 && !s[right].isAlNum()) {
                right -= 1
            }

            if (left >= right) break

            if (s[left].toLowerCase() != s[right].toLowerCase()) {
                return false
            }

            left += 1
            right -= 1
        }

        return true
    }
}