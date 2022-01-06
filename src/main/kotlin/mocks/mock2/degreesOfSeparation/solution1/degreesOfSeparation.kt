package mocks.mock2.degreesOfSeparation.solution1

import java.util.LinkedList

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

    val queue = LinkedList<Pair<String, Int>>()
    val visited = mutableSetOf<String>()

    queue.add(startPerson to 0)

    while (queue.isNotEmpty()) {
        val (currentNode, currentDistance) = queue.pop()

        if (visited.contains(currentNode)) continue

        visited.add(currentNode)
        shortestDistances[currentNode] = currentDistance

        for (neighbor in friendsLists[currentNode]!!) {
            if (!visited.contains(neighbor)) {
                queue.add(neighbor to currentDistance + 1)
            }
        }
    }

    return shortestDistances
}
