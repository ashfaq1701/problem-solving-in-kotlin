package ctci.palindromeLinkedList.solution1

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

class Solution {
    fun isPalindrome(head: ListNode?): Boolean {
        return isPalindromeRecursive(head, head).first
    }

    fun isPalindromeRecursive(leftNode: ListNode?, rightNode: ListNode?): Pair<Boolean, ListNode?> {
        if (rightNode == null) return true to leftNode

        val (areOuterNodesEqual, leftNodeToCompare) = isPalindromeRecursive(leftNode, rightNode.next)

        val areNodesEqual = areOuterNodesEqual && leftNodeToCompare!!.`val` == rightNode.`val`

        return areNodesEqual to leftNodeToCompare?.next
    }
}