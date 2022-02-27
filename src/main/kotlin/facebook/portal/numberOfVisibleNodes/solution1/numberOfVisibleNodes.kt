package facebook.portal.numberOfVisibleNodes.solution1

import java.util.LinkedList

class Node(val data: Int, val left: Node?, val right: Node?)

fun main() {
    val root = Node(
        8,
        Node(
            3,
            Node(1, null, null),
            Node(
                6,
                Node(4, null, null),
                Node(7, null, null)
            )
        ),
        Node(
            10,
            null,
            Node(
                14,
                Node(13, null, null),
                null
            )
        )
    )
    println(visibleNodes(root))
}

fun visibleNodes(root: Node?): Int {
    root ?: return 0

    var levels = 0
    val queue = LinkedList<Node?>()
    queue.add(root)
    queue.add(null)

    while (queue.isNotEmpty()) {
        val current = queue.poll()

        if (current == null) {
            if (queue.isNotEmpty()) {
                queue.add(null)
            }

            levels += 1
            continue
        }

        if (current.left != null) {
            queue.add(current.left)
        }

        if (current.right != null) {
            queue.add(current.right)
        }
    }

    return levels
}