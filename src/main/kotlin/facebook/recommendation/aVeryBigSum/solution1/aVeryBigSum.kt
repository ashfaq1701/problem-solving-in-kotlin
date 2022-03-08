package facebook.recommendation.aVeryBigSum.solution1

fun aVeryBigSum(ar: Array<Long>): Long {
    var sum = 0L

    for (num in ar) {
        sum += num
    }

    return sum
}
