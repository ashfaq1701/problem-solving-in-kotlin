package ae.medium.reverseWordsInString.solution2

fun reverseWordsInString(string: String): String {
    val charList = string.toCharArray().toMutableList()
    reverseSegment(charList, 0, charList.lastIndex)

    var startIdx = 0
    var endIdx = 0

    while (endIdx < charList.size) {
        endIdx = startIdx

        while (endIdx < charList.size && charList[endIdx] != ' ') {
            endIdx += 1
        }

        reverseSegment(charList, startIdx, endIdx - 1)

        startIdx = endIdx + 1
    }

    return String(charList.toCharArray())
}

fun reverseSegment(charList: MutableList<Char>, left: Int, right: Int) {
    var leftIdx = left
    var rightIdx = right

    while (leftIdx < rightIdx) {
        charList[leftIdx] = charList[rightIdx].also {
            charList[rightIdx] = charList[leftIdx]
        }

        leftIdx += 1
        rightIdx -= 1
    }
}