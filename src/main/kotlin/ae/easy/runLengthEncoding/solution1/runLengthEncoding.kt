package ae.easy.runLengthEncoding.solution1

fun runLengthEncoding(string: String): String {
    val result = mutableListOf<Char>()
    var length = 1

    for (i in 1 .. string.lastIndex) {
        val currentChar = string[i]
        val prevChar = string[i - 1]

        if (currentChar != prevChar || length == 9) {
            result.add('0' + length)
            result.add(prevChar)
            length = 0
        }

        length += 1
    }

    result.add('0' + length)
    result.add(string.last())

    return String(result.toCharArray())
}
