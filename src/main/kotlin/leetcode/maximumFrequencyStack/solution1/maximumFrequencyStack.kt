package leetcode.maximumFrequencyStack.solution1

import java.util.*
import kotlin.math.max

class FreqStack() {

    val freq = mutableMapOf<Int, Int>()
    var maxFreq = 0
    val buckets = mutableMapOf<Int, Stack<Int>>()

    fun push(`val`: Int) {
        val currentFreq = (freq[`val`] ?: 0) + 1
        freq[`val`] = currentFreq
        maxFreq = max(currentFreq, maxFreq)

        if (currentFreq !in buckets) {
            buckets[currentFreq] = Stack<Int>()
        }
        buckets[currentFreq]!!.add(`val`)
    }

    fun pop(): Int {
        val item = buckets[maxFreq]!!.pop()
        freq[item] = freq[item]!! - 1
        if (buckets[maxFreq]!!.isEmpty()) {
            maxFreq -= 1
        }
        return item
    }

}