package facebook.portal.passingYearbooks.solution1

fun main(args : Array<String>) {
    println(findSignatureCounts(arrayOf(1, 2)).toList())
}

fun findSignatureCounts(arr: Array<Int>): Array<Int> {
    val signCounts = MutableList(arr.size) { 1 }

    for (idx in arr.indices) {
        var currentIdx = idx

        while (arr[currentIdx] != idx + 1) {
            currentIdx = arr[currentIdx] - 1
            signCounts[idx] += 1
        }
    }

    return  signCounts.toTypedArray()
}