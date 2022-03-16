package leetcode.validateStackSequences.solution1

import java.util.*

class Solution {
    fun validateStackSequences(pushed: IntArray, popped: IntArray): Boolean {
        val stack = Stack<Int>()
        var popIdx = 0

        for (numToPush in pushed) {
            stack.push(numToPush)

            while (stack.isNotEmpty() && popIdx < popped.size && popped[popIdx] == stack.peek()) {
                stack.pop()
                popIdx += 1
            }
        }

        return popIdx == popped.size
    }
}