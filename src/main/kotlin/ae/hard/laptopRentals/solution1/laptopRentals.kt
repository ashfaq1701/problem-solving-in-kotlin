package ae.hard.laptopRentals.solution1

import java.util.PriorityQueue
import kotlin.Comparator

fun laptopRentals(times: List<List<Int>>): Int {

    // Sort the time intervals according to their start time.
    val sortedTimes = times.sortedWith(Comparator<List<Int>> { time1, time2 ->
        time1[0].compareTo(time2[0])
    })

    // Create a heap as the laptop pool
    // Min Heap
    val pool = PriorityQueue<Int>()

    // For each of the sorted intervals
    for (time in sortedTimes) {

        // If pool size is greater than 0
        // and pool's top end-time (earliest end time) is less than or equal to the next rental's start-time
        // Then we can use the same laptop.
        if (pool.isNotEmpty() && pool.peek() <= time[0]) {
            // So remove the end time from the pool.
            pool.poll()
        }

        // Regardless of anything specific we need to insert current interval's end time into the pool
        // Depending on the previous if statement it can be reassignment of an existing laptop or a new laptop
        // Existing laptop when it's previous rental ended before the start of the current rental.
        pool.add(time[1])
    }

    // Finally the pool size is the required number of laptops.
    return pool.size
}
