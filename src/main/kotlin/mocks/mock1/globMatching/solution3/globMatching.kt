package mocks.mock1.globMatching.solution3

// O(n * m) Time | O(n * m) Space
fun globMatching(fileName: String, pattern: String): Boolean {
    val matchTable = initializeMatchTable(fileName, pattern)

    for (i in 1 .. fileName.length) {
        for (j in 1 .. pattern.length) {
            if (pattern[j - 1] == '*') {
                matchTable[i][j] = matchTable[i][j - 1] || matchTable[i - 1][j]
            } else if (pattern[j - 1] == '?' || pattern[j - 1] == fileName[i - 1]) {
                matchTable[i][j] = matchTable[i - 1][j - 1]
            }
        }
    }

    return matchTable[fileName.length][pattern.length]
}

fun initializeMatchTable(fileName: String, pattern: String): MutableList<MutableList<Boolean>> {
    val matchTable = MutableList(fileName.length + 1) {
        MutableList(pattern.length + 1) { false }
    }

    matchTable[0][0] = true

    for (j in 1 .. pattern.length) {
        if (pattern[j - 1] == '*') matchTable[0][j] = matchTable[0][j - 1]
    }

    return matchTable
}