package leetcode.rangeSumQueryMutable.solution1

class NumArray(val nums: IntArray) {

    val tree: MutableList<Int> by lazy {
        val t = MutableList(nums.size * 2) { 0 }

        for (i in nums.indices) {
            t[nums.size + i] = nums[i]
        }

        for (i in nums.lastIndex downTo 1) {
            t[i] = t[i * 2] + t[i * 2 + 1]
        }

        t
    }

    fun update(index: Int, `val`: Int) {
        var shiftedIdx = index + nums.size
        tree[shiftedIdx] = `val`

        while (shiftedIdx > 0) {
            var left = shiftedIdx
            var right = shiftedIdx

            if (shiftedIdx % 2 == 0) {
                right = shiftedIdx + 1
            } else {
                left = shiftedIdx - 1
            }

            shiftedIdx /= 2
            tree[shiftedIdx] = tree[left] + tree[right]
        }
    }

    fun sumRange(left: Int, right: Int): Int {
        var shiftedLeft = left + nums.size
        var shiftedRight = right + nums.size

        var sum = 0

        while (shiftedLeft <= shiftedRight) {
            if (shiftedLeft % 2 == 1) {
                sum += tree[shiftedLeft]
                shiftedLeft += 1
            }

            if (shiftedRight % 2 == 0) {
                sum += tree[shiftedRight]
                shiftedRight -= 1
            }

            shiftedLeft /= 2
            shiftedRight /= 2
        }

        return sum
    }
}