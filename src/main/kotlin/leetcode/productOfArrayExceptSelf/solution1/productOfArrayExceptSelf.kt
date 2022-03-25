package leetcode.productOfArrayExceptSelf.solution1

class Solution {
    fun productExceptSelf(nums: IntArray): IntArray {
        val leftProducts = mutableListOf<Int>()
        var runningLeftProduct = 1
        for (num in nums) {
            leftProducts.add(runningLeftProduct)
            runningLeftProduct *= num
        }

        val rightProducts = MutableList<Int>(nums.size) { 0 }
        var runningRightProduct = 1
        for (idx in nums.lastIndex downTo 0) {
            rightProducts[idx] = runningRightProduct
            runningRightProduct *= nums[idx]
        }

        val productsExceptSelf = mutableListOf<Int>()
        for (i in nums.indices) {
            productsExceptSelf.add(leftProducts[i] * rightProducts[i])
        }

        return productsExceptSelf.toIntArray()
    }
}