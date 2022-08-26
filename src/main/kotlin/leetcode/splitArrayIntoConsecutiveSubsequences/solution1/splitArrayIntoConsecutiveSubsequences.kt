package leetcode.splitArrayIntoConsecutiveSubsequences.solution1

class Solution {
    fun isPossible(nums: IntArray): Boolean {
        val subsequences = mutableMapOf<Int, Int>()
        val frequencies = mutableMapOf<Int, Int>()

        for (num in nums) {
            if (num !in frequencies) {
                frequencies[num] = 0
            }

            frequencies[num] = frequencies[num]!! + 1
        }

        for(num in nums) {

            if (frequencies[num]!! == 0) {
                continue;
            }

            if ((subsequences[num - 1] ?: 0) > 0) {

                subsequences[num - 1] = subsequences[num - 1]!! - 1
                subsequences[num] = (subsequences[num] ?: 0) + 1
            } else if ((frequencies[num + 1] ?: 0) > 0 &&
                (frequencies[num + 2] ?: 0) > 0) {

                subsequences[num + 2] = (subsequences[num + 2] ?: 0) + 1
                frequencies[num + 1] = frequencies[num + 1]!! - 1
                frequencies[num + 2] = frequencies[num + 2]!! - 1
            } else {
                return false
            }

            frequencies[num] = frequencies[num]!! - 1
        }

        return true
    }
}