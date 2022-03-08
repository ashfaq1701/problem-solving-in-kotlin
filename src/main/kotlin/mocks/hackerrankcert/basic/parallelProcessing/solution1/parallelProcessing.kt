package mocks.hackerrankcert.basic.parallelProcessing.solution1

fun minTime(files: Array<Int>, numCores: Int, limit: Int): Long {
    var parallelizationLeft = limit
    files.sortWith(Comparator<Int> { a, b ->
        b.compareTo(a)
    })
    var time = 0L

    for (f in files) {
        if (parallelizationLeft > 0 && f % numCores == 0) {
            time += (f / numCores)
            parallelizationLeft -= 1
        } else {
            time += f
        }
    }

    return time
}