package leetcode.countPermutations.solution1

class Solution {

    val a = 0; val e = 1; val i = 2; val o = 3; val u = 4
    private val relations = listOf(listOf(e), listOf(a, i), listOf(a, e, o, u), listOf(i, u), listOf(a))
    private val mod = 1e9+7

    private fun countPermutationHelper(count: Int, ch: Int, memo: List<MutableList<Int>>): Int {
        if (count == 1) return 1

        if (memo[count][ch] != -1) {
            return memo[count][ch]
        }

        var permutations = 0.0

        for (nextCh in relations[ch]) {
            permutations += countPermutationHelper(count - 1, nextCh, memo)
            permutations %= mod
        }

        memo[count][ch] = permutations.toInt()
        return memo[count][ch]
    }

    fun countVowelPermutation(n: Int): Int {
        val memo = List(n + 1) { MutableList(5) { -1 } }

        var permutationCount = 0.0
        for (ch in 0 until 5) {
            permutationCount += countPermutationHelper(n, ch, memo)
            permutationCount %= mod
        }

        return permutationCount.toInt()
    }
}