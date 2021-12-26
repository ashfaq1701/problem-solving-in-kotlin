package ae.medium.spiralTraverse.solution1

fun spiralTraverse(array: List<List<Int>>): List<Int> {
    var sR = 0
    var eR = array.lastIndex

    var sC = 0
    var eC = array[0].lastIndex

    val result = mutableListOf<Int>()

    while (sR <= eR && sC <= eC) {
        for (c in sC .. eC) {
            result.add(array[sR][c])
        }

        for (r in sR + 1 .. eR) {
            result.add(array[r][eC])
        }

        for (c in eC - 1 downTo sC) {
            if (sR == eR) break
            result.add(array[eR][c])
        }

        for (r in eR - 1 downTo sR + 1) {
            if (sC == eC) break
            result.add(array[r][sC])
        }

        sR += 1
        eR -= 1
        sC += 1
        eC -= 1
    }

    return result
}
