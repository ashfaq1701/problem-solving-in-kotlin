package ae.hard.quickSelect.solution1

fun quickselect(array: MutableList<Int>, k: Int): Int {
    val position = k - 1
    return quickselectHelper(array, 0, array.lastIndex, position)
}

fun quickselectHelper(array: MutableList<Int>, startIdx: Int, endIdx: Int, position: Int): Int {
    var runningStartIdx = startIdx
    var runningEndIdx = endIdx

    while (true) {
        if (runningStartIdx > runningEndIdx) {
            throw Exception("Illegal state")
        }

        // Pick start value as pivot
        val pivotIdx = runningStartIdx
        // Pick second value as left
        var leftIdx = runningStartIdx + 1
        // Pick end value as right
        var rightIdx = runningEndIdx

        // Until left meets right
        // All the elements smaller than pivot should come before it.
        // All elements greater than pivot should come after it.
        // If this is not the case then we need to swap.
        while (leftIdx <= rightIdx) {

            // If array element at left is greater than element at pivot
            // And if element at right is less than element at pivot
            // Then we did find two elements which are out of order
            // We will swap them
            if (array[leftIdx] > array[pivotIdx] && array[rightIdx] < array[pivotIdx]) {
                swap(leftIdx, rightIdx, array)
            }

            // If element at left is less than or equal to element at pivot
            // Then it's in correct position
            // Otherwise we will freeze left until we find a right which is also in incorrect position
            if (array[leftIdx] <= array[pivotIdx]) {
                leftIdx++
            }

            // If element at right is greater than or equal to element at pivot
            // Them it's in correct position
            // Otherwise we will freeze right until we find a left which is also in incorrect position
            if (array[rightIdx] >= array[pivotIdx]) {
                rightIdx--
            }
        }

        // Swap element at pivot and at right
        // Now we can say that element at right is in it's correct sorted position
        swap(pivotIdx, rightIdx, array)

        // element in right is the kth smallest integer
        if (rightIdx === position) {
            return array[rightIdx]

            // rightIdx is in it's correct sorted position
            // ALSO All the number before rightIdx is smaller than it and all the number after it is greater than it
            // If rightIdx is greater then position this means kth smallest number should be smaller and to the left of rightIdx
            // So look at the left
        } else if (rightIdx > position) {
            runningEndIdx = rightIdx - 1
            // Otherwise element at rightIdx is smaller than kth smallest number
            // So look at right of rightIdx
        } else {
            runningStartIdx = rightIdx + 1
        }
    }
}

fun swap(leftIdx: Int, rightIdx: Int, array: MutableList<Int>) {
    array[leftIdx] = array[rightIdx].also {
        array[rightIdx] = array[leftIdx]
    }
}