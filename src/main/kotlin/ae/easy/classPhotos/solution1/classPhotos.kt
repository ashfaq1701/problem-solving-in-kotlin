package ae.easy.classPhotos.solution1

fun classPhotos(redShirtHeights: MutableList<Int>, blueShirtHeights: MutableList<Int>): Boolean {

    redShirtHeights.sort()
    blueShirtHeights.sort()

    if (redShirtHeights.size == 0) return true

    val firstRow = if (redShirtHeights[0] < blueShirtHeights[0]) redShirtHeights else blueShirtHeights
    val secondRow = if (firstRow == redShirtHeights) blueShirtHeights else redShirtHeights

    for (i in 0 .. firstRow.lastIndex) {
        if (firstRow[i] >= secondRow[i]) {
            return false
        }
    }

    return true
}
