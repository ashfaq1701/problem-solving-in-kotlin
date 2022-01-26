package ctci.intersectionOfTwoLinkedLists.solution1

import kotlin.math.abs

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

class Solution {
    fun getIntersectionNode(headA:ListNode?, headB:ListNode?):ListNode? {
        val len1 = getLength(headA)
        val len2 = getLength(headB)

        val biggerList = if (len1 > len2) headA else headB
        val smallerList = if (biggerList == headA) headB else headA

        return getIntersectionHelper(biggerList, smallerList, abs(len1 - len2))
    }

    fun getIntersectionHelper(biggerList: ListNode?, smallerList: ListNode?, diff: Int): ListNode? {
        return if (diff > 0) {
            getIntersectionHelper(biggerList?.next, smallerList, diff - 1)
        } else {
            if (biggerList == smallerList) {
                biggerList
            } else {
                getIntersectionHelper(biggerList?.next, smallerList?.next, diff)
            }
        }
    }

    fun getLength(head: ListNode?): Int {
        var current = head
        var length = 0

        while (current != null) {
            current = current.next
            length += 1
        }

        return length
    }
}