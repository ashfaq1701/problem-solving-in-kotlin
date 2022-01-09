package mocks.interview.wordSubstitution.solution1

/**
 * Given a list of words and a substitution map transform the given list of words with the given substitution map.
 * The substitution should be idempotent. In other words multiple calls of the function should produce the same output.
 */

/**
val substitutions = mapOf(
    "is" to "was",
    "was" to "then",
    "then" to "be",
    "be" to "but",
    "this" to "that",
    "that" to "them",
    "them" to "it",
    "it" to "their",
    "soft" to "hard",
    "hard" to "rock",
    "rock" to "mountain",
    "true" to "false",
    "false" to "falsy"
)

val testList = listOf("is", "this", "them", "it", "soft", "rock", "mountain", "false", "true", "was", "be", "that", "hard", "falsy", "because")
*/

fun wordSubstitution(wordList: List<String>, substituteMap: Map<String, String>): List<String> {
    val compressedMap = mutableMapOf<String, String>()

    for ((key, _) in substituteMap) {
        compressSubstituteMap(key, substituteMap, compressedMap)
    }

    return wordList.map { word ->
        if (word in compressedMap) compressedMap[word]!! else word
    }
}

fun compressSubstituteMap(key: String, substituteMap: Map<String, String>, compressedMap: MutableMap<String, String>): String {
    if (key in compressedMap) {
        return compressedMap[key]!!
    }

    if (key !in substituteMap) return key

    val compressedValue = compressSubstituteMap(substituteMap[key]!!, substituteMap, compressedMap)
    compressedMap[key] = compressedValue

    return compressedValue
}
