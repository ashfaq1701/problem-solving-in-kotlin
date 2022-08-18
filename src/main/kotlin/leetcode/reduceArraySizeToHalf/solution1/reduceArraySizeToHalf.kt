package leetcode.reduceArraySizeToHalf.solution1

class Solution {
    fun minSetSize(arr: IntArray): Int {
        val freq = mutableMapOf<Int, Int>()
        for (num in arr) {
            if (num !in freq) {
                freq[num] = 0
            }

            freq[num] = freq[num]!! + 1
        }

        val sortedCounts = freq.values.sortedWith(Comparator<Int> { n1, n2 ->
            n2.compareTo(n1)
        })


        var totalSumYet = 0
        var itemsRemoved = 0
        for (count in sortedCounts) {
            if (totalSumYet >= arr.size / 2) break

            totalSumYet += count
            itemsRemoved += 1
        }

        return itemsRemoved
    }
}