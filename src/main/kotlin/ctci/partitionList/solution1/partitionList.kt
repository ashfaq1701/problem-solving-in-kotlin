package ctci.partitionList.solution1

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

class Solution {
    fun partition(head: ListNode?, x: Int): ListNode? {
        val lesserNodes = ListNodes()
        val otherNodes = ListNodes()

        var currentNode = head

        while (currentNode != null) {
            val nextNode = currentNode.next

            if (currentNode.`val` < x) {
                growLinkedList(currentNode, lesserNodes)
            } else {
                growLinkedList(currentNode, otherNodes)
            }

            currentNode = nextNode
        }

        return joinLinkedLists(lesserNodes, otherNodes)
    }

    fun growLinkedList(currentNode: ListNode, nodes: ListNodes) {
        currentNode.next = null

        if (nodes.headNode == null) {
            nodes.headNode = currentNode
            nodes.tailNode = currentNode
        } else {
            nodes.tailNode!!.next = currentNode
            nodes.tailNode = currentNode
        }
    }

    fun joinLinkedLists(lesserNodes: ListNodes, otherNodes: ListNodes): ListNode? {
        if (lesserNodes.tailNode != null) {
            lesserNodes.tailNode!!.next = otherNodes.headNode
        }

        return if (lesserNodes.headNode != null) lesserNodes.headNode else otherNodes.headNode
    }
}

class ListNodes(var headNode: ListNode? = null, var tailNode: ListNode? = null)