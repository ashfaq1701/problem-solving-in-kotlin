package ae.medium.breadthFirstSearch.solution1

import java.util.LinkedList

class Node(name: String) {
    val name: String = name
    val children = mutableListOf<Node>()

    fun breadthFirstSearch(): List<String> {
        val array = mutableListOf<String>()

        val queue = LinkedList<Node>()
        queue.add(this)

        while (queue.isNotEmpty()) {
            val currentNode = queue.poll()
            array.add(currentNode.name)

            for (child in currentNode.children) {
                queue.add(child)
            }
        }

        return array
    }
}
