package leetcode.randomPickOfWeight.solution1

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

        for (i in prefixSums.indices) {
            if (target < prefixSums[i]) {
                return i
            }
        }

        return prefixSums.lastIndex
    }

}