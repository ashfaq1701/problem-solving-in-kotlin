package leetcode.kthSmallestElementInASortedMatrix.solution1

import java.util.*
import kotlin.math.min

data class Node(val item: Int, val row: Int, val col: Int)

class Solution {
    fun kthSmallest(matrix: Array<IntArray>, k: Int): Int {
        val heap = PriorityQueue<Node> { n1, n2 ->
            n1.item.compareTo(n2.item)
        }

        for (r in 0 until min(matrix.size, k)) {
            heap.add(Node(matrix[r][0], r, 0))
        }

        var elementsLeft = k
        var lastElement = -1
        while (elementsLeft > 0) {
            val (element, r, c) = heap.poll()
            elementsLeft -= 1

            if (c < matrix.lastIndex) {
                heap.add(Node(matrix[r][c + 1], r, c + 1))
            }
            lastElement = element
        }

        return lastElement
    }
}