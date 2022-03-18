package leetcode.insertIntoASortedCircularLinkedList.solution1

class Node(var `val`: Int) {
    var next: Node? = null
}

class Solution {
    fun insert(head: Node?, insertVal: Int): Node? {
        val node = Node(insertVal)

        if (head == null) {
            node.next = node
            return node
        }

        var prev = head!!
        var current = prev.next!!

        while (current != head) {
            var toInsert = false

            if (prev.`val` <= insertVal  && insertVal <= current.`val`) {
                toInsert = true
            } else if (prev.`val` > current.`val`) {
                if (insertVal >= prev.`val` || insertVal <= current.`val`) {
                    toInsert = true
                }
            }

            if (toInsert) {
                prev.next = node
                node.next = current
                return head
            }

            prev = current
            current = current.next!!
        }

        prev.next = node
        node.next = current
        return head
    }
}