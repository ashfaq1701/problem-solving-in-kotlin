package codejam.y2022.qualification.chainReactions.solution1

import kotlin.math.max

fun findMaximumFunForTestCases() {
    val testCaseCount = readLine()!!.toInt()

    for (i in 1 .. testCaseCount) {
        val numModules = readLine()!!.toInt()
        val funFactors = readLine()!!.split(" ").map { it.toInt() }
        val connections = readLine()!!.split(" ").map { it.toInt() }
        val maxFun = findMaxFun(numModules, funFactors, connections)
        println("Case #$i: $maxFun")
    }
}

fun findMaxFun(numModules: Int, funFactors: List<Int>, connections: List<Int>): Int {
    val initiators = getInitiators(numModules, connections)
    val seen = mutableSetOf<Int>()
    var totalFun = 0

    val sortedInitiators = initiators.sortedWith(Comparator { i1, i2 ->
        funFactors[i1].compareTo(funFactors[i2])
    })

    for (initiator in sortedInitiators) {
        println(initiator + 1)
        totalFun += getMaxFunForPath(initiator + 1, funFactors, connections, seen)
    }

    return totalFun
}

fun getInitiators(numModules: Int, connections: List<Int>): List<Int> {
    val pointedByCounts = MutableList(numModules) { 0 }

    for (idx in connections.indices) {
        val pointsTo = connections[idx] - 1
        if (pointsTo != -1) {
            pointedByCounts[pointsTo] += 1
        }
    }

    val initiators = mutableListOf<Int>()
    for (idx in pointedByCounts.indices) {
        if (pointedByCounts[idx] == 0) {
            initiators.add(idx)
        }
    }

    return initiators
}

fun getMaxFunForPath(initiatorIdx: Int, funFactors: List<Int>, connections: List<Int>, seen: MutableSet<Int>): Int {
    var currentIdx = initiatorIdx
    var maxFun = 0

    while (currentIdx !in seen && currentIdx != 0) {
        maxFun = max(maxFun, funFactors[currentIdx - 1])
        seen.add(currentIdx)
        currentIdx = connections[currentIdx - 1]
    }

    return maxFun
}

fun main() {
    findMaximumFunForTestCases()
}