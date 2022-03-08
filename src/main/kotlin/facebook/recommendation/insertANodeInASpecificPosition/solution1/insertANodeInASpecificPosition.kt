package facebook.recommendation.insertANodeInASpecificPosition.solution1

class SinglyLinkedListNode(val value: Int) {
    var next: SinglyLinkedListNode? = null
}

fun insertNodeAtPosition(llist: SinglyLinkedListNode?, data: Int, position: Int): SinglyLinkedListNode? {
    val prehead = SinglyLinkedListNode(-1)
    prehead.next = llist

    var current: SinglyLinkedListNode? = prehead
    var currentPosition = 0
    while (current?.next != null && currentPosition < position) {
        current = current.next
        currentPosition += 1
    }

    val nextNode = current!!.next
    val newNode = SinglyLinkedListNode(data)
    current.next = newNode
    newNode.next = nextNode

    return prehead.next
}