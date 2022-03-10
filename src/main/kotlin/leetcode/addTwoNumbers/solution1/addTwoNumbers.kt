package leetcode.addTwoNumbers.solution1

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

class Solution {
    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        var carry = 0

        var currentL1 = l1
        var currentL2 = l2
        val preHead = ListNode(-1)
        var currentResult = preHead

        while (currentL1 != null || currentL2 != null) {
            var sum = carry

            if (currentL1 != null) {
                sum += currentL1.`val`
                currentL1 = currentL1.next
            }

            if (currentL2 != null) {
                sum += currentL2.`val`
                currentL2 = currentL2.next
            }

            val newNode = ListNode(sum % 10)
            currentResult.next = newNode
            currentResult = currentResult.next!!

            carry = sum / 10
        }

        if (carry > 0) {
            currentResult.next = ListNode(carry)
        }

        return preHead.next
    }
}