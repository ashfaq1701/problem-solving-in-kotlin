package ae.easy.findClosestValueInBST.solution2

import kotlin.math.abs

open class BST(value: Int) {
    var value = value
    var left: BST? = null
    var right: BST? = null
}

fun findClosestValueInBst(tree: BST, target: Int): Int {
    var current: BST? = tree
    var closest = tree.value

    while (current != null) {
        if (abs(target - current.value) < abs(target - closest)) {
            closest = current.value
        }

        if (target < current.value) {
            current = current.left
        } else if (target > current.value) {
            current = current.right
        } else {
            break
        }
    }

    return closest
}
