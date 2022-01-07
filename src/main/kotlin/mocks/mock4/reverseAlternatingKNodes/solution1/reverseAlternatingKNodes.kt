package mocks.mock4.reverseAlternatingKNodes.solution1

fun reverseAlternatingKNodes(head: LinkedList, k: Int): LinkedList {
    val preHead = LinkedList(-1)
    preHead.next = reverseAlternatingKNodesHelper(head, k, true)
    return preHead.next!!
}

fun reverseAlternatingKNodesHelper(head: LinkedList?, k: Int, toReverse: Boolean): LinkedList? {
    if (head == null) return null

    var currentNode: LinkedList = head
    var currentPosition = 1

    while (currentPosition < k && currentNode.next != null) {
        currentNode = currentNode.next!!
        currentPosition += 1
    }

    val nextNode = currentNode.next
    currentNode.next = null

    val modifiedTail = if (toReverse) head else currentNode

    val modifiedHead = if (toReverse) {
        reverseLinkedList(head)
    } else {
        head
    }

    modifiedTail.next = reverseAlternatingKNodesHelper(nextNode, k, !toReverse)

    return modifiedHead
}

fun reverseLinkedList(head: LinkedList): LinkedList {
    var current: LinkedList? = head
    var prev: LinkedList? = null

    while (current != null) {
        val next = current.next
        current.next = prev
        prev = current
        current = next
    }

    return prev!!
}

// This is an input class. Do not edit.
open class LinkedList(value: Int) {
    var value = value
    var next: LinkedList? = null
}
