package leetcode.reorderList.solution1

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

class Solution {
    fun reorderList(head: ListNode?): Unit {
        if (head == null) return

        val secondHalfHead = splitList(head)
        if (secondHalfHead == null) return
        val revHead = reverseLinkedList(secondHalfHead)
        interweaveLinkedList(head, revHead)
    }

    private fun splitList(head: ListNode): ListNode? {
        var slow = head.next
        var fast = head.next?.next

        while (fast?.next?.next != null) {
            slow = slow?.next
            fast = fast?.next?.next
        }

        return slow?.next.also {
            slow?.next = null
        }
    }

    private fun reverseLinkedList(head: ListNode): ListNode {
        var prev: ListNode? = null
        var current: ListNode? = head

        while (current != null) {
            val next = current.next
            current.next = prev
            prev = current
            current = next
        }

        return prev!!
    }

    fun interweaveLinkedList(head1: ListNode, head2: ListNode) {
        var current1: ListNode? = head1
        var current2: ListNode? = head2

        while (current2 != null) {
            val current1Next = current1!!.next
            val current2Next = current2.next

            current1!!.next = current2
            current2.next = current1Next

            current1 = current1Next
            current2 = current2Next
        }
    }
}