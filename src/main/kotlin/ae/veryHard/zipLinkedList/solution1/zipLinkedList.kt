package ae.veryHard.zipLinkedList.solution1

// This is an input class. Do not edit.
open class LinkedList(value: Int) {
    var value = value
    var next: LinkedList? = null
}

fun zipLinkedList(linkedList: LinkedList): LinkedList {
    // If the linked list contains less than 3 elements
    // then there are no point to zip.
    if (linkedList.next?.next == null) {
        return linkedList
    }

    val firstHalfHead = linkedList
    val secondHalfHead = splitLinkedList(linkedList)
    val secondHalfHeadReversed = reverseLinkedList(secondHalfHead)

    return interweaveLinkedList(firstHalfHead, secondHalfHeadReversed)!!
}

fun splitLinkedList(head: LinkedList): LinkedList? {
    var slow: LinkedList? = head
    var fast: LinkedList? = head

    while (fast?.next?.next != null) {
        slow = slow?.next
        fast = fast.next?.next
    }

    val secondHalfHead = slow!!.next
    slow.next = null

    return secondHalfHead
}

fun reverseLinkedList(head: LinkedList?): LinkedList? {
    var prev: LinkedList? = null
    var current: LinkedList? = head

    while (current != null) {
        val next = current.next
        current.next = prev
        prev = current
        current = next
    }

    return prev
}

fun interweaveLinkedList(ll1: LinkedList?, ll2: LinkedList?): LinkedList? {
    val ll1Head = ll1

    var ll1Current = ll1
    var ll2Current = ll2

    while (ll2Current != null) {
        val ll1Next = ll1Current?.next
        val ll2Next = ll2Current.next

        ll1Current?.next = ll2Current
        ll2Current.next = ll1Next

        ll1Current = ll1Next
        ll2Current = ll2Next
    }

    return ll1Head
}
