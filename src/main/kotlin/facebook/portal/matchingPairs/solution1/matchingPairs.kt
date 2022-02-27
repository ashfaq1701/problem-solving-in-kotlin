package facebook.portal.matchingPairs.solution1

fun main(args : Array<String>) {
    println(matchingPairs("mno", "mno"))
}

fun matchingPairs(s: String, t: String): Int {
    val unmatchedS = mutableSetOf<Char>()
    val unmatchedT = mutableSetOf<Char>()

    var matchCount = 0

    for (i in s.indices) {
        if (s[i] != t[i]) {
            unmatchedS.add(s[i])
            unmatchedT.add(t[i])
        } else {
            matchCount += 1
        }
    }

    if (unmatchedS.isEmpty()) {
        return s.length - 2
    }

    var matchAfterSwap = 0
    for (ch in unmatchedS) {
        if (ch in unmatchedT) {
            matchAfterSwap += 1
        }

        if (matchAfterSwap == 2) break
    }

    return matchCount + matchAfterSwap
}
