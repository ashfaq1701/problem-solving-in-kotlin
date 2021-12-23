package ae.veryHard.linkedListPalindrome.solution1

open class LinkedList(value: Int) {
    var value = value
    var next: LinkedList? = null
}

fun linkedListPalindrome(head: LinkedList?): Boolean {
    var slow = head
    var fast = head

    // When fast itself is past last element (null)
    // Or it is the last element (it's next is null)
    // Then break the loop
    // fast != null && fast.next != null
    while (fast?.next != null) {
        slow = slow!!.next
        fast = fast.next!!.next
    }

    var current = head
    var revCurrent = reverseLinkedList(slow)

    // current can have more element
    // In other word for odd length palindromes
    // current can have one more element than revCurrent.
    // That one more element will have no pair
    while (revCurrent != null) {
        if (current!!.value != revCurrent.value) {
            return  false
        }

        current = current.next
        revCurrent = revCurrent.next
    }

    return true
}

fun reverseLinkedList(node: LinkedList?): LinkedList? {
    var prev: LinkedList? = null
    var current = node

    while (current != null) {
        val next = current.next
        current.next = prev
        prev = current
        current = next
    }

    return prev
}