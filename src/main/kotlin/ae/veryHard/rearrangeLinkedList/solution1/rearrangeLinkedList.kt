package ae.veryHard.rearrangeLinkedList.solution1

open class LinkedList(value: Int) {
    var value = value
    var next: LinkedList? = null
}

fun rearrangeLinkedList(head: LinkedList, k: Int): LinkedList {
    var smallerListHead: LinkedList? = null
    var smallerListTail: LinkedList? = null
    var equalListHead: LinkedList? = null
    var equalListTail: LinkedList? = null
    var greaterListHead: LinkedList? = null
    var greaterListTail: LinkedList? = null

    var current: LinkedList? = head

    while (current != null) {

        if (current.value < k) {
            val smallerListNodes = growLinkedList(smallerListHead, smallerListTail, current)
            smallerListHead = smallerListNodes.first
            smallerListTail = smallerListNodes.second
        } else if (current.value == k) {
            val equalListNodes = growLinkedList(equalListHead, equalListTail, current)
            equalListHead = equalListNodes.first
            equalListTail = equalListNodes.second
        } else {
            val greaterListNodes = growLinkedList(greaterListHead, greaterListTail, current)
            greaterListHead = greaterListNodes.first
            greaterListTail = greaterListNodes.second
        }

        val prev = current
        current = current.next
        prev.next = null
    }

    val (firstConnectedListHead, firstConnectedListTail) = connectLinkedLists(smallerListHead, smallerListTail, equalListHead, equalListTail)
    val (secondConnectedListHead, _) = connectLinkedLists(firstConnectedListHead, firstConnectedListTail, greaterListHead, greaterListTail)

    return secondConnectedListHead!!
}

fun growLinkedList(head: LinkedList?, tail: LinkedList?, node: LinkedList?): Pair<LinkedList?, LinkedList?> {
    var newHead: LinkedList? = head
    val newTail: LinkedList? = node

    if (newHead == null) {
        newHead = node
    }

    if (tail != null) {
        tail.next = newTail
    }

    return newHead to newTail
}

fun connectLinkedLists(firstHead: LinkedList?, firstTail: LinkedList?, secondHead: LinkedList?, secondTail: LinkedList?): Pair<LinkedList?, LinkedList?> {
    val mergedHead = firstHead ?: secondHead
    val mergedTail = secondTail ?: firstTail

    if (firstTail != null) {
        firstTail.next = secondHead
    }

    return mergedHead to mergedTail
}