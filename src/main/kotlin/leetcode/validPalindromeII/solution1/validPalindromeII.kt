package leetcode.validPalindromeII.solution1

class Solution {
    fun validPalindrome(s: String): Boolean {
        return validPalindromeHelper(s, 0, s.lastIndex, false)
    }

    fun validPalindromeHelper(s: String, left: Int, right: Int, isReplaced: Boolean): Boolean {
        var currentLeft = left
        var currentRight = right

        while (currentLeft <= currentRight) {
            if (s[currentLeft] != s[currentRight]) {
                return if (!isReplaced) {
                    validPalindromeHelper(s, currentLeft + 1, currentRight, true) ||
                            validPalindromeHelper(s, currentLeft, currentRight - 1, true)
                } else {
                    false
                }
            }

            currentLeft += 1
            currentRight -= 1
        }

        return true
    }
}