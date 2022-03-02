package facebook.portal.revenueMilestones.solution1

fun main(args : Array<String>) {
    println(getMilestoneDays(arrayOf(10, 20, 30, 40, 50, 60, 70, 80, 90, 100), arrayOf(100, 200, 500)).toList())
}

fun getMilestoneDays(revenues: Array<Int>, milestones: Array<Int>): Array<Int> {
    val revenueSums = MutableList(revenues.size) { 0 }
    revenueSums[0] = revenues[0]

    for (i in 1 until revenues.size) {
        revenueSums[i] = revenueSums[i - 1] + revenues[i]
    }

    return milestones.map { milestone ->
        binarySearch(revenueSums, milestone)
    }.toTypedArray()
}

fun binarySearch(arr: List<Int>, target: Int): Int {
    var left = 0
    var right = arr.lastIndex

    while (left <= right) {
        val middle = (left + right) / 2

        if (arr[middle] < target) {
            left = middle + 1
        } else {
            if (middle == left || arr[middle - 1] < target) {
                return middle + 1
            } else {
                right = middle - 1
            }
        }
    }

    return -1
}
