package ctci.findTheDuplicateNumber.solution4

class Solution {
    // We need to find the first number for which "it's less than or equal to element count in the array" > "the value of itself"
    fun findDuplicate(nums: IntArray): Int {
        var left = 1
        var right = nums.size - 1

        var duplicate = -1

        while (left <= right) {
            val mid = (left + right) / 2

            var lessThanOrEqualCount = 0
            for (num in nums) {
                if (num <= mid) {
                    lessThanOrEqualCount += 1
                }
            }

            if (lessThanOrEqualCount > mid) {
                duplicate = mid
                right = mid - 1
            } else {
                left = mid + 1
            }
        }

        return duplicate
    }
}