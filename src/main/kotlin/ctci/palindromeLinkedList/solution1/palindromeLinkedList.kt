package ctci.palindromeLinkedList.solution1

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

/**
 * a -> b -> c -> b -> a
 *
 * a, a    - a == a, return a.next = (true, null)
 * a, b    - b == b, return b.next = (true, a)
 * a, c    - c == c, return c.next = (true, b)
 * a, b    - b == b, return b.next = (true, c)
 * a, a    - a == a, return a.next = (true, b)
 * a, null -         return (true, a)
 */

/**
 * a -> b -> c -> c -> b -> a
 * a, a    - a == a, return a.next = (true, null)
 * a, b    - b == b, return b.next = (true, a)
 * a, c    - c == c, return c.next = (true, b)
 * a, c    - c == c, return c.next = (true, c)
 * a, b    - b == b, return b.next = (true, c)
 * a, a    - a == a, return a.next = (true, b)
 * a, null -         return (true, a)
 */

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