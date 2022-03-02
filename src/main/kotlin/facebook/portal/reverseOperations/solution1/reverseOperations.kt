package facebook.portal.reverseOperations.solution1

fun arrToLinkedList(arr: Array<Int>): Node {
    val preHead = Node(-1)
    var current = preHead

    for (element in arr) {
        current.next = Node(element)
        current = current.next!!
    }

    return preHead.next!!
}

fun printLinkedList(head: Node?) {
    var current = head
    while (current != null) {
        print("${current.data} -> ")
        current = current.next
    }
    println()
}

fun main() {
    printLinkedList(
        reverse(
            arrToLinkedList(
                arrayOf(1, 2, 8, 9, 12, 16)
            )
        )
    )

    printLinkedList(
        reverse(
            arrToLinkedList(
                arrayOf(2, 18, 24, 3, 5, 7, 9, 6, 12)
            )
        )
    )
}

class Node(val data: Int, var next: Node? = null)

fun reverse(head: Node?): Node? {
    val preHead = Node(-1)
    preHead.next = head

    var preStart: Node? = preHead

    while (preStart != null) {
        var start = preStart.next

        while (start != null && start.data % 2 == 1) {
            preStart = start
            start = start.next
        }

        var end = start
        var postEnd = end

        while (postEnd != null && (postEnd.data % 2) == 0) {
            end = postEnd
            postEnd = postEnd.next
        }

        var prev: Node? = null
        var current = start

        while (current != null && current != postEnd) {
            val next = current.next
            current.next = prev
            prev = current
            current = next
        }

        preStart?.next = end
        start?.next = postEnd

        preStart = postEnd
    }

    return preHead.next
}