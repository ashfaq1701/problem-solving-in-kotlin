package ctci.missingNumber.solution3

class Solution {
    fun missingNumber(nums: IntArray): Int {
        val sum = nums.sum()
        val expectedSum = (nums.size * (nums.size + 1)) / 2

        return expectedSum - sum
    }
}