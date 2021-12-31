package ae.hard.shortenPath.solution1

fun isImportantToken(token: String) =
    token.isNotEmpty() && token != "."

fun shortenPath(path: String): String {

    val filteredTokens = path.split("/").filter(::isImportantToken)

    val stack = mutableListOf<String>()

    if (path.startsWith('/')) {
        stack.add("")
    }

    filteredTokens.forEach { token ->
        if (token == "..") {
            if (stack.isEmpty() || stack.last() == "..") {
                stack.add(token)
            } else if (stack.last() != "") {
                stack.removeAt(stack.lastIndex)
            }
        } else {
            stack.add(token)
        }
    }

    if (stack.size == 1 && stack[0] == "") {
        return "/"
    }

    return stack.joinToString("/")
}
