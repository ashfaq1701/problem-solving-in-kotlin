package facebook.recommendation.leftRotation.solution1

fun rotLeft(a: Array<Int>, d: Int): Array<Int> {
    val rotated = Array<Int>(a.size){ 0 }

    for (i in a.indices) {
        val rotatedIdx = (i + a.size - d) % a.size
        rotated[rotatedIdx] = a[i]
    }

    return rotated
}