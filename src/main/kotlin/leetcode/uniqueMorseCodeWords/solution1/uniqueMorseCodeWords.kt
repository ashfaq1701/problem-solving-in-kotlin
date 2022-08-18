package leetcode.uniqueMorseCodeWords.solution1

class Solution {
    fun uniqueMorseRepresentations(words: Array<String>): Int {
        val morseCodes = mutableListOf(".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--..")

        val codeSet = mutableSetOf<String>()

        for (word in words) {
            val codeOfWord = word.map { ch -> morseCodes[ch - 'a'] }.joinToString("")
            codeSet.add(codeOfWord)
        }

        return codeSet.size
    }
}
