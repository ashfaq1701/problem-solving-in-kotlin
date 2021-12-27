package ae.medium.reverseWordsInString.solution1

fun reverseWordsInString(string: String): String {
    val words = mutableListOf<String>()
    var start = 0

    for (idx in string.indices) {
        val ch = string[idx]

        if (ch == ' ') {
            words.add(string.substring(start, idx))
            start = idx
        } else if (string[start] == ' ') {
            words.add(" ")
            start = idx
        }
    }

    words.add(string.substring(start))

    words.reverse()

    return words.joinToString("")
}