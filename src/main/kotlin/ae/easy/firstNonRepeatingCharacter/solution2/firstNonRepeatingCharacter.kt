package ae.easy.firstNonRepeatingCharacter.solution2

fun firstNonRepeatingCharacter(string: String): Int {
    val freq = string.fold(MutableList<Int>(26) { 0 }) { freq, c ->
        freq.also {
            freq[c - 'a'] += 1
        }
    }

    for (i in string.indices) {
        val c = string[i]
        if (freq[c - 'a'] == 1) {
            return i
        }
    }

    return -1
}