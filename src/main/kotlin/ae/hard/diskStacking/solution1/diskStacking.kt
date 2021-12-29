package ae.hard.diskStacking.solution1

fun diskStacking(disks: List<List<Int>>): List<List<Int>> {
    val sortedDisks = disks.sortedWith(Comparator<List<Int>> { disk1, disk2 ->
        disk1[2].compareTo(disk2[2])
    })

    val heights = sortedDisks.map { it[2] }.toMutableList()

    val sequences = MutableList(disks.size) { -1 }
    var maxHeightIdx = 0

    for (i in 1 .. disks.lastIndex) {
        val currentDisk = sortedDisks[i]

        for (j in 0 until i) {
            val otherDisk = sortedDisks[j]

            if (currentDisk[0] > otherDisk[0] && currentDisk[1] > otherDisk[1] && currentDisk[2] > otherDisk[2]) {
                if (heights[j] + currentDisk[2] > heights[i]) {
                    heights[i] = heights[j] + currentDisk[2]
                    sequences[i] = j
                }
            }
        }

        if (heights[i] > heights[maxHeightIdx]) {
            maxHeightIdx = i
        }
    }

    return buildSequences(sequences, sortedDisks, maxHeightIdx)
}

fun buildSequences(sequences: List<Int>, disks: List<List<Int>>, maxHeightIdx: Int): List<List<Int>> {
    var currentIdx = maxHeightIdx
    val result = mutableListOf<List<Int>>()

    while (currentIdx != -1) {
        result.add(disks[currentIdx])
        currentIdx = sequences[currentIdx]
    }

    return result.reversed()
}
