package mocks.mock1.globMatching.solution1

fun globMatching(fileName: String, pattern: String): Boolean {
    return matchPatternStartingFrom(fileName, pattern, 0, 0)
}

fun matchPatternStartingFrom(fileName: String, pattern: String, nameIdx: Int, patternIdx: Int): Boolean {
    if (patternIdx == pattern.length) {
        return nameIdx == fileName.length
    }

    val patternChar = pattern[patternIdx]
    if (nameIdx == fileName.length) {
        return patternChar == '*' && matchPatternStartingFrom(fileName, pattern, nameIdx, patternIdx + 1)
    }

    val nameChar = fileName[nameIdx]

    return when (patternChar) {
        '*' -> {
            matchPatternStartingFrom(fileName, pattern, nameIdx, patternIdx + 1) ||
                    matchPatternStartingFrom(fileName, pattern, nameIdx + 1, patternIdx)
        }
        '?' -> {
            matchPatternStartingFrom(fileName, pattern, nameIdx + 1, patternIdx + 1)
        }
        else -> {
            if (nameChar == patternChar) {
                matchPatternStartingFrom(fileName, pattern, nameIdx + 1, patternIdx + 1)
            } else {
                return false
            }
        }
    }
}
