package ae.veryHard.countInversions.solution1

fun countInversions(array: MutableList<Int>): Int {
    return countSubarrayInversions(array, 0, array.size)
}

// Count inversion in a subarray bounded with left and right indexes
// Left is inclusive, right is exclusive
fun countSubarrayInversions(array: MutableList<Int>, left: Int, right: Int): Int {

    // If the array size is 1 or less then return 0.
    // An array of length 1 or 0 will have 0 inversions by definition.
    if (right - left <= 1)
        return 0

    // Get the mid pointer
    val mid = left + (right - left) / 2

    val leftInversions = countSubarrayInversions(array, left, mid)

    val rightInversions = countSubarrayInversions(array, mid, right)

    // Get number of inversions while merging the two halves.
    val mergedInversions = mergeAndCountInversions(array, left, mid, right)

    // Finally here we return inversions count in left, right and merge step.
    return leftInversions + rightInversions + mergedInversions
}

// Count inversions while merging two *SORTED* subarrays
// right is exclusive
fun mergeAndCountInversions(array: MutableList<Int>, left: Int, mid: Int, right: Int): Int {
    // idx1 starts at left and idx2 starts at mid.
    var idx1 = left
    var idx2 = mid

    // We assume inversions count is 0 and we declare the array which will be used to merge the sorted arrays.
    var inversions = 0
    val sortedArray = mutableListOf<Int>()

    // While both subarrays has element
    while (idx1 < mid && idx2 < right) {
        // If the next number to insert is from the first subarray
        // e.g. number at first subarray's current pointer is less than or equal to the number at second array's current pointer
        // Then there is no inversion here. Just insert the number in the merged array.
        if (array[idx1] <= array[idx2]) {
            sortedArray.add(array[idx1])
            idx1 += 1

            // Else the next number to insert is from the second subarray
            // e.g. number at second subarray's current pointer is less than the number at first array's current pointer
            // Then there are inversions
            // And the count of inversions here is simply the number of elements from the first subarray which are yet to be inserted in the merged array.
            // That is (end idx of first subarray + 1) - current idx in the first subarray
            // After incrementing inversion count with this value insert the number in the merged array.
        } else {
            inversions += (mid - idx1)
            sortedArray.add(array[idx2])
            idx2 += 1
        }
    }

    // Insert all remaining elements from the first subarray into the merged array.
    while (idx1 < mid) {
        sortedArray.add(array[idx1])
        idx1 += 1
    }

    // Insert all remaining elements from the second subarray into the merged array.
    while (idx2 < right) {
        sortedArray.add(array[idx2])
        idx2 += 1
    }

    // Copy the sorted elements into the corresponding segment of the main array.
    // Main array's current segment of operation starts at left
    // So copy the sorted elements into the main array starting from `left` index.
    for (i in 0 .. sortedArray.lastIndex) {
        array[left + i] = sortedArray[i]
    }

    // Return accumulated count of inversions.
    return inversions
}