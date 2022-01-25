package ctci.deleteNodeInALinkedList.solution1

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

class Solution {
    fun deleteNode(node: ListNode?) {
        node?.`val` = node?.next!!.`val`
        node.next = node.next!!.next
    }
}