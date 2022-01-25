package ctci.removeDuplicatesFromAnUnsortedLinkedList.solution1

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

class Solution {
    fun deleteDuplicatesUnsorted(head: ListNode?): ListNode? {
        val freq = createFrequencyTable(head)

        val preHead = ListNode(-1)
        preHead.next = head

        var slow: ListNode? = preHead
        var fast = head

        while (fast != null) {
            while (fast != null && freq[fast.`val`]!! > 1) {
                fast = fast.next
            }

            slow?.next = fast
            slow = fast
            fast = fast?.next
        }

        return preHead.next
    }

    fun createFrequencyTable(head: ListNode?): Map<Int, Int> {
        val freq = mutableMapOf<Int, Int>()

        var current = head

        while (current != null) {
            if (current.`val` !in freq) {
                freq[current.`val`] = 0
            }

            freq[current.`val`] = freq[current.`val`]!! + 1
            current = current.next
        }

        return freq
    }
}