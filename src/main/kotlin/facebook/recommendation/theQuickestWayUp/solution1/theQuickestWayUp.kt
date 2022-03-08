package facebook.recommendation.theQuickestWayUp.solution1

import java.util.LinkedList

fun quickestWayUp(ladders: Array<Array<Int>>, snakes: Array<Array<Int>>): Int {
    val laddersMap = mutableMapOf<Int, Int>()
    for ((ladderFrom, ladderTo) in ladders) {
        laddersMap[ladderFrom] = ladderTo
    }

    val snakesMap = mutableMapOf<Int, Int>()
    for ((snakeFrom, snakeTo) in snakes) {
        snakesMap[snakeFrom] = snakeTo
    }

    val queue = LinkedList<Pair<Int, Int>>()
    val visited = mutableSetOf<Int>()

    queue.add(1 to 0)

    while (queue.isNotEmpty()) {
        val (currentPosition, currentDist) = queue.poll()

        if (currentPosition in visited) continue

        visited.add(currentPosition)
        if (currentPosition == 100) return currentDist

        for (move in 1 .. 6) {
            val movedPosition = currentPosition + move

            val nextPosition = when (movedPosition) {
                in laddersMap -> laddersMap[movedPosition]!!
                in snakesMap -> snakesMap[movedPosition]!!
                else -> movedPosition
            }

            if (nextPosition <= 100) {
                queue.add(nextPosition to currentDist + 1)
            }
        }
    }

    return -1
}