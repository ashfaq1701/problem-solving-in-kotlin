package leetcode.poorPigs.solution1

import kotlin.math.log10
import kotlin.math.ceil

class Solution {
    fun poorPigs(buckets: Int, minutesToDie: Int, minutesToTest: Int): Int {
        val states = minutesToTest / minutesToDie + 1
        return ceil(log10(buckets.toDouble()) / log10(states.toDouble())).toInt()
    }
}