package ae.easy.tandemBicycle.solution1

import kotlin.math.max

fun tandemBicycle(redShirtSpeeds: MutableList<Int>, blueShirtSpeeds: MutableList<Int>, fastest: Boolean): Int {
    redShirtSpeeds.sort()

    if (fastest) {
        blueShirtSpeeds.sort()
    } else {
        blueShirtSpeeds.sortWith(Comparator<Int> { i1, i2 ->
            i2.compareTo(i1)
        })
    }

    var totalSpeed = 0

    for (i in 0 .. redShirtSpeeds.lastIndex) {
        val redShirtSpeed = redShirtSpeeds[i]
        val blueShirtSpeed = blueShirtSpeeds[blueShirtSpeeds.size - i - 1]
        totalSpeed += max(redShirtSpeed, blueShirtSpeed)
    }

    return totalSpeed
}
