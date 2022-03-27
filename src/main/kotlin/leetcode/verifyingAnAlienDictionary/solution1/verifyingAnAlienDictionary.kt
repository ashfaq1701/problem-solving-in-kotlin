package leetcode.verifyingAnAlienDictionary.solution1

import kotlin.math.min

class Solution {
    fun isAlienSorted(words: Array<String>, order: String): Boolean {
        val orderMap = populateOrderMap(order)
        for (idx in 1 until words.size) {
            if (!compareWords(words[idx - 1], words[idx], orderMap)) {
                return false
            }
        }

        return true
    }

    private fun populateOrderMap(order: String): Map<Char, Int> {
        val orderMap = mutableMapOf<Char, Int>()

        for (idx in order.indices) {
            orderMap[order[idx]] = idx
        }

        return orderMap
    }

    private fun compareWords(firstWord: String, secondWord: String, orderMap: Map<Char, Int>): Boolean {

        var idx = 0

        while (idx < min(firstWord.length, secondWord.length)) {
            if (orderMap[firstWord[idx]]!! < orderMap[secondWord[idx]]!!) {
                return true
            }

            if (orderMap[firstWord[idx]]!! > orderMap[secondWord[idx]]!!) {
                return false
            }

            idx += 1
        }

        return idx == firstWord.length
    }
}