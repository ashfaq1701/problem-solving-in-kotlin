package leetcode.nestedListWeightSum.solution1

abstract class NestedInteger {
    // Constructor initializes an empty nested list.
    constructor()

    // Constructor initializes a single integer.
    constructor(value: Int)

    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    abstract fun isInteger(): Boolean

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    abstract fun getInteger(): Int?

    // Set this NestedInteger to hold a single integer.
    abstract fun setInteger(value: Int): Unit

    // Set this NestedInteger to hold a nested list and adds a nested integer to it.
    abstract fun add(ni: NestedInteger): Unit

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return null if this NestedInteger holds a single integer
    abstract fun getList(): List<NestedInteger>?
}

class Solution {
    fun depthSum(nestedList: List<NestedInteger>): Int {
        var sum = 0

        for (item in nestedList) {
            sum += depthSumHelper(item, 1)
        }

        return sum
    }

    fun depthSumHelper(nestedItem: NestedInteger, depth: Int): Int {
        return if (nestedItem.isInteger()) {
            depth * nestedItem.getInteger()!!
        } else {
            var sum = 0

            for (item in nestedItem.getList()!!) {
                sum += depthSumHelper(item, depth + 1)
            }

            sum
        }
    }
}
