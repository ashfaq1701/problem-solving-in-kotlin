package ae.hard.interweavingStrings.solution1

fun interweavingStrings(one: String, two: String, three: String): Boolean {
    if (one.length + two.length != three.length) {
        return false
    }
    return areInterwoven(one, two, three, 0, 0)
}

fun areInterwoven(one: String, two: String, three: String, i: Int, j: Int): Boolean {
    val k = i + j

    if (k == three.length) {
        return true
    }

    if (i < one.length && one[i] == three[k]) {
        if (areInterwoven(one, two, three, i + 1, j)) {
            return true
        }
    }

    if (j < two.length && two[j] == three[k]) {
        return areInterwoven(one, two, three, i, j + 1)
    }

    return false
}