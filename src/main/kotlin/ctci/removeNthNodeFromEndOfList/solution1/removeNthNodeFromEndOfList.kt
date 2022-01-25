package ctci.removeNthNodeFromEndOfList.solution1

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

class Solution {
    fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
        val preHead = ListNode(-1)
        preHead.next = head

        var slow: ListNode? = preHead
        var fast: ListNode? = preHead

        for (currentPosition in 1 .. n) {
            if (fast == null) break
            fast = fast.next
        }

        while (fast?.next != null) {
            slow = slow?.next
            fast = fast.next
        }

        slow?.next = slow?.next?.next

        return preHead.next
    }
}