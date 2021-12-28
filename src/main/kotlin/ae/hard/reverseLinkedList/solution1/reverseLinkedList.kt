package ae.hard.reverseLinkedList.solution1

open class LinkedList(value: Int) {
    var value = value
    var next: LinkedList? = null
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