package ctci.majorityElement.solution1

class Solution {
    fun majorityElement(nums: IntArray): Int {
        val counts = mutableMapOf<Int, Int>()

        for (num in nums) {
            if (num !in counts) {
                counts[num] = 0
            }
            counts[num] = counts[num]!! + 1

            if (counts[num]!! > nums.size / 2) {
                return num
            }
        }

        return -1
    }
}