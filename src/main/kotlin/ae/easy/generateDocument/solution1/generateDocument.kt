package ae.easy.generateDocument.solution1

fun generateDocument(characters: String, document: String): Boolean {
    val charFreq = mutableMapOf<Char, Int>()

    for (c in characters) {
        if (!charFreq.containsKey(c)) {
            charFreq[c] = 0
        }

        charFreq[c] = charFreq[c]!! + 1
    }

    for (c in document) {
        if (!charFreq.containsKey(c) || charFreq[c]!! == 0) {
            return false
        }

        charFreq[c] = charFreq[c]!! - 1
    }

    return true
}
