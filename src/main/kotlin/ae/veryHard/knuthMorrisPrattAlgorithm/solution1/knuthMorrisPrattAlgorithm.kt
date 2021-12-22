package ae.veryHard.knuthMorrisPrattAlgorithm.solution1

// O(n + m) Time | O(m) Space
fun knuthMorrisPrattAlgorithm(string: String, substring: String): Boolean {
    val pattern = buildPattern(substring)
    return doesMatch(string, substring, pattern)
}

// Uses repeated patterns in string to perform string matching
// https://imgur.com/a/OdeXXFE
fun buildPattern(str: String): List<Int> {
    val pattern = MutableList(str.length) { -1 }

    var i = 1
    var j = 0

    while (i < str.length) {
        if (str[i] == str[j]) {
            pattern[i] = j
            i += 1
            j += 1
        } else if (j > 0) {
            j = pattern[j - 1] + 1
        } else {
            i += 1
        }
    }

    return pattern
}

fun doesMatch(str: String, substr: String, pattern: List<Int>): Boolean {
    var i = 0
    var j = 0

    // Until there are enough characters to match substr
    while (i + substr.length - j <= str.length) {
        if (str[i] == substr[j]) {

            if (j == substr.lastIndex) {
                return true
            }

            i += 1
            j += 1
        } else if (j > 0) {
            j = pattern[j - 1] + 1
        } else {
            i += 1
        }
    }

    return false
}

