package leetcode.mergeTwoSortedLists.solution1

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

class Solution {
    fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {
        var current1 = list1
        var current2 = list2

        val preHead = ListNode(-1)
        var current = preHead

        while (current1 != null || current2 != null) {
            val value1 = if (current1 != null) {
                current1.`val`
            } else Int.MAX_VALUE

            val value2 = if (current2 != null) {
                current2.`val`
            } else Int.MAX_VALUE

            val newNode = if (value1 < value2) {
                ListNode(value1).also {
                    current1 = current1!!.next
                }
            } else {
                ListNode(value2).also {
                    current2 = current2!!.next
                }
            }

            current.next = newNode
            current = current.next!!
        }

        return preHead.next
    }
}