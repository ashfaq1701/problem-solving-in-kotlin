package ae.medium.mergeOverlappingIntervals.solution1

import kotlin.math.max

fun mergeOverlappingIntervals(intervals: List<List<Int>>): List<List<Int>> {

    // Sort intervals by start time using sortedWith
    // And map to mutable data structures
    val sortedIntervals = intervals.sortedWith(Comparator<List<Int>> { i1, i2 ->
        i1[0].compareTo(i2[0])
    }).map { it.toMutableList() }

    val mergedIntervals = mutableListOf<MutableList<Int>>()
    // Push first interval in merged intervals
    mergedIntervals.add(sortedIntervals[0])

    // Iterate through all of the remaining intervals
    for (i in 1 .. intervals.lastIndex) {
        // Get the next interval
        val nextInterval = sortedIntervals[i]

        // Get last element in last merged intervals
        val currentInterval = mergedIntervals.last()

        // If next interval starts after the current interval finishes
        if (nextInterval[0] > currentInterval[1]) {
            // Push the new interval into merged intervals as is. This is non overlapping
            mergedIntervals.add(nextInterval)
        } else {
            // Current interval and next interval is overlapping. So this will not be inserted as a new interval.
            // Current interval's end time will be the end time of the interval which finishes later
            currentInterval[1] = max(currentInterval[1], nextInterval[1])
        }
    }

    return mergedIntervals.map { it.toList() }
}
