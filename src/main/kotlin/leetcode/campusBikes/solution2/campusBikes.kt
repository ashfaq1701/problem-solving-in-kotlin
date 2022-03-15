package leetcode.campusBikes.solution2

import java.util.*
import kotlin.Comparator
import kotlin.math.abs

/**
 * WRONG code. Fails few test cases
 */
data class WorkerBikePair(val workerIdx: Int, val bikeIdx: Int, val distance: Int)

class Solution {
    private val workerBikePairComparator = Comparator<WorkerBikePair> { o1, o2 ->
        if (o1.distance != o2.distance) {
            o1.distance.compareTo(o2.distance)
        } else if (o1.workerIdx != o2.workerIdx) {
            o1.workerIdx.compareTo(o2.workerIdx)
        } else {
            o1.bikeIdx.compareTo(o2.bikeIdx)
        }
    }

    fun assignBikes(workers: Array<IntArray>, bikes: Array<IntArray>): IntArray {
        val heap = PriorityQueue(workerBikePairComparator)
        val workerBikeLists = mutableMapOf<Int, MutableList<Pair<Int, Int>>>()

        for (workerIdx in workers.indices) {
            val bikeDistanceList = mutableListOf<Pair<Int, Int>>()

            for (bikeIdx in bikes.indices) {
                bikeDistanceList.add(getDistance(workers[workerIdx], bikes[bikeIdx]) to bikeIdx)
            }

            bikeDistanceList.sortWith(Comparator<Pair<Int, Int>> { p1, p2 ->
                p2.first.compareTo(p1.first)
            })

            workerBikeLists[workerIdx] = bikeDistanceList

            val (minDistance, bikeIdx) = bikeDistanceList.removeAt(bikeDistanceList.lastIndex)
            heap.add(WorkerBikePair(workerIdx, bikeIdx, minDistance))
        }

        val bikesTaken = MutableList<Boolean>(bikes.size) { false }
        val workerBikeIds = MutableList<Int>(workers.size) { -1 }

        while (heap.isNotEmpty()) {
            val (workerIdx, bikeIdx, _) = heap.poll()

            if (!bikesTaken[bikeIdx]) {
                bikesTaken[bikeIdx] = true
                workerBikeIds[workerIdx] = bikeIdx
            } else {
                val bikeList = workerBikeLists[workerIdx]!!
                val (dist, nextBikeIdx) = bikeList.removeAt(bikeList.lastIndex)
                heap.add(WorkerBikePair(workerIdx, nextBikeIdx, dist))
            }
        }

        return workerBikeIds.toIntArray()
    }

    fun getDistance(p1: IntArray, p2: IntArray): Int {
        return abs(p1[0] - p2[0]) + abs(p1[1] - p2[1])
    }
}
