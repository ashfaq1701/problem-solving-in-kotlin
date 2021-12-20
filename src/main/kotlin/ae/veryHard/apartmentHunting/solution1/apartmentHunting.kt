package ae.veryHard.apartmentHunting.solution1

import kotlin.math.*

fun apartmentHunting(blocks: List<Map<String, Boolean>>, reqs: List<String>): Int {
    val maxDistancesAtBlocks = MutableList(blocks.size) { Integer.MIN_VALUE }

    for (i in 0 .. blocks.lastIndex) {

        reqs.forEach { req ->

            var minDistanceToRequirement = blocks
                .foldIndexed(Integer.MAX_VALUE) { j, minDistance, currentBlock ->
                    if (currentBlock[req]!!) {
                        min(minDistance, getDistance(i, j))
                    } else minDistance
                }

            maxDistancesAtBlocks[i] = max(minDistanceToRequirement, maxDistancesAtBlocks[i])
        }
    }

    return getIdxWithMinRequirement(maxDistancesAtBlocks)
}

fun getDistance(i: Int, j: Int) = abs(i - j)

fun getIdxWithMinRequirement(maxDistancesAtBlocks: List<Int>) : Int {
    val minElement = maxDistancesAtBlocks.minOrNull()!!
    return maxDistancesAtBlocks.indexOf(minElement)
}
