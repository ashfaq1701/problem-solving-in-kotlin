package ctci.findTheDuplicateNumber.solution3

class Solution {
    fun findDuplicate(nums: IntArray): Int {
        var cur = 0

        while (cur != nums[cur]) {
            nums[cur] = cur.also {
                cur = nums[cur]
            }
        }

        return cur
    }
}