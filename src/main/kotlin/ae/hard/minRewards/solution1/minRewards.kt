package ae.hard.minRewards.solution1

import kotlin.math.max

fun minRewards(scores: List<Int>): Int {
    val rewards = MutableList(scores.size) { 1 }

    for (i in 1 until scores.size) {

        var j = i - 1

        if (scores[i] > scores[j]) {
            rewards[i] = rewards[j] + 1
        } else {
            while (j >= 0 && scores[j] > scores[j + 1]) {
                rewards[j] = max(rewards[j], rewards[j + 1] + 1)
                j -= 1
            }
        }
    }

    return rewards.sum()
}