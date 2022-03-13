package leetcode.topKFrequentElements.solution1

import java.util.*
import kotlin.Comparator

class Solution {
    fun topKFrequent(nums: IntArray, k: Int): IntArray {
        val counts = populateCounts(nums)

        val heap = PriorityQueue<Int>(Comparator<Int> { a, b ->
            counts[a]!!.compareTo(counts[b]!!)
        })

        for (num in counts.keys) {
            heap.add(num)

            if (heap.size > k) {
                heap.poll()
            }
        }

        val topKFrequentElements = mutableListOf<Int>()
        while (heap.isNotEmpty()) {
            topKFrequentElements.add(heap.poll())
        }

        return topKFrequentElements.toIntArray()
    }

    fun populateCounts(nums: IntArray): Map<Int, Int> {
        val counts = mutableMapOf<Int, Int>()

        for (num in nums) {
            if (num !in counts) {
                counts[num] = 0
            }

            counts[num] = counts[num]!! + 1
        }

        return counts
    }
}