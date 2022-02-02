package ctci.mergeSortedArray.solution2

class Solution {
    fun merge(nums1: IntArray, m: Int, nums2: IntArray, n: Int): Unit {
        val nums1Copy = nums1.toList()

        var p1 = 0
        var p2 = 0

        for (p in 0 until m + n) {
            if (p2 >= n || (p1 < m && nums1Copy[p1] < nums2[p2])) {
                nums1[p] = nums1Copy[p1]
                p1 += 1
            } else {
                nums1[p] = nums2[p2]
                p2 += 1
            }
        }
    }
}