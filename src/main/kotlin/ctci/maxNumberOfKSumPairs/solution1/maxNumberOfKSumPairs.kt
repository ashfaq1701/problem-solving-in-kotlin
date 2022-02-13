package ctci.maxNumberOfKSumPairs.solution1

class Solution {
    fun maxOperations(nums: IntArray, k: Int): Int {
        val seen = mutableMapOf<Int, Int>()
        var operationCount = 0

        for (num in nums) {
            if ((k - num) in seen && seen[k - num]!! > 0) {
                operationCount += 1
                seen[k - num] = seen[k - num]!! - 1
            } else {
                if (num !in seen) {
                    seen[num] = 0
                }
                seen[num] = seen[num]!! + 1
            }
        }

        return operationCount
    }
}