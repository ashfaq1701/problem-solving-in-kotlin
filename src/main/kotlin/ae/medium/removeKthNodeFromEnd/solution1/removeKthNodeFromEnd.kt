package ae.medium.removeKthNodeFromEnd.solution1

open class LinkedList(value: Int) {
    var value = value
    var next: LinkedList? = null
}

fun removeKthNodeFromEnd(head: LinkedList, k: Int) {
    var first: LinkedList? = head
    var second: LinkedList? = head

    var counter = 1

    while (second != null && counter <= k) {
        second = second.next
        counter += 1
    }

    if (second == null) {
        head.value = head.next!!.value
        head.next = head.next!!.next
        return
    }

    while (second?.next != null) {
        first = first!!.next
        second = second.next
    }

    first!!.next = first.next!!.next
}
