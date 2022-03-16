package leetcode.customSortString.solution1

class Solution {
    fun customSortString(order: String, s: String): String {
        val counts = mutableMapOf<Char, Int>()
        val charsNotInOrder = mutableListOf<Char>()

        for (ch in order) {
            counts[ch] = 0
        }

        for (ch in s) {
            if (ch in counts) {
                counts[ch] = counts[ch]!! + 1
            } else {
                charsNotInOrder.add(ch)
            }
        }

        val result = mutableListOf<Char>()

        for ((ch, count) in counts) {
            (0 until count).forEach { result.add(ch) }
        }

        result.addAll(charsNotInOrder)

        return String(result.toCharArray())
    }
}