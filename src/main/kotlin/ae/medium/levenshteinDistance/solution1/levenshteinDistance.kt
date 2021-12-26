package ae.medium.levenshteinDistance.solution1

import kotlin.math.min

fun levenshteinDistance(str1: String, str2: String): Int {
    val edits = MutableList(str1.length + 1) { MutableList(str2.length + 1) { 0 } }

    for (i in 0 .. str1.length) {
        edits[i][0] = i
    }

    for (j in 0 .. str2.length) {
        edits[0][j] = j
    }

    // If i, j pair characters are equal then take the diagonal
    // else take the min of three neighboring numbers + 1
    for (i in 1 .. str1.length) {
        for (j in 1 .. str2.length) {
            if (str1[i - 1] == str2[j - 1]) {
                edits[i][j] = edits[i - 1][j - 1]
            } else {
                edits[i][j] = 1 + min(
                    min(edits[i - 1][j], edits[i][j - 1]),
                    edits[i - 1][j - 1]
                )
            }
        }
    }

    return edits[str1.length][str2.length]
}
