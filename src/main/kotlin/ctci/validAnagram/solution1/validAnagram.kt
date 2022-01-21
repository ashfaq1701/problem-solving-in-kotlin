package ctci.validAnagram.solution1

class Solution {
    fun isAnagram(s: String, t: String): Boolean {
        return s.toCharArray().sorted() == t.toCharArray().sorted()
    }
}