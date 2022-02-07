package ctci.letterCombinationsOfAPhoneNumber.solution1

class Solution {
    fun letterCombinations(digits: String): List<String> {
        val charMap = mapOf(
            '2' to listOf('a', 'b', 'c'),
            '3' to listOf('d', 'e', 'f'),
            '4' to listOf('g', 'h', 'i'),
            '5' to listOf('j', 'k', 'l'),
            '6' to listOf('m', 'n', 'o'),
            '7' to listOf('p', 'q', 'r', 's'),
            '8' to listOf('t', 'u', 'v'),
            '9' to listOf('w', 'x', 'y', 'z')
        )

        val result = mutableListOf<String>()

        if (digits.isEmpty()) return result

        findLetterCombinations(0, digits, mutableListOf(), result, charMap)
        return result
    }

    fun findLetterCombinations(idx: Int, digits: String, current: MutableList<Char>, result: MutableList<String>, charMap: Map<Char, List<Char>>) {
        if (idx == digits.length) {
            result.add(String(current.toCharArray()))
            return
        }

        val currentDigit = digits[idx]

        for (ch in charMap[currentDigit]!!) {
            current.add(ch)
            findLetterCombinations(idx + 1, digits, current, result, charMap)
            current.removeAt(current.lastIndex)
        }
    }
}