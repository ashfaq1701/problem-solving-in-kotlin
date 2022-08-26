package leetcode.minimumNumberOfRefuelingStops.solution2

import java.util.*
import kotlin.Comparator

class Solution {
    fun minRefuelStops(target: Int, startFuel: Int, stations: Array<IntArray>): Int {
        val pq = PriorityQueue<Int>(Comparator<Int> { a, b ->
            b.compareTo(a)
        })
        var tank = startFuel

        var numRefuels = 0
        var prevDistance = 0

        for (station in stations) {
            val (distance, fuel) = station

            tank -= (distance - prevDistance)

            while (pq.isNotEmpty() && tank < 0) {
                tank += pq.poll()
                numRefuels += 1
            }

            if (tank < 0) return -1

            pq.add(fuel)
            prevDistance = distance
        }

        tank -= (target - prevDistance)
        while (pq.isNotEmpty() && tank < 0) {
            tank += pq.poll()
            numRefuels += 1
        }
        if (tank < 0) return -1

        return numRefuels
    }
}