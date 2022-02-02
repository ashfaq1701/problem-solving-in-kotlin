package ctci.mergeSortedArray.solution1

class Solution {
    fun merge(nums1: IntArray, m: Int, nums2: IntArray, n: Int): Unit {
        val nums1Copy = nums1.toList()

        var i = 0
        var j = 0
        var k = 0

        while (i < m && j < n) {
            if (nums1Copy[i] < nums2[j]) {
                nums1[k] = nums1Copy[i]
                i += 1
            } else {
                nums1[k] = nums2[j]
                j += 1
            }

            k += 1
        }

        while (i < m) {
            nums1[k] = nums1Copy[i]
            i += 1
            k += 1
        }

        while (j < n) {
            nums1[k] = nums2[j]
            j += 1
            k += 1
        }
    }
}