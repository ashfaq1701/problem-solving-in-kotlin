package ctci.missingNumber.solution2

class Solution {
    fun missingNumber(nums: IntArray): Int {
        nums.sort()

        for (i in 0 .. nums.lastIndex) {
            if (nums[i] != i) {
                return i
            }
        }

        return nums.size
    }
}