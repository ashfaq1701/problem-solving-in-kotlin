package leetcode.palindromeNumber.solution1

class Solution {
    fun isPalindrome(x: Int): Boolean {
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false
        }

        var revertedNumber = 0
        var currentX = x
        while (currentX > revertedNumber) {
            revertedNumber = revertedNumber * 10 + (currentX % 10)
            currentX /= 10
        }

        return currentX == revertedNumber || currentX == revertedNumber / 10
    }
}