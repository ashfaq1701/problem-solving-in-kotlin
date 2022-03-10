package leetcode.strobogrammaticNumberII.solution1

class Solution {
    fun findStrobogrammatic(n: Int): List<String> {
        val pairs = mapOf('1' to '1', '6' to '9', '8' to '8', '9' to '6')
        return findStrobogrammaticHelper(n, n, pairs)
    }

    fun findStrobogrammaticHelper(current: Int, n: Int, pairs: Map<Char, Char>): List<String> {
        if (current == 0) {
            return listOf("")
        }

        if (current == 1) {
            return listOf("0", "1", "8")
        }

        val prevLevel = findStrobogrammaticHelper(current - 2, n, pairs)
        val currentLevel = mutableListOf<String>()

        for (prevNumber in prevLevel) {
            if (current != n) {
                currentLevel.add("0${prevNumber}0")
            }

            for ((leftDigit, rightDigit) in pairs) {
                currentLevel.add("$leftDigit$prevNumber$rightDigit")
            }
        }

        return currentLevel
    }
}