package leetcode.findAndReplacePattern.solution2

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
        for (i in word.indices) {
            val w = word[i]
            val p = pattern[i]

            if (w !in wordMap) {
                wordMap[w] = p
            }

            if (wordMap[w]!! != p) return false
        }

        val seen = MutableList(26) { false }

        for (p in wordMap.values) {
            if (seen[p - 'a']) return false

            seen[p - 'a'] = true
        }

        return true
    }
}