package facebook.recommendation.cycleDetection.solution1

class SinglyLinkedListNode(val value: Int) {
    var next: SinglyLinkedListNode? = null
}

fun hasCycle(head: SinglyLinkedListNode?): Boolean {
    if (head == null) return false

    var slow: SinglyLinkedListNode? = head.next ?: return false
    var fast = head.next!!.next

    while (fast?.next != null) {
        if (slow == fast) return true

        slow = slow!!.next
        fast = fast.next!!.next
    }

    return false
}