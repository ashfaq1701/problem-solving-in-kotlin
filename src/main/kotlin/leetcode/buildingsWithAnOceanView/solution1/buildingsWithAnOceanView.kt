package leetcode.buildingsWithAnOceanView.solution1

import java.util.Stack

class Solution {
    fun findBuildings(heights: IntArray): IntArray {
        val stack = Stack<Int>()

        for (i in heights.indices) {
            while (stack.isNotEmpty() && heights[stack.peek()] <= heights[i]) {
                stack.pop()
            }

            stack.push(i)
        }

        return stack.toList().toIntArray()
    }
}