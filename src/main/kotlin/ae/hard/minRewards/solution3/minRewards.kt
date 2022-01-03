package ae.hard.minRewards.solution3

import kotlin.math.max

fun minRewards(scores: List<Int>): Int {

    val rewards = MutableList(scores.size) { 1 }

    for (i in 1 until scores.size) {
        if (scores[i] > scores[i - 1]) {
            rewards[i] = rewards[i - 1] + 1
        }
    }

    for (i in scores.lastIndex - 1 downTo 0) {
        if (scores[i] > scores[i + 1]) {
            rewards[i] = max(rewards[i], rewards[i + 1] + 1)
        }
    }

    return rewards.sum()
}
