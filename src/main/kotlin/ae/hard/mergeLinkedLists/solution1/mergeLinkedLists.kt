package ae.hard.mergeLinkedLists.solution1

open class LinkedList(value: Int) {
    var value = value
    var next: LinkedList? = null
}

fun mergeLinkedLists(headOne: LinkedList, headTwo: LinkedList): LinkedList {
    var p1: LinkedList? = headOne
    var p2: LinkedList? = headTwo
    var p1Prev: LinkedList? = null

    while (p1 != null && p2 != null) {
        if (p1.value < p2.value) {
            p1Prev = p1
            p1 = p1.next
        } else {
            if (p1Prev != null) {
                p1Prev.next = p2
            }

            p1Prev = p2
            p2 = p2.next
            p1Prev.next = p1
        }
    }

    if (p1 == null) {
        p1Prev?.next = p2
    }

    return if (headOne.value < headTwo.value) headOne else headTwo
}
