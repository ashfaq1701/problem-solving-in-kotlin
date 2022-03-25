package leetcode.romanToInteger.solution1

class Solution {
    fun romanToInt(s: String): Int {
        val valueMap = mutableMapOf(
            'I' to 1,
            'V' to 5,
            'X' to 10,
            'L' to 50,
            'C' to 100,
            'D' to 500,
            'M' to 1000
        )

        var currentIdx = 0
        var currentValue = 0

        while (currentIdx < s.length) {
            if (currentIdx < s.lastIndex && valueMap[s[currentIdx]]!! < valueMap[s[currentIdx + 1]]!!) {
                currentValue += (valueMap[s[currentIdx + 1]]!! - valueMap[s[currentIdx]]!!)
                currentIdx += 2
            } else {
                currentValue += valueMap[s[currentIdx]]!!
                currentIdx += 1
            }
        }

        return currentValue
    }
}