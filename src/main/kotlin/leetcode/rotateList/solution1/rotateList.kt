package leetcode.rotateList.solution1

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

class Solution {
    fun rotateRight(head: ListNode?, k: Int): ListNode? {
        if (k == 0 || head == null) return head

        val len = getLength(head)
        val kRounded = k % len

        if (kRounded == 0) return head

        val prehead = ListNode(-1)
        prehead.next = head
        val currentTail = getTail(head)

        var newTail = prehead
        var currentPos = 0
        while (currentPos < len - kRounded) {
            newTail = newTail.next!!
            currentPos += 1
        }

        val newHead = newTail.next
        newTail.next = null
        currentTail?.next = head
        prehead.next = newHead

        return prehead.next
    }

    fun getTail(head: ListNode?): ListNode? {
        var current = head

        while (current?.next != null) {
            current = current.next
        }

        return current
    }

    fun getLength(head: ListNode?): Int {
        var len = 0
        var current = head

        while (current != null) {
            len += 1
            current = current.next
        }

        return len
    }
}