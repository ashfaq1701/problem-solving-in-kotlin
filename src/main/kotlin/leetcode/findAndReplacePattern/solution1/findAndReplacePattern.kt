package leetcode.findAndReplacePattern.solution1

class Solution {
    fun findAndReplacePattern(words: Array<String>, pattern: String): List<String> {
        val result = mutableListOf<String>()

        for (word in words) {
            if (matches(word, pattern)) {
                result.add(word)
            }
        }

        return result
    }

    fun matches(word: String, pattern: String): Boolean {
        if (word.length != pattern.length) return false

        val wordMap = mutableMapOf<Char, Char>()
        val patternMap = mutableMapOf<Char, Char>()

        for (i in word.indices) {
            val c = word[i]
            val p = pattern[i]

            if (c !in wordMap) {
                wordMap[c] = p
            }

            if (p !in patternMap) {
                patternMap[p] = c
            }

            if (wordMap[c]!! != p || patternMap[p]!! != c) {
                return false
            }
        }

        return true
    }
}