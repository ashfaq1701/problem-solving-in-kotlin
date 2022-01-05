package mocks.mock1.globMatching.solution2

fun globMatching(fileName: String, pattern: String): Boolean {
    val memo = MutableList(fileName.length + 1) { MutableList(pattern.length) { -1 } }
    return matchPatternStartingFrom(fileName, pattern, 0, 0, memo)
}

fun matchPatternStartingFrom(fileName: String, pattern: String, nameIdx: Int, patternIdx: Int, memo: MutableList<MutableList<Int>>): Boolean {
    if (patternIdx == pattern.length) {
        return nameIdx == fileName.length
    }

    if (memo[nameIdx][patternIdx] != -1) {
        return memo[nameIdx][patternIdx] == 1
    }

    val patternChar = pattern[patternIdx]
    if (nameIdx == fileName.length) {
        return if (patternChar == '*') {
            val nextMatch = matchPatternStartingFrom(fileName, pattern, nameIdx, patternIdx + 1, memo)
            memo[nameIdx][patternIdx] = if (nextMatch) 1 else 0
            return nextMatch
        } else false
    }

    val nameChar = fileName[nameIdx]

    val nextMatch = when (patternChar) {
        '*' -> {
            matchPatternStartingFrom(fileName, pattern, nameIdx, patternIdx + 1, memo) ||
                    matchPatternStartingFrom(fileName, pattern, nameIdx + 1, patternIdx, memo)
        }
        '?' -> {
            matchPatternStartingFrom(fileName, pattern, nameIdx + 1, patternIdx + 1, memo)
        }
        else -> {
            if (nameChar == patternChar) {
                matchPatternStartingFrom(fileName, pattern, nameIdx + 1, patternIdx + 1, memo)
            } else {
                return false
            }
        }
    }

    memo[nameIdx][patternIdx] = if (nextMatch) 1 else 0
    return nextMatch
}
