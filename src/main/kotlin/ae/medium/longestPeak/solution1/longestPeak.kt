package ae.medium.longestPeak.solution1

import kotlin.math.max

fun longestPeak(array: List<Int>): Int {

    var longestPeakWidth = 0

    var i = 1

    // Loop starts from 1 and goes till array.size() - 1, so every element has both i - 1 and i + 1 positions
    while (i < array.lastIndex) {

        // If an element is greater than both of it's previous and next elements then we have a peak
        val isPeak = array[i - 1] < array[i] && array[i] > array[i + 1]

        // If current element is not a peak then continue
        if (!isPeak) {
            i += 1
            continue
        }

        // LeftIdx starts from 2 elements before the peak
        var leftIdx = i - 2
        // When leftIdx is greater than zero and it continues the peak
        // i.e it's smaller than it's next element, till then decrement it
        // i.e try to grow the peak to the left
        while (leftIdx >= 0 && array[leftIdx] < array[leftIdx + 1]) {
            leftIdx -= 1
        }

        // RightIdx starts from 2 elements after the peak
        var rightIdx = i + 2
        // When rightIdx is less than array size and it continues the peak
        // i.e it's smaller than it's previous element, till then increment it
        // i.e try to grow the peak to the right
        while (rightIdx < array.size && array[rightIdx] < array[rightIdx - 1]) {
            rightIdx += 1
        }

        // Peak width calculation formula
        // rightIdx and leftIdx at this point isn't contained in the peak
        // If they were contained in the peak then the formula could be rightIdx - leftIdx + 1.
        val currentPeakWidth = rightIdx - leftIdx - 1
        longestPeakWidth = max(currentPeakWidth, longestPeakWidth)

        // Once the current peak is over, rightIdx will be the potential next peak
        // Try to grow it and potentially update longest peak width in next iteration
        i = rightIdx
    }

    return longestPeakWidth
}
