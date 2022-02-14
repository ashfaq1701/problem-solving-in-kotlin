package ctci.maxNumberOfKSumPairs.solution2

class Solution {
    fun maxOperations(nums: IntArray, k: Int): Int {
        nums.sort()
        var operationCount = 0

        var left = 0
        var right = nums.lastIndex

        while (left < right) {
            val currentSum = nums[left] + nums[right]

            if (currentSum < k) {
                left += 1
            } else if (currentSum > k) {
                right -= 1
            } else {
                operationCount += 1
                left += 1
                right -= 1
            }
        }

        return operationCount
    }
}