package leetcode.lowestCommonAncestorOfABinaryTreeIII.solution1

import kotlin.math.abs

class Node(var `val`: Int) {
    var left: Node? = null
    var right: Node? = null
    var parent: Node? = null
}

class Solution {

    fun getDepth(node: Node?): Int {
        var current = node
        var depth = 0

        while (current != null) {
            current = current.parent
            depth += 1
        }

        return depth
    }

    fun getNodeAtKLevelUp(node: Node, k: Int): Node {
        var currentDiff = k
        var currentNode = node

        while (currentDiff > 0) {
            currentNode = currentNode.parent!!
            currentDiff -= 1
        }

        return currentNode
    }

    fun getCommonParent(p: Node?, q: Node?): Node? {
        var currentP = p
        var currentQ = q

        while (currentP != null && currentQ != null && currentP != currentQ) {
            currentP = currentP.parent
            currentQ = currentQ.parent
        }

        return currentP
    }

    fun lowestCommonAncestor(p: Node?, q: Node?): Node? {
        val pDepth = getDepth(p)
        val qDepth = getDepth(q)

        val diff = abs(pDepth - qDepth)

        val pSameLevel = if (pDepth > qDepth) {
            getNodeAtKLevelUp(p!!, diff)
        } else {
            p
        }

        val qSameLevel = if (qDepth > pDepth) {
            getNodeAtKLevelUp(q!!, diff)
        } else {
            q
        }

        return getCommonParent(pSameLevel, qSameLevel)
    }
}


