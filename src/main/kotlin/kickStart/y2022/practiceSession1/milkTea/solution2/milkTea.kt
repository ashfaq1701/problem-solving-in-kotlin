package kickStart.y2022.practiceSession1.milkTea.solution2

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
    val zeroCounts = MutableList(p) { 0 }
    val oneCounts = MutableList(p) { 0 }

    for (preference in preferences) {
        for (position in 0 until p) {
            if (preference[position] == '1') {
                oneCounts[position] += 1
            } else {
                zeroCounts[position] += 1
            }
        }
    }

    var runningList = listOf(ScoreWithPref(0, ""))

    for (pos in 0 until p) {
        val newList = mutableListOf<ScoreWithPref>()

        for (prevItem in runningList) {
            expand(newList, prevItem, preferences, zeroCounts, oneCounts)
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

fun expand(itemList: MutableList<ScoreWithPref>, prevPrefItem: ScoreWithPref, prefs: List<String>, zeroCounts: List<Int>, oneCounts: List<Int>) {
    val appendedZero = "${prevPrefItem.preference}0"
    itemList.add(ScoreWithPref(prevPrefItem.score + oneCounts[prevPrefItem.preference.length], appendedZero))

    val appendedOne = "${prevPrefItem.preference}1"
    itemList.add(ScoreWithPref(prevPrefItem.score + zeroCounts[prevPrefItem.preference.length], appendedOne))
}

class ScoreWithPref(val score: Int, val preference: String): Comparable<ScoreWithPref> {
    override fun compareTo(other: ScoreWithPref): Int {
        return score.compareTo(other.score)
    }
}

fun main() {
    parseAndFindMinComplaints()
}