package ctci.validAnagram.solution2

class Solution {
    fun isAnagram(s: String, t: String): Boolean {
        if (s.length > t.length) return isAnagram(t, s)

        val charFreq = mutableMapOf<Char, Int>()

        for (c in s) {
            if (c !in charFreq) {
                charFreq[c] = 0
            }

            charFreq[c] = charFreq[c]!! + 1
        }

        for (c in t) {
            if (c !in charFreq || charFreq[c] == 0) {
                return false
            }

            charFreq[c] = charFreq[c]!! - 1
        }

        return true
    }
}