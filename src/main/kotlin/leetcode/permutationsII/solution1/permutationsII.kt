package leetcode.permutationsII.solution1

class Solution {
    fun permuteUnique(nums: IntArray): List<List<Int>> {
        val counts = getCounts(nums)
        val current = mutableListOf<Int>()
        val result = mutableListOf<List<Int>>()
        backtrackPermutationHelper(current, counts, result, nums.size)
        return result
    }

    private fun backtrackPermutationHelper(current: MutableList<Int>, counts: MutableMap<Int, Int>, result: MutableList<List<Int>>, n: Int) {
        if (current.size == n) {
            result.add(current.toList())
            return
        }

        for ((num, count) in counts) {
            if (count == 0) continue

            counts[num] = counts[num]!! - 1
            current.add(num)

            backtrackPermutationHelper(current, counts, result, n)

            counts[num] = counts[num]!! + 1
            current.removeAt(current.lastIndex)
        }
    }

    private fun getCounts(nums: IntArray): MutableMap<Int, Int> {
        val counts = mutableMapOf<Int, Int>()

        for (num in nums) {
            if (num !in counts) {
                counts[num] = 0
            }

            counts[num] = counts[num]!! + 1
        }

        return counts
    }
}