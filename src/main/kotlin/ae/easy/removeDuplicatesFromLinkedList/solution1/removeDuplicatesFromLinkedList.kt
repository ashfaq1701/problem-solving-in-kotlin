package ae.easy.removeDuplicatesFromLinkedList.solution1

open class LinkedList(value: Int) {
    var value = value
    var next: LinkedList? = null
}

fun removeDuplicatesFromLinkedList(linkedList: LinkedList): LinkedList {

    var slow: LinkedList? = linkedList
    var fast: LinkedList? = linkedList

    while (fast != null) {
        while (fast != null && fast.value == slow!!.value) {
            fast = fast.next
        }

        slow!!.next = fast
        slow = fast
    }

    return linkedList
}