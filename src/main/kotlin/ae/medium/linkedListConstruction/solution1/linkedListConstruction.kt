package ae.medium.linkedListConstruction.solution1

class Node(value: Int) {
    val value = value
    var prev: Node? = null
    var next: Node? = null
}

class DoublyLinkedList {
    private var head: Node? = null
    private var tail: Node? = null

    fun setHead(node: Node) {
        if (head == null) {
            head = node
            tail = node
            return
        }

        insertBefore(head!!, node)
    }

    fun setTail(node: Node) {
        if (tail == null) {
            setHead(node)
            return
        }

        insertAfter(tail!!, node)
    }

    fun insertBefore(node: Node, nodeToInsert: Node) {
        if (nodeToInsert == head && nodeToInsert == tail) {
            return
        }

        remove(nodeToInsert)
        nodeToInsert.prev = node.prev
        nodeToInsert.next = node

        if (node.prev == null) {
            head = nodeToInsert
        } else {
            node.prev!!.next = nodeToInsert
        }

        node.prev = nodeToInsert
    }

    fun insertAfter(node: Node, nodeToInsert: Node) {
        if (nodeToInsert == head && nodeToInsert == tail) {
            return
        }

        remove(nodeToInsert)
        nodeToInsert.next = node.next
        nodeToInsert.prev = node

        if (node.next == null) {
            tail = nodeToInsert
        } else {
            node.next!!.prev = nodeToInsert
        }

        node.next = nodeToInsert
    }

    fun insertAtPosition(position: Int, nodeToInsert: Node) {
        // Unnecessary because this logic will be handled by the next commented line
        if (position == 1) {
            setHead(nodeToInsert)
            return
        }

        var currentNode: Node? = head
        var currentPosition = 1

        while (currentNode != null && currentPosition != position) {
            currentNode = currentNode.next
            currentPosition += 1
        }

        if (currentNode == null) {
            setTail(nodeToInsert)
        } else {
            // If currentNode is head, then this will effectively insert the new node in the head
            insertBefore(currentNode, nodeToInsert)
        }
    }

    fun removeNodesWithValue(value: Int) {
        var current = head

        while (current != null) {
            val nextNode = current.next

            if (current.value == value) {
                remove(current)
            }

            current = nextNode
        }
    }

    fun remove(node: Node) {
        if (node == head) {
            head = head!!.next
        }

        if (node == tail) {
            tail = tail!!.prev
        }

        removeBindings(node)
    }

    fun containsNodeWithValue(value: Int): Boolean {
        var currentNode = head

        while (currentNode != null && currentNode.value != value) {
            currentNode = currentNode.next
        }

        return currentNode != null
    }

    fun getHead(): Node? { return this.head }

    fun getTail(): Node? { return this.tail }

    fun removeBindings(node: Node) {
        if (node.prev != null) {
            node.prev!!.next = node.next
        }

        if (node.next != null) {
            node.next!!.prev = node.prev
        }

        node.prev = null
        node.next = null
    }
}
