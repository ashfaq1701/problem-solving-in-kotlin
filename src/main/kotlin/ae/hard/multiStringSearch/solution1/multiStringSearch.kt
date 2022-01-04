package ae.hard.multiStringSearch.solution1

// O(bns) Time | O(n) Space
fun multiStringSearch(bigString: String, smallStrings: List<String>): List<Boolean> {
    return smallStrings.map { smallString ->
        isInBigString(bigString, smallString)
    }
}

fun isInBigString(bigString: String, smallString: String): Boolean {
    for (i in bigString.indices) {
        if (i + smallString.length > bigString.length) {
            break
        }

        if (isInBigStringHelper(bigString, smallString, i)) {
            return true
        }
    }

    return false
}

fun isInBigStringHelper(bigString: String, smallString: String, startIdx: Int): Boolean {
    var bigLeftIdx = startIdx
    var bigRightIdx = startIdx + smallString.length - 1

    var smallLeftIdx = 0
    var smallRightIdx = smallString.lastIndex

    while (bigLeftIdx < bigRightIdx) {
        if (bigString[bigLeftIdx] != smallString[smallLeftIdx] || bigString[bigRightIdx] != smallString[smallRightIdx]) {
            return false
        }

        bigLeftIdx += 1
        smallLeftIdx += 1
        bigRightIdx -= 1
        smallRightIdx -= 1
    }

    return true
}
