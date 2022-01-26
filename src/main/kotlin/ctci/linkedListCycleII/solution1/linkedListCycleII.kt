package ctci.linkedListCycleII.solution1

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

class Solution {
    fun detectCycle(head: ListNode?): ListNode? {
        var slow = head?.next
        var fast = head?.next?.next

        while (fast != null && slow != fast) {
            slow = slow?.next
            fast = fast.next?.next
        }

        if (fast == null) return null

        var first = head
        var second = fast

        while (first != second) {
            first = first?.next
            second = second?.next
        }

        return first
    }
}