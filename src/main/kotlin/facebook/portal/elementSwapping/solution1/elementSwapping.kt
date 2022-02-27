package facebook.portal.elementSwapping.solution1

fun main(args : Array<String>) {
    println(findMinArray(arrayOf(5, 3, 1), 3).toList())
}

fun findMinArray(arr: Array<Int>, k: Int): Array<Int> {
    var minIdx = 0
    var minElement = arr[0]

    var currentIdx = 1
    while (currentIdx <= k && currentIdx < arr.size) {
        if (arr[currentIdx] < minElement) {
            minIdx = currentIdx
            minElement = arr[currentIdx]
        }

        currentIdx += 1
    }

    for (i in minIdx downTo 1) {
        arr[i] = arr[i - 1].also {
            arr[i - 1] = arr[i]
        }
    }

    return arr
}