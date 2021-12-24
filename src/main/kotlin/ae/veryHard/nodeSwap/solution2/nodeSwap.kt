package ae.veryHard.nodeSwap.solution2

open class LinkedList(value: Int) {
    var value = value
    var next: LinkedList? = null
}

fun nodeSwap(head: LinkedList): LinkedList {
    val preHead = LinkedList(-1)
    preHead.next = head

    var prev: LinkedList? = preHead

    while (prev?.next?.next != null) {
        val firstNode = prev.next!!
        val secondNode = prev.next?.next!!

        firstNode.next = secondNode.next
        secondNode.next = firstNode
        prev.next = secondNode

        prev = firstNode
    }

    return preHead.next!!
}
