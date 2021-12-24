package ae.easy.findClosestValueInBST.solution1

import kotlin.math.abs

open class BST(value: Int) {
    var value = value
    var left: BST? = null
    var right: BST? = null
}

fun findClosestValueInBst(tree: BST, target: Int): Int {
    return helper(tree, target, tree.value)
}

fun helper(tree: BST?, target: Int, closest: Int): Int {
    tree ?: return closest

    var nextClosest = closest

    if (abs(target - tree.value) < abs(target - closest)) {
        nextClosest = tree.value
    }

    if (target < tree.value) {
        nextClosest = helper(tree.left, target, nextClosest)
    } else if (target > tree.value) {
        nextClosest = helper(tree.right, target, nextClosest)
    }

    return nextClosest
}
