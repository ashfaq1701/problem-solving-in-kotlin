package leetcode.reorderedPowerOfTwo.solution2

class Solution {
    fun reorderedPowerOf2(n: Int): Boolean {
        val countN = count(n)

        for (i in 0 until 31) {
            if (countN == count(1 shl i)) {
                return true
            }
        }

        return false
    }

    fun count(n: Int): List<Int>  {
        var countArr = MutableList(10) { 0 };
        var currentN = n

        while (currentN > 0) {
            countArr[currentN % 10] += 1
            currentN /= 10
        }

        return countArr
    }
}