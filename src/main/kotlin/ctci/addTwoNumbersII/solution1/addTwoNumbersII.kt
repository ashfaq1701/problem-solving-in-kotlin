package ctci.addTwoNumbersII.solution1

import ctci.addTwoNumbers.solution1.ListNode

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

class Solution {
    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        val len1 = getLength(l1)
        val len2 = getLength(l2)

        val prehead = ListNode(1)

        prehead.next = if (len1 > len2) addTwoNumbersHelper(l1, l2, len1 - len2) else addTwoNumbersHelper(l2, l1, len2 - len1)

        if (prehead.next!!.`val` > 9) {
            prehead.next!!.`val` %= 10
            return prehead
        }

        return prehead.next
    }

    fun addTwoNumbersHelper(l1: ListNode?, l2: ListNode?, offset: Int): ListNode? {
        if (l1 == null || l2 == null) return null

        val currentNode = if (offset == 0) ListNode(l1.`val` + l2.`val`) else ListNode(l1.`val`)
        val nextNode = if (offset == 0) {
            addTwoNumbersHelper(l1.next, l2.next, offset)
        } else {
            addTwoNumbersHelper(l1.next, l2, offset - 1)
        }

        if (nextNode != null && nextNode.`val` > 9) {
            nextNode.`val` %= 10
            currentNode.`val` += 1
        }

        currentNode.next = nextNode
        return currentNode
    }

    fun getLength(l1: ListNode?): Int {
        var length = 0
        var current = l1

        while (current != null) {
            length += 1
            current = current.next
        }

        return length
    }
}