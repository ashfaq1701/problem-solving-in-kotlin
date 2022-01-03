package ae.hard.minRewards.solution2

import kotlin.math.max

fun minRewards(scores: List<Int>): Int {
    val rewards = MutableList(scores.size) { 1 }

    for (valleyIdx in findValleyIdxs(scores)) {
        expandAroundValley(valleyIdx, scores, rewards)
    }

    return rewards.sum()
}

fun findValleyIdxs(array: List<Int>): List<Int> {
    if (array.size == 1) return listOf(0)

    val valleyIdxs = mutableListOf<Int>()

    for (i in array.indices) {
        if (i == 0 && array[i] < array[i + 1]) {
            valleyIdxs.add(i)
        } else if (i == array.lastIndex && array[i] < array[i - 1]) {
            valleyIdxs.add(i)
        } else if (i == 0 || i == array.lastIndex) {
            continue
        } else if (array[i] < array[i - 1] && array[i] < array[i + 1]) {
            valleyIdxs.add(i)
        }
    }

    return valleyIdxs
}

fun expandAroundValley(valleyIdx: Int, scores: List<Int>, rewards: MutableList<Int>) {
    var leftIdx = valleyIdx - 1
    while (leftIdx >= 0 && scores[leftIdx] > scores[leftIdx + 1]) {
        rewards[leftIdx] = max(rewards[leftIdx], rewards[leftIdx + 1] + 1)
        leftIdx -= 1
    }

    var rightIdx = valleyIdx + 1
    while (rightIdx < scores.size && scores[rightIdx] > scores[rightIdx - 1]) {
        rewards[rightIdx] = rewards[rightIdx - 1] + 1
        rightIdx += 1
    }
}
