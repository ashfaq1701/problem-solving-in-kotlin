package ctci.missingNumber.solution1

class Solution {
    fun missingNumber(nums: IntArray): Int {
        val numMap = mutableMapOf<Int, Boolean>()

        for (num in nums) {
            numMap[num] = true
        }

        for (i in 0 .. nums.size) {
            if (i !in numMap) {
                return i
            }
        }

        return -1
    }
}