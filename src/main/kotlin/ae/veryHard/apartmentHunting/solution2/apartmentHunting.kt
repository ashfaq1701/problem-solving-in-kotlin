package ae.veryHard.apartmentHunting.solution2

import kotlin.math.abs
import kotlin.math.min

fun apartmentHunting(blocks: List<Map<String, Boolean>>, reqs: List<String>): Int {
    val minDistancesToRequirements = getMinimumDistancesToRequirements(blocks, reqs)
    val maxDistanceAtBlocks = getMaxDistanceAtBlocks(minDistancesToRequirements)
    return getIdxWithMinRequirement(maxDistanceAtBlocks)
}

// For each requirement create an array of minimum distances from a block to that requirement
// So returned value from this function will be array of such distances array for all requirements
fun getMinimumDistancesToRequirements(blocks: List<Map<String, Boolean>>, reqs: List<String>) : List<List<Int>> {
    val minimumDistancesToRequirements = reqs.map { req ->
        getMinimumDistancesToRequirement(blocks, req)
    }

    return minimumDistancesToRequirements
}

// Visit the array from left to right and from right to left to
// calculate minimum distance to requirement
fun getMinimumDistancesToRequirement(blocks: List<Map<String, Boolean>>, req: String) : List<Int> {
    val minDistancesToRequirement = MutableList(blocks.size) { 0 }

    // Assign it to INT_MAX because INT_MAX is the highest possible number.
    // See below comments for clarity
    var lastIdxOfRequirement = Integer.MAX_VALUE

    // Left to right partial calculation
    for (idx in 0 .. blocks.lastIndex) {
        val currentBlock = blocks[idx]

        // If index 0 doesn't contain requirement then minimumDistancesToRequirement[0] will be INT_MAX - 0
        // minimumDistancesToRequirement[1] will be INT_MAX - 1 and so on.
        // Which are also big numbers
        // But for example if we used 0 to initialize lastRequirementIdx then these values would be 0, -1, -2 etc
        // Which are smaller than other legit distances.
        // As a result line 78 will fail. Hence we need to initialize lastRequirementIdx to INT_MAX.
        if (currentBlock[req]!!) {
            lastIdxOfRequirement = idx
        }

        minDistancesToRequirement[idx] = getDistance(idx, lastIdxOfRequirement)
    }

    // Right to left partial calculation
    for (idx in blocks.lastIndex downTo 0) {
        val currentBlock = blocks[idx]

        if (currentBlock[req]!!) {
            lastIdxOfRequirement = idx
        }

        minDistancesToRequirement[idx] = min(minDistancesToRequirement[idx], getDistance(idx, lastIdxOfRequirement))
    }

    return minDistancesToRequirement
}

// Group elements of each requirements to their maximum value for every block.
fun getMaxDistanceAtBlocks(minDistancesToRequirements: List<List<Int>>): List<Int> {
    val blockCount = minDistancesToRequirements[0].size

    val maxDistances = mutableListOf<Int>()

    for (i in 0 until blockCount) {

        val distancesAtBlock = minDistancesToRequirements.map { it[i] }
        maxDistances.add(distancesAtBlock.maxOrNull()!!)
    }

    return maxDistances
}

fun getDistance(i: Int, j: Int) = abs(i - j)

// Get index of the minimum value.
fun getIdxWithMinRequirement(maxDistancesAtBlocks: List<Int>) : Int {
    val minElement = maxDistancesAtBlocks.minOrNull()!!
    return maxDistancesAtBlocks.indexOf(minElement)
}
