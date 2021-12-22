package ae.veryHard.longestStringChains.solution1

fun longestStringChain(strings: List<String>): List<String> {
    // OR
    // val sortedStrings = strings.sortedBy { it.length }
    // OR
    /* val sortedStrings = strings.sortedWith(Comparator<String> { str1, str2 ->
        str1.length - str2.length
    }) */
    // OR
    /* val sortedStrings = strings.sortedWith(Comparator<String> { str1, str2 ->
        when {
            str1.length > str2.length -> 1
            str1.length < str2.length -> -1
            else -> 0
        }
    })*/

    /*
    New Syntax - Lambda outside, no parenthesis
    val sortedStrings = strings.sortedWith { str1, str2 ->
        str1.length.compareTo(str2.length)
    }
     */

    val sortedStrings = strings.sortedWith(Comparator<String> { str1, str2 ->
        str1.length.compareTo(str2.length)
    })

    val stringChains = sortedStrings.associateWith {
        StringChain("", 1)
    }.toMutableMap()

    sortedStrings.forEach { str ->
        findLongestStringChain(str, stringChains)
    }

    return buildLongestStringChains(strings, stringChains)
}

data class StringChain(var nextStringInChain: String, var stringChainLen: Int)

fun findLongestStringChain(str: String, stringChains: MutableMap<String, StringChain>) {
    for (i in 0 .. str.lastIndex) {
        val smallerSubstring = getSmallerString(str, i)
        if (!stringChains.containsKey(smallerSubstring)) continue
        updateLongestStringChain(str, smallerSubstring, stringChains)
    }
}

fun getSmallerString(str: String, idx: Int) = str.substring(0 until idx) + str.substring(idx + 1)

fun updateLongestStringChain(currentString: String, nextString: String, stringChains: MutableMap<String, StringChain>) {
    val currentStringChainLen = stringChains[currentString]!!.stringChainLen
    val nextStringChainLen = stringChains[nextString]!!.stringChainLen

    if (nextStringChainLen + 1 > currentStringChainLen) {
        stringChains[currentString]?.nextStringInChain = nextString
        stringChains[currentString]?.stringChainLen = nextStringChainLen + 1
    }
}

fun buildLongestStringChains(strings: List<String>, stringChains: Map<String, StringChain>): List<String> {
    var longestChainStartStr = ""
    var longestChainLen = 0

    for (str in strings) {
        val strChain = stringChains[str]!!

        if (strChain.stringChainLen > longestChainLen) {
            longestChainStartStr = str
            longestChainLen = strChain.stringChainLen
        }
    }

    val longestChain = mutableListOf<String>()
    var currentStr = longestChainStartStr

    while (currentStr != "") {
        longestChain.add(currentStr)
        currentStr = stringChains[currentStr]!!.nextStringInChain
    }

    return if (longestChain.size == 1) listOf() else longestChain
}
