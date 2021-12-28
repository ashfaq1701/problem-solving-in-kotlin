package ae.medium.youngestCommonAncestor.solution1

class AncestralTree(name: Char) {
    val name = name
    var ancestor: AncestralTree? = null
}

fun getYoungestCommonAncestor(topAncestor: AncestralTree, descendantOne: AncestralTree, descendantTwo: AncestralTree): AncestralTree? {
    val depthOne = getDepth(descendantOne, topAncestor)
    val depthTwo = getDepth(descendantTwo, topAncestor)
    return if (depthOne > depthTwo) {
        backtrackAncestralTree(descendantOne, descendantTwo, depthOne - depthTwo)
    } else {
        backtrackAncestralTree(descendantTwo, descendantOne, depthTwo - depthOne)
    }
}

fun getDepth(descendant: AncestralTree, topAncestor: AncestralTree): Int {
    var depth = 0
    var descendantNode = descendant

    while (descendantNode != topAncestor) {
        depth += 1
        descendantNode = descendantNode.ancestor!!
    }

    return depth
}

fun backtrackAncestralTree(lowerDescendant: AncestralTree, higherDescendant: AncestralTree, diff: Int): AncestralTree? {
    var diffValue = diff

    var lowerDescendantNode: AncestralTree?  = lowerDescendant
    var higherDescendantNode: AncestralTree? = higherDescendant

    while (diffValue > 0) {
        lowerDescendantNode = lowerDescendantNode!!.ancestor
        diffValue -= 1
    }

    while (lowerDescendantNode != higherDescendantNode) {
        lowerDescendantNode = lowerDescendantNode?.ancestor
        higherDescendantNode = higherDescendantNode?.ancestor
    }

    return lowerDescendantNode
}
