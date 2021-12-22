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

    // Sort the strings array by length for ease in implementation. Sorting guarantees that
    // the shorter string is computed before the present longer string
    val sortedStrings = strings.sortedWith(Comparator<String> { str1, str2 ->
        str1.length.compareTo(str2.length)
    })

    // Iterate through each string and prepare the map of string -> stringChain object
    val stringChains = sortedStrings.associateWith {
        StringChain("", 1)
    }.toMutableMap()

    // Find the longest string chain for each string from shortest to longest string
    sortedStrings.forEach { str ->
        findLongestStringChain(str, stringChains)
    }

    // Find the overall longest string chain, build the sequence and return the chain
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

    // Does using this next string makes the chain longer?
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
