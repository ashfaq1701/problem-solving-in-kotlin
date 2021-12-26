package ae.medium.sumOfLinkedLists.solution1

open class LinkedList(value: Int) {
    var value = value
    var next: LinkedList? = null
}

fun sumOfLinkedLists(linkedListOne: LinkedList, linkedListTwo: LinkedList): LinkedList {

    val preHead = LinkedList(-1)

    var currentNode = preHead
    var carry = 0

    var linkedListOneCurrent: LinkedList? = linkedListOne
    var linkedListTwoCurrent: LinkedList? = linkedListTwo

    while (linkedListOneCurrent != null || linkedListTwoCurrent != null || carry > 0) {
        val valueOne = linkedListOneCurrent?.value ?: 0
        val valueTwo = linkedListTwoCurrent?.value ?: 0
        val sumOfNodes = valueOne + valueTwo + carry

        val newValue = sumOfNodes % 10
        val newNode = LinkedList(newValue)
        currentNode.next = newNode
        currentNode = newNode

        carry = sumOfNodes / 10

        linkedListOneCurrent = linkedListOneCurrent?.next
        linkedListTwoCurrent = linkedListTwoCurrent?.next
    }

    return preHead.next!!
}
