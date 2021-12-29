package ae.hard.quickSort.solution1

fun quickSort(array: MutableList<Int>): List<Int> {
    quickSortHelper(array, 0, array.lastIndex)
    return array
}

fun quickSortHelper(array: MutableList<Int>, start: Int, end: Int) {
    // If start meets end, then return
    if (start >= end) {
        return
    }

    // Pick start value as pivot
    var pivot = start
    // Pick second value as left
    var left = start + 1
    // Pick end value as right
    var right = end

    // Until left meets right
    // All the elements smaller than pivot should come before it.
    // All elements greater than pivot should come after it.
    // If this is not the case then we need to swap.
    while (left <= right) {

        // If array element at left is greater than element at pivot
        // And if element at right is less than element at pivot
        // Then we did find two elements which are out of order
        // We will swap them
        if (array[left] > array[pivot] && array[right] < array[pivot]) {
            swap(left, right, array)
        }

        // If element at left is less than or equal to element at pivot
        // Then it's in correct position
        // Otherwise we will freeze left until we find a right which is also in incorrect position
        if (array[left] <= array[pivot]) {
            left += 1
        }

        // If element at right is greater than or equal to element at pivot
        // Then it's in correct position
        // Otherwise we will freeze right until we find a left which is also in incorrect position
        if (array[right] >= array[pivot]) {
            right -= 1
        }
    }

    // Swap element at pivot and at right
    // Now we can say that element at right is in it's correct sorted position
    swap(pivot, right, array)

    // New pivot is right, so which half of the array is smaller?
    // We need to divide the array around it
    // Is it right - 1 - start or end - (right + 1)
    val isLeftSmaller = right - 1 - start < end - (right + 1)

    // If left part is smaller
    // then call quick sort recursively on the left part first and then on the right part
    // Otherwise call quick sort recursively on the right part first and then on the left part
    if (isLeftSmaller) {
        quickSortHelper(array, start, right - 1)
        quickSortHelper(array, right + 1, end)
    } else {
        quickSortHelper(array, right + 1, end)
        quickSortHelper(array, start, right - 1)
    }
}

fun swap(leftIdx: Int, rightIdx: Int, array: MutableList<Int>) {
    array[leftIdx] = array[rightIdx].also {
        array[rightIdx] = array[leftIdx]
    }
}
