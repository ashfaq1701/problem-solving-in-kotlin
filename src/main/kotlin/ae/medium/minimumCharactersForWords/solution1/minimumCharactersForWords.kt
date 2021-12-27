package ae.medium.minimumCharactersForWords.solution1

import kotlin.math.max

fun minimumCharactersForWords(words: List<String>): List<Char> {
    val maxFrequencies = mutableMapOf<Char, Int>()

    for (word in words) {
        val charFrequencies = computeCharFrequencies(word)
        updateMaxFrequencies(charFrequencies, maxFrequencies)
    }
    return makeListFromMaxFrequencies(maxFrequencies)
}

fun computeCharFrequencies(string: String): Map<Char, Int> {
    val charFreq = mutableMapOf<Char, Int>()

    for (c in string) {
        if (!charFreq.containsKey(c)) {
            charFreq[c] = 0
        }

        charFreq[c] = charFreq[c]!! + 1
    }

    return charFreq
}

fun updateMaxFrequencies(charFrequencies: Map<Char, Int>, maxFrequencies: MutableMap<Char, Int>) {
    charFrequencies.forEach { (char, freq) ->
        if (!maxFrequencies.containsKey(char)) {
            maxFrequencies[char] = freq
        } else {
            maxFrequencies[char] = max(maxFrequencies[char]!!, freq)
        }
    }
}

fun makeListFromMaxFrequencies(frequencies: Map<Char, Int>): List<Char> {
    val charList = mutableListOf<Char>()

    frequencies.forEach { (char, freq) ->
        charList.addAll(List(freq) { char })
    }

    return charList
}