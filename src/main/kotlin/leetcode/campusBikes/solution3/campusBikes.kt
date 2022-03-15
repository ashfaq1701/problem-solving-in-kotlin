package leetcode.campusBikes.solution3

import kotlin.math.abs
import kotlin.math.min

data class WorkerBikePair(val workerIdx: Int, val bikeIdx: Int)

class Solution {

    fun assignBikes(workers: Array<IntArray>, bikes: Array<IntArray>): IntArray {
        var minDist = Int.MAX_VALUE
        val buckets = mutableMapOf<Int, MutableList<WorkerBikePair>>()

        for (workerIdx in workers.indices) {
            for (bikeIdx in bikes.indices) {
                val dist = getDistance(workers[workerIdx], bikes[bikeIdx])

                if (dist !in buckets) {
                    buckets[dist] = mutableListOf()
                }
                buckets[dist]!!.add(WorkerBikePair(workerIdx, bikeIdx))

                minDist = min(minDist, dist)
            }
        }

        var currentDist = minDist

        val bikesAssigned = MutableList(bikes.size) { false }
        val workerAssignment = MutableList(workers.size) { -1 }
        var countWorkersAssigned = 0

        while (countWorkersAssigned < workers.size) {
            if (currentDist !in buckets) {
                currentDist += 1
                continue
            }

            for ((workerIdx, bikeIdx) in buckets[currentDist]!!) {
                if (workerAssignment[workerIdx] == -1 && !bikesAssigned[bikeIdx]) {
                    workerAssignment[workerIdx] = bikeIdx
                    bikesAssigned[bikeIdx] = true

                    countWorkersAssigned += 1
                }
            }

            currentDist += 1
        }

        return workerAssignment.toIntArray()
    }

    fun getDistance(p1: IntArray, p2: IntArray): Int {
        return abs(p1[0] - p2[0]) + abs(p1[1] - p2[1])
    }
}
