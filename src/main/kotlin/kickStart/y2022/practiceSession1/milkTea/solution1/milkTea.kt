package kickStart.y2022.practiceSession1.milkTea.solution1

import kotlin.math.min

fun parseAndFindMinComplaints() {
    val numTests = readLine()!!.toInt()

    for (i in 1 .. numTests) {
        val (n, m, p) = readLine()!!.split(" ").map { it.toInt() }
        val preferences = (0 until n).map { readLine()!! }
        val forbiddenSet = (0 until m).map { readLine()!! }.toSet()
        val minComplains = getMinComplains(preferences, forbiddenSet, m, n, p)
        println("Case #$i: $minComplains")
    }
}

fun getMinComplains(preferences: List<String>, forbiddenSet: Set<String>, m: Int, n: Int, p: Int): Int {
    var runningList = listOf(ScoreWithPref(0, ""))

    for (pos in 0 until p) {
        val newList = mutableListOf<ScoreWithPref>()

        for (prevItem in runningList) {
            expand(newList, prevItem.preference, preferences)
        }

        newList.sort()

        runningList = newList.subList(0, min(newList.size, m + 1))
    }

    for (currentItem in runningList) {
        if (currentItem.preference !in forbiddenSet) {
            return currentItem.score
        }
    }

    return -1
}

fun calculateDiff(prefix: String, preference: String): Int {
    var diff = 0

    for (i in prefix.indices) {
        if (prefix[i] != preference[i]) {
            diff += 1
        }
    }

    return diff
}

fun calculateScore(prefix: String, prefs: List<String>): Int {
    var score = 0

    for (preference in prefs) {
        score += calculateDiff(prefix, preference)
    }

    return score
}

fun expand(itemList: MutableList<ScoreWithPref>, prevPrefStr: String, prefs: List<String>) {
    val appendedZero = "${prevPrefStr}0"
    itemList.add(ScoreWithPref(calculateScore(appendedZero, prefs), appendedZero))

    val appendedOne = "${prevPrefStr}1"
    itemList.add(ScoreWithPref(calculateScore(appendedOne, prefs), appendedOne))
}

class ScoreWithPref(val score: Int, val preference: String): Comparable<ScoreWithPref> {
    override fun compareTo(other: ScoreWithPref): Int {
        return score.compareTo(other.score)
    }
}

fun main() {
    parseAndFindMinComplaints()
}