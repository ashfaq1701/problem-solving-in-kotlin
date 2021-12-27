package ae.medium.nextGreaterElement.solution2

import java.util.Stack

fun nextGreaterElement(array: MutableList<Int>): MutableList<Int> {
    val result = MutableList(array.size) { -1 }
    val stack = Stack<Int>()

    for (i in (2 * array.size - 1) downTo 0) {
        val circularIdx = i % array.size

        while (stack.isNotEmpty()) {
            if (stack.peek() <= array[circularIdx]) {
                stack.pop()
            } else {
                result[circularIdx] = stack.peek()
                break
            }
        }

        stack.push(array[circularIdx])
    }

    return result
}
