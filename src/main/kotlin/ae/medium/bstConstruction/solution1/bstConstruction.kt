package ae.medium.bstConstruction.solution1

open class BST(value: Int) {
    var value = value
    var left: BST? = null
    var right: BST? = null

    fun insert(value: Int): BST {
        if (value < this.value) {
            if (left == null) {
                left = BST(value)
            } else {
                left!!.insert(value)
            }
        } else {
            if (right == null) {
                right = BST(value)
            } else {
                right!!.insert(value)
            }
        }

        return this
    }

    fun contains(value: Int): Boolean {
        return if (value < this.value) {
            left?.contains(value) ?: false
        } else if (value > this.value) {
            right?.contains(value) ?: false
        } else {
            true
        }
    }

    fun remove(value: Int, parent: BST? = null): BST {
        if (value < this.value) {
            left?.remove(value, this)
        } else if (value > this.value) {
            right?.remove(value, this)
        } else {
            if (left != null && right != null) {
                this.value = right!!.getMinValue()
                right!!.remove(this.value, this)
            } else if (parent == null) {
                if (left != null) {
                    this.value = left!!.value
                    right = left!!.right
                    left = left!!.left
                } else if (right != null) {
                    this.value = right!!.value
                    left = right!!.left
                    right = right!!.right
                } else {
                    // Node has no left child and no right child so how to remove it
                }
            } else if (parent.left == this) {
                parent.left = if (left != null) left else right
            } else if (parent.right == this) {
                parent.right = if (left != null) left else right
            }
        }

        return this
    }

    fun getMinValue(): Int {
        return if (left != null) {
            left!!.getMinValue()
        } else this.value
    }
}

