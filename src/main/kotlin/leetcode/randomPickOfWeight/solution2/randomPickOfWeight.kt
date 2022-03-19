package leetcode.randomPickOfWeight.solution2

class Solution(w: IntArray) {

    private var prefixSums: MutableList<Int>
    private var totalSum: Int

    init {
        prefixSums = mutableListOf<Int>()
        totalSum = 0

        for (weight in w) {
            totalSum += weight
            prefixSums.add(totalSum)
        }
    }

    fun pickIndex(): Int {
        val target = totalSum * Math.random()

        var left = 0
        var right = prefixSums.lastIndex

        while (left < right) {
            val mid = (left + right) / 2

            if (target > prefixSums[mid]) {
                left = mid + 1
            } else {
                right = mid
            }
        }

        return left
    }

}