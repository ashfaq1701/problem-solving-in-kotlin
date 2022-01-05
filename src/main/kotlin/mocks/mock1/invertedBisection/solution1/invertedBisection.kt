package mocks.mock1.invertedBisection.solution1

fun invertedBisection(head: LinkedList): LinkedList {
    val length = findLength(head)

    if (length <= 3) return head

    val firstHalfTail = getMiddleNode(head, length)

    val middleNode = if (length % 2 == 0) null else firstHalfTail.next
    val secondHalfHead = if (length % 2 == 0) firstHalfTail.next!! else firstHalfTail.next!!.next!!

    firstHalfTail.next = null

    reverseLinkedList(head)
    val reversedSecondHalfHead = reverseLinkedList(secondHalfHead)

    if (middleNode != null) {
        head.next = middleNode
        middleNode.next = reversedSecondHalfHead
    } else {
        head.next = reversedSecondHalfHead
    }

    return firstHalfTail
}

// This is an input class. Do not edit.
open class LinkedList(value: Int) {
    var value = value
    var next: LinkedList? = null
}

fun findLength(head: LinkedList): Int {
    var current: LinkedList? = head
    var length = 0

    while (current != null) {
        current = current.next
        length += 1
    }

    return length
}

fun getMiddleNode(head: LinkedList, len: Int): LinkedList {
    val halfLen = len / 2
    var currentPosition = 1
    var currentNode = head

    while (currentPosition < halfLen) {
        currentNode = currentNode.next!!
        currentPosition += 1
    }

    return currentNode
}

fun reverseLinkedList(head: LinkedList): LinkedList {
    var prev: LinkedList? = null
    var current: LinkedList? = head

    while (current != null) {
        val next = current.next
        current.next = prev
        prev = current
        current = next
    }

    return prev!!
}
