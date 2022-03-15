package leetcode.campusBikes.solution1

import kotlin.math.abs

data class WorkerBikePair(val workerIdx: Int, val bikeIdx: Int, val distance: Int)

class Solution {
    fun assignBikes(workers: Array<IntArray>, bikes: Array<IntArray>): IntArray {
        val allPairs = mutableListOf<WorkerBikePair>()

        for (workerIdx in workers.indices) {
            for (bikeIdx in bikes.indices) {
                allPairs.add(WorkerBikePair(workerIdx, bikeIdx, getDistance(workers[workerIdx], bikes[bikeIdx])))
            }
        }

        allPairs.sortWith(Comparator<WorkerBikePair> { o1, o2 ->
            if (o1.distance != o2.distance) {
                o1.distance.compareTo(o2.distance)
            } else if (o1.workerIdx != o2.workerIdx) {
                o1.workerIdx.compareTo(o2.workerIdx)
            } else {
                o1.bikeIdx.compareTo(o2.bikeIdx)
            }
        })

        val bikesTaken = MutableList<Boolean>(bikes.size) { false }
        val workerBikeIds = MutableList<Int>(workers.size) { -1 }
        var workersAssigned = 0

        for ((workerIdx, bikeIdx, _) in allPairs) {
            if (!bikesTaken[bikeIdx] && workerBikeIds[workerIdx] == -1) {
                bikesTaken[bikeIdx] = true
                workerBikeIds[workerIdx] = bikeIdx

                workersAssigned += 1
            }

            if (workersAssigned == workers.size) {
                break
            }
        }

        return workerBikeIds.toIntArray()
    }

    fun getDistance(p1: IntArray, p2: IntArray): Int {
        return abs(p1[0] - p2[0]) + abs(p1[1] - p2[1])
    }
}