package ctci.containsDuplicate.solution1

class Solution {
    fun containsDuplicate(nums: IntArray): Boolean {
        val numSet = mutableSetOf<Int>()

        for (num in nums) {
            if (num in numSet) {
                return true
            }

            numSet.add(num)
        }

        return false
    }
}