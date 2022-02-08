package ctci.wordPattern.solution1

class Solution {
    fun wordPattern(pattern: String, s: String): Boolean {
        val words = s.split(" ")

        if (words.size != pattern.length) return false

        val patternMap = mutableMapOf<Char, String>()
        val wordMap = mutableMapOf<String, Char>()

        for (i in pattern.indices) {
            val currentPatternLetter = pattern[i]
            val currentWord = words[i]

            if (currentPatternLetter !in patternMap) {

                if (currentWord in wordMap) return false

                patternMap[currentPatternLetter] = currentWord
                wordMap[currentWord] = currentPatternLetter
            } else if (patternMap[currentPatternLetter]!! != currentWord) {
                return false
            }
        }

        return true
    }
}