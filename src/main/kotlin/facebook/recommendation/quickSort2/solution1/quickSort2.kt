package facebook.recommendation.quickSort2.solution1

fun quickSort(arr: Array<Int>, start: Int, end: Int) {
    if (start >= end) return

    val pivot    = start
    var left     = start + 1
    var right    = end

    while (left <= right) {

        if (arr[left] > arr[pivot] && arr[right] < arr[pivot]) {
            swap(arr, left, right)
        }

        if (arr[left] <= arr[pivot]) left += 1

        if (arr[right] >= arr[pivot]) right -= 1
    }

    swap(arr, pivot, right)

    quickSort(arr, start, right - 1)
    quickSort(arr, right + 1, end)

    println(arr.sliceArray(start .. end).toList().joinToString(" "))
}

fun swap(arr: Array<Int>, x: Int, y: Int) {
    arr[x] = arr[y].also {
        arr[y] = arr[x]
    }
}

fun main(args: Array<String>) {
    val n = readLine()!!.toInt()
    val arr = readLine()!!.split(" ").map { it.toInt() }.toTypedArray()
    quickSort(arr, 0, arr.lastIndex)
}