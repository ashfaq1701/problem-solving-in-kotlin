package ae.hard.interweavingStrings.solution2

fun interweavingStrings(one: String, two: String, three: String): Boolean {
    if (one.length + two.length != three.length) {
        return false
    }

    // Either one of i and j can go out of bound while the other is in bound.
    // So we keep the cache size one more than the string sizes.
    val cache = MutableList(one.length + 1) {
        MutableList(two.length + 1) { -1 }
    }
    return areInterwoven(one, two, three, 0, 0, cache)
}

fun areInterwoven(one: String, two: String, three: String, i: Int, j: Int, cache: MutableList<MutableList<Int>>): Boolean {
    if (cache[i][j] != -1) {
        return cache[i][j] == 1
    }

    val k = i + j

    if (k == three.length) {
        return true
    }

    if (i < one.length && one[i] == three[k]) {
        val areNextInterwoven = areInterwoven(one, two, three, i + 1, j, cache)
        fillCachePosition(cache, i, j, areNextInterwoven)

        if (cache[i][j] == 1) {
            return true
        }
    }

    if (j < two.length && two[j] == three[k]) {
        val areNextInterwoven = areInterwoven(one, two, three, i, j + 1, cache)
        fillCachePosition(cache, i, j, areNextInterwoven)
        return cache[i][j] == 1
    }

    cache[i][j] = 0
    return false
}

fun fillCachePosition(cache: MutableList<MutableList<Int>>, i: Int, j: Int, value: Boolean) {
    cache[i][j] = if (value) 1 else 0
}