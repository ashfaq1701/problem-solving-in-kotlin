package ctci.wordPattern.solution2

class Solution {
    fun wordPattern(pattern: String, s: String): Boolean {
        val words = s.split(" ")
        val patternMap = mutableMapOf<String, Int>()

        if (words.size != pattern.length) return false

        for (i in pattern.indices) {
            val currentLetter = pattern[i]
            val currentLetterAsPattern = "P$currentLetter"
            val currentWord = words[i]

            if (currentLetterAsPattern !in patternMap) {
                patternMap[currentLetterAsPattern] = i
            }

            if (currentWord !in patternMap) {
                patternMap[currentWord] = i
            }

            if (patternMap[currentLetterAsPattern] != patternMap[currentWord]) {
                return false
            }
        }

        return true
    }
}