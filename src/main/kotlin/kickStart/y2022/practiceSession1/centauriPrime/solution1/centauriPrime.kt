package kickStart.y2022.practiceSession1.centauriPrime.solution1

fun findRulers() {
    val vowels = setOf('a', 'e', 'i', 'o', 'u')

    val testCaseCount = readLine()!!.toInt()

    for (i in 1 .. testCaseCount) {
        val kingdomName = readLine()!!
        val lastChar = kingdomName[kingdomName.lastIndex].toLowerCase()
        val ruler = if (lastChar in vowels) {
            "Alice"
        } else if (lastChar == 'y') {
            "nobody"
        } else {
            "Bob"
        }

        println("Case #$i: $kingdomName is ruled by $ruler")
    }
}

fun main() {
    findRulers()
}