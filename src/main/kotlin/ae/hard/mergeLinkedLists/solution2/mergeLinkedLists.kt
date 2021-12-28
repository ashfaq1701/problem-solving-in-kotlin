package ae.hard.mergeLinkedLists.solution2

open class LinkedList(value: Int) {
    var value = value
    var next: LinkedList? = null
}

fun mergeLinkedLists(headOne: LinkedList, headTwo: LinkedList): LinkedList {
    recursiveMerge(headOne, headTwo, null)
    return if (headOne.value < headTwo.value) headOne else headTwo
}

fun recursiveMerge(p1: LinkedList?, p2: LinkedList?, p1Prev: LinkedList?) {
    if (p1 == null) {
        p1Prev?.next = p2
        return
    }

    if (p2 == null) {
        return
    }

    if (p1.value < p2.value) {
        recursiveMerge(p1.next, p2, p1)
    } else {
        if (p1Prev != null) {
            p1Prev.next = p2
        }

        val nextP2 = p2.next
        p2.next = p1
        recursiveMerge(p1, nextP2, p2)
    }
}
