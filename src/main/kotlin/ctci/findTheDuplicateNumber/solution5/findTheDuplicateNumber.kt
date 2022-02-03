package ctci.findTheDuplicateNumber.solution5

class Solution {
    fun findDuplicate(nums: IntArray): Int {
        // Simulate the first step in the while loop to make slow and fast different from the very beginning
        // At the first step slow will move forward by 1 element
        // while fast will move forward by 2 elements
        var slow = nums[nums[0]]
        var fast = nums[nums[nums[0]]]

        while (slow != fast) {
            slow = nums[slow]
            fast = nums[nums[fast]]
        }

        slow = nums[0]

        while (slow != fast) {
            slow = nums[slow]
            fast = nums[fast]
        }

        return slow
    }
}