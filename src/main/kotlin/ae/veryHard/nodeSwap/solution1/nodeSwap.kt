package ae.veryHard.nodeSwap.solution1

open class LinkedList(value: Int) {
    var value = value
    var next: LinkedList? = null
}

fun nodeSwap(head: LinkedList?): LinkedList {
    return recurseHelper(head)!!
}

fun recurseHelper(head: LinkedList?): LinkedList? {
    if (head?.next == null) {
        return head
    }

    val firstNode = head
    val secondNode = head.next

    firstNode.next = recurseHelper(secondNode?.next)
    secondNode?.next = firstNode

    return secondNode
}