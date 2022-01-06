package mocks.mock2.degreesOfSeparation.solution2

import java.util.PriorityQueue

// Using Dijkstra (Unoptimal)
fun degreesOfSeparation(friendsLists: Map<String, List<String>>, personOne: String, personTwo: String): String {
    val countPersonsOne = findNumberOfPersonsWithHighDegreesOfSeparation(friendsLists, personOne)
    val countPersonsTwo = findNumberOfPersonsWithHighDegreesOfSeparation(friendsLists, personTwo)

    return if (countPersonsOne > countPersonsTwo) {
        personTwo
    } else if (countPersonsTwo > countPersonsOne) {
        personOne
    } else ""
}

fun findNumberOfPersonsWithHighDegreesOfSeparation(friendsLists: Map<String, List<String>>, person: String): Int {
    val shortestDistances = findShortestDistancesGivenStartNode(friendsLists, person)
    return shortestDistances.count { (_, dist) -> dist > 6 }
}

fun findShortestDistancesGivenStartNode(friendsLists: Map<String, List<String>>, startPerson: String): Map<String, Int> {
    val shortestDistances = friendsLists.mapValues { Int.MAX_VALUE }.toMutableMap()
    shortestDistances[startPerson] = 0

    val nodesToVisit = PriorityQueue(Comparator<Pair<String, Int>> { p1, p2 ->
        p1.second.compareTo(p2.second)
    })
    nodesToVisit.add(startPerson to 0)

    while (nodesToVisit.isNotEmpty()) {
        val (currentNode, distanceToCurrentNode) = nodesToVisit.poll()

        if (distanceToCurrentNode == Int.MAX_VALUE) continue

        for (neighbor in friendsLists[currentNode]!!) {
            val distanceToNeighbor = distanceToCurrentNode + 1

            if (distanceToNeighbor < shortestDistances[neighbor]!!) {
                shortestDistances[neighbor] = distanceToNeighbor
                nodesToVisit.add(neighbor to distanceToNeighbor)
            }
        }
    }

    return shortestDistances
}
