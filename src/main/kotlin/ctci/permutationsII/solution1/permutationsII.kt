package ctci.permutationsII.solution1

class Solution {
    fun permuteUnique(nums: IntArray): List<List<Int>> {
        val counts = getCountsMap(nums)
        val current = mutableListOf<Int>()
        val result = mutableListOf<List<Int>>()
        backtrackPermutationHelper(counts, current, result, nums.size)
        return result
    }

    fun backtrackPermutationHelper(counts: MutableMap<Int, Int>, current: MutableList<Int>, result: MutableList<List<Int>>, n: Int) {
        if (current.size == n) {
            result.add(current.toList())
            return
        }

        for ((item, count) in counts) {
            if (count == 0) {
                continue
            }

            counts[item] = counts[item]!! - 1
            current.add(item)

            backtrackPermutationHelper(counts, current, result, n)

            counts[item] = counts[item]!! + 1
            current.removeAt(current.lastIndex)
        }
    }

    fun getCountsMap(nums: IntArray): MutableMap<Int, Int> {
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