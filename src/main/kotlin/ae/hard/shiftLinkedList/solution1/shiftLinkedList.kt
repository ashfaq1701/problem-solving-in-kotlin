package ae.hard.shiftLinkedList.solution1

import kotlin.math.abs

open class LinkedList(value: Int) {
    var value = value
    var next: LinkedList? = null
}

// 0 -> 1 -> 2 -> 3 -> 4 -> 5, k =  2
// 4 -> 5 -> 0 -> 1 -> 2 -> 3
// 0 -> 1 -> 2 -> 3 -> 4 -> 5, k = -2
// 2 -> 3 -> 4 -> 5 -> 0 -> 1
fun shiftLinkedList(head: LinkedList, k: Int): LinkedList {
    var tail = head
    var listLength = 1

    while (tail.next != null) {
        tail = tail.next!!
        listLength += 1
    }

    val offset = abs(k) % listLength

    if (offset == 0) return head

    val newTailPosition = if (k > 0) listLength - offset else offset

    var newTail = head
    for (i in 1 until newTailPosition) {
        newTail = newTail.next!!
    }

    val newHead = newTail.next!!
    newTail.next = null
    tail.next = head

    return newHead
}
