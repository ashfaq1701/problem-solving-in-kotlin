package commonAlgo.quickSort

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

    val isLeftSmaller = right - 1 - start < end - right - 1

    if (isLeftSmaller) {
        quickSort(arr, start, right - 1)
        quickSort(arr, right + 1, end)
    } else {
        quickSort(arr, right + 1, end)
        quickSort(arr, start, right - 1)
    }
}

fun swap(arr: Array<Int>, x: Int, y: Int) {
    arr[x] = arr[y].also {
        arr[y] = arr[x]
    }
}