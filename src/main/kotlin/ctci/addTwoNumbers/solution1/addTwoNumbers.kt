package ctci.addTwoNumbers.solution1

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

class Solution {
    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        return addTwoNumbersHelper(l1, l2, 0)
    }

    fun addTwoNumbersHelper(l1: ListNode?, l2: ListNode?, carry: Int): ListNode? {
        if (l1 == null && l2 == null) {
            return if (carry > 0) ListNode(carry) else null
        }

        val sum = (l1?.`val` ?: 0) + (l2?.`val` ?: 0) + carry
        val currentNode = ListNode(sum % 10)
        val nextNode = addTwoNumbersHelper(l1?.next, l2?.next, sum / 10)

        currentNode.next = nextNode
        return currentNode
    }
}
