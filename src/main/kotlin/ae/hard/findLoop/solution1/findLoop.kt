package ae.hard.findLoop.solution1

open class LinkedList(value: Int) {
    var value = value
    var next: LinkedList? = null
}

fun findLoop(head: LinkedList?): LinkedList? {
    var first = head?.next
    var second = head?.next?.next

    while (first != second) {
        first = first?.next
        second = second?.next?.next
    }

    first = head

    while (first != second) {
        first = first?.next
        second = second?.next
    }

    return first
}
