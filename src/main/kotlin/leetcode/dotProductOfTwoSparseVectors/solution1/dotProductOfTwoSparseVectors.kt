package leetcode.dotProductOfTwoSparseVectors.solution1

class SparseVector(nums: IntArray) {

    val numMap: Map<Int, Int>

    init {
        numMap = arrayToMap(nums)
    }

    // Return the dotProduct of two sparse vectors
    fun dotProduct(vec: SparseVector): Int {

        val otherNumMap = vec.numMap
        var product = 0

        for ((idx, value) in numMap) {
            if (idx in otherNumMap) {
                product += value * otherNumMap[idx]!!
            }
        }

        return product
    }

    fun arrayToMap(nums: IntArray): Map<Int, Int> {
        val numMap = mutableMapOf<Int, Int>()

        for (idx in nums.indices) {
            if (nums[idx] != 0) {
                numMap[idx] = nums[idx]
            }
        }

        return numMap
    }
}