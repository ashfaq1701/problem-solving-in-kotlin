package ae.veryHard.linkedListPalindrome.solution2

open class LinkedList(value: Int) {
    var value = value
    var next: LinkedList? = null
}

fun linkedListPalindrome(head: LinkedList?): Boolean {
    return isPalindrome(head, head).outerNodesAreEqual
}

fun isPalindrome(leftNode: LinkedList?, rightNode: LinkedList?): LinkedListInfo {
    rightNode ?: return LinkedListInfo(true, leftNode)

    val (nextOuterCallsAreEqual, leftNodeToCompare) = isPalindrome(leftNode, rightNode.next)

    val outerNodesAreEqual = nextOuterCallsAreEqual && leftNodeToCompare!!.value == rightNode.value

    return LinkedListInfo(outerNodesAreEqual, leftNodeToCompare?.next)
}

data class LinkedListInfo(val outerNodesAreEqual: Boolean, val leftNodeToCompare: LinkedList?)