package ae.medium.nextGreaterElement.solution1

import java.util.Stack

fun nextGreaterElement(array: MutableList<Int>): MutableList<Int> {
    val result = MutableList(array.size) { -1 }
    val stack = Stack<Int>()

    for (i in 0 until 2 * array.size) {
        val circularIdx = i % array.size

        while (stack.isNotEmpty() && array[circularIdx] > array[stack.peek()]) {
            val top = stack.pop()
            result[top] = array[circularIdx]
        }

        stack.push(circularIdx)
    }

    return result
}
