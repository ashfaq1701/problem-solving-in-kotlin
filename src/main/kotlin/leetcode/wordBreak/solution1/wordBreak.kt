package leetcode.wordBreak.solution1

class Solution {
    fun wordBreak(s: String, wordDict: List<String>): Boolean {
        val wordSet = wordDict.toSet()
        return wordBreakHelper(0, s, wordSet, mutableMapOf())
    }

    private fun wordBreakHelper(startIdx: Int, s: String, wordSet: Set<String>, cache: MutableMap<Int, Boolean>): Boolean {

        if (startIdx == s.length) return true

        if (startIdx in cache) return cache[startIdx]!!

        for (idx in startIdx until s.length) {

            val substr = s.substring(startIdx .. idx)
            if (substr in wordSet &&
                wordBreakHelper(idx + 1, s, wordSet, cache)) {

                cache[startIdx] = true
                return true
            }
        }

        cache[startIdx] = false
        return false
    }
}