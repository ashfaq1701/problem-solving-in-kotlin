package leetcode.intersectionOfTwoArraysII.solution1

import kotlin.math.min

class Solution {
    fun intersect(nums1: IntArray, nums2: IntArray): IntArray {
        val nums1Counts = getCharCounts(nums1)
        val nums2Counts = getCharCounts(nums2)

        val result = mutableListOf<Int>()

        for ((num2, count2) in nums2Counts) {
            if (num2 in nums1Counts) {
                val minCount = min(count2, nums1Counts[num2]!!)
                for (i in 0 until minCount) {
                    result.add(num2)
                }
            }
        }
        return result.toIntArray()
    }

    fun getCharCounts(arr: IntArray): Map<Int, Int> {
        val counts = mutableMapOf<Int, Int>()

        for (num in arr) {
            if (num !in counts) {
                counts[num] = 0
            }

            counts[num] = counts[num]!! + 1
        }

        return counts
    }
}