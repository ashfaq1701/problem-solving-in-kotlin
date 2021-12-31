package ae.hard.generateDivTags.solution1

fun generateDivTags(numberOfTags: Int): List<String> {
    val matchedDivTags = mutableListOf<String>()
    generateDivTagsHelper(numberOfTags, numberOfTags, listOf<String>(), matchedDivTags)
    return matchedDivTags
}

fun generateDivTagsHelper(opening: Int, closing: Int, prefix: List<String>, result: MutableList<String>) {
    if (opening > 0) {
        val newPrefix = prefix.plus("<div>")
        generateDivTagsHelper(opening - 1, closing, newPrefix, result)
    }

    // Means that more "opening" has been "used" than closing
    if (closing > opening) {
        val newPrefix = prefix.plus("</div>")
        generateDivTagsHelper(opening, closing - 1, newPrefix, result)
    }

    if (closing == 0) {
        result.add(prefix.joinToString(""))
    }
}
