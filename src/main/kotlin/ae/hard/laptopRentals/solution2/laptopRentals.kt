package ae.hard.laptopRentals.solution2

fun laptopRentals(times: List<List<Int>>): Int {
    // We don't need start and end times paired up here.
    // We need sorted versions of start and end times
    // to know which rental is starting next and which rental is ending next separately
    val startTimes = times.map{ it[0] }.sorted()
    val endTimes = times.map { it[1] }.sorted()

    // Number of laptops for rental
    var laptops = 0

    // pointers to the sorted arrays startTimes and endTimes
    var startIdx = 0
    var endIdx = 0

    // We loop until startIdx is in bounds
    // endIdx progresses (or stands) along with startIdx, so we don't need to check it
    while (startIdx < startTimes.size) {
        // If the earliest start time starts at or after the earliest end time
        // Then we have a laptop freed up
        if (startTimes[startIdx] >= endTimes[endIdx]) {
            // We decrement laptops as a count of the freed up laptop
            laptops -= 1
            // We will consider the next ending time on next iteration
            endIdx += 1
        }

        // Anyway we need to allocate current rental, so we always increment laptops count
        laptops += 1
        // We increment index for start indexes
        startIdx += 1
    }

    // We return laptop count
    return laptops
}
