package kickStart.y2022.practiceSession1.milkTea

fun parseAndFindMinComplaints() {
    val numTests = readLine()!!.toInt()

    for (i in 1 .. numTests) {
        val (n, m, p) = readLine()!!.split(" ").map { it.toInt() }
        val preferences = (0 until n).map { readLine()!! }
        val forbidden = (0 until m).map { readLine()!! }.toSet()
        val minComplains = getMinComplains(preferences, forbidden, p, n)
        println("Case #$i: $minComplains")
    }
}

fun getMinComplains(preferences: List<String>, forbidden: Set<String>, p: Int, n: Int): Int {

    val countZeroes = MutableList(p) { 0 }
    val countOnes = MutableList(p) { 0 }

    for (preference in preferences) {
        for (j in preference.indices) {
            if (preference[j] == '0') {
                countZeroes[j] += 1
            } else {
                countOnes[j] += 1
            }
        }
    }

    val res = StringBuilder()
    for (j in 0 until p) {
        if (countZeroes[j] > countOnes[j]) {
            res.append('0')
        } else {
            res.append('1')
        }
    }

    var tempStr = res.toString()
    val resultSet = mutableSetOf<String>()
    resultSet.add(tempStr)
    while (tempStr in forbidden) {
        var minComplains = Int.MAX_VALUE

        for (choice in resultSet) {
            for (j in 0 until p) {
                val sb = StringBuilder(choice)
                sb.setCharAt(j, if (sb[j] == '0') '1' else '0')
                val currString = sb.toString()
                if (currString in resultSet) continue

                val newComplains = calculateComplaint(currString, countZeroes, countOnes)
                if (newComplains < minComplains) {
                    minComplains = newComplains
                    tempStr = currString
                }
            }
        }

        if (tempStr in forbidden) {
            resultSet.add(tempStr)
        } else {
            break
        }
    }

    return calculateComplaint(tempStr, countZeroes, countOnes)
}

fun calculateComplaint(currStr: String, zeroesCount: List<Int>, onesCount: List<Int>): Int {
    var complaint = 0

    for (j in currStr.indices) {
        if (currStr[j] == '0') {
            complaint += onesCount[j]
        } else {
            complaint += zeroesCount[j]
        }
    }

    return complaint
}
