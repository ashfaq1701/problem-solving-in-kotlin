package ae.hard.patternMatcher.solution1

import kotlin.math.floor

fun patternMatcher(pattern: String, string: String): List<String> {
    if (pattern.length > string.length) {
        return listOf()
    }

    val transformedPattern = transformPattern(pattern)
    val isFlipped = transformedPattern[0] != pattern[0]

    val charCounts = mutableMapOf('x' to 0, 'y' to 0)
    val firstYPosition = countCharsAndGetFirstYPosition(transformedPattern, charCounts)

    if (charCounts['y']!! > 0) {
        for (xSize in 1 .. string.lastIndex) {
            val ySizeDouble = (string.length - charCounts['x']!! * xSize).toDouble() / charCounts['y']!!
            if (ySizeDouble <= 0 || floor(ySizeDouble) != ySizeDouble) continue
            val ySize = ySizeDouble.toInt()
            val yStartingIdx = firstYPosition!! * xSize

            val x = string.substring(0, xSize)
            val y = string.substring(yStartingIdx, yStartingIdx + ySize)

            val potentialMatch = transformedPattern.map { c ->
                if (c == 'x') x else y
            }.joinToString("")

            if (potentialMatch == string) {
                return if (isFlipped) listOf(y, x) else listOf(x, y)
            }
        }
    } else {
        val xSizeDouble = string.length.toDouble() / charCounts['x']!!
        if (floor(xSizeDouble) != xSizeDouble) return listOf()
        val xSize = xSizeDouble.toInt()
        val x = string.substring(0, xSize)
        val potentialMatch = transformedPattern.map { x }.joinToString("")

        if (potentialMatch == string) {
            return if (isFlipped) listOf("", x) else listOf(x, "")
        }
    }

    return listOf()
}

fun transformPattern(pattern: String): CharArray {
    if (pattern[0] == 'x') return pattern.toCharArray()

    return pattern.map { c ->
        if (c == 'y') 'x' else 'y'
    }.toCharArray()
}

fun countCharsAndGetFirstYPosition(pattern: CharArray, charCounts: MutableMap<Char, Int>): Int? {
    var firstYPosition: Int? = null

    pattern.forEachIndexed { idx, c ->
        charCounts[c] = charCounts[c]!! + 1
        if (c == 'y' && firstYPosition == null) {
            firstYPosition = idx
        }
    }

    return firstYPosition
}