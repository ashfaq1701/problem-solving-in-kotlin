package facebook.portal.nodesInASubtree.solution1

import java.util.LinkedList

class Node(val `val`: Int, val children: List<Node>)

class Query(val u: Int, val c: Char)

fun main() {
    val root = Node(1, listOf(
        Node(2, listOf(
            Node(4, listOf()),
            Node(5, listOf())
        )),
        Node(3, listOf(
            Node(6, listOf())
        )),
        Node(7, listOf())
    ))
    val queries = listOf(Query(1, 'a'), Query(2, 'b'), Query(3, 'a'))
    println(countOfNodes(root, queries, "abaacab"))
}

fun countOfNodes(root: Node?, queries: List<Query>, s: String): List<Int> {
    if (root == null) return listOf()

    return queries.map { query ->
        var beginNode: Node? = null
        val qu1 = LinkedList<Node>()
        qu1.add(root)

        while (qu1.isNotEmpty()) {
            val current = qu1.poll()

            if (current.`val` == query.u) {
                beginNode = current
                break
            }

            for (child in current.children) {
                qu1.add(child)
            }
        }

        if (beginNode == null) return@map 0

        var childCount = 0

        val qu2 = LinkedList<Node>()
        qu2.add(beginNode)

        while (qu2.isNotEmpty()) {
            val current = qu2.poll()

            if (s[current.`val` - 1] == query.c) {
                childCount += 1
            }

            for (child in current.children) {
                qu2.add(child)
            }
        }

        childCount
    }
}