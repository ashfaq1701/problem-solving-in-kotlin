package facebook.portal.oneBillionUsers.solution1

import kotlin.math.pow

fun main(args : Array<String>) {
    println(getBillionUsersDay(arrayOf(1.5f)))
    println(getBillionUsersDay(arrayOf(1.1f, 1.2f, 1.3f)))
    println(getBillionUsersDay(arrayOf(1.01f, 1.02f)))
}

fun getBillionUsersDay(args: Array<Float>): Int {
    var start = 1
    var end = 2000

    val target = 1_000_000_000.0

    while (start < end) {
        val mid = start + (end - start) / 2

        var userCount = 0.0

        for (growthRate in args) {
            userCount += growthRate.pow(mid)
        }

        if (userCount == target) {
            return mid
        }

        if (userCount < target) {
            start = mid + 1
        } else {
            end = mid
        }
    }

    return start
}