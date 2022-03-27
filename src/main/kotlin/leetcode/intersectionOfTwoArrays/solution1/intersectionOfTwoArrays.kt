package leetcode.intersectionOfTwoArrays.solution1

class Solution {
    fun intersection(nums1: IntArray, nums2: IntArray): IntArray {
        val nums1Set = nums1.toSet()

        val nums2Set = mutableSetOf<Int>()
        for (num in nums2) {
            if (num in nums1Set) {
                nums2Set.add(num)
            }
        }

        return nums2Set.toIntArray()
    }
}