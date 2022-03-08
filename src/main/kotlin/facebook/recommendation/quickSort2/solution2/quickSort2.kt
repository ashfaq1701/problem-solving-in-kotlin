package facebook.recommendation.quickSort2.solution2

fun quickSort(arr: MutableList<Int>) {

    val pivot = arr[0]
    val lowers = mutableListOf<Int>()
    val highers = mutableListOf<Int>()

    for (i in 1 until arr.size) {
        if (arr[i] < pivot) {
            lowers.add(arr[i])
        } else {
            highers.add(arr[i])
        }
    }

    if (lowers.size > 1) {
        quickSort(lowers)
        println(lowers.joinToString(" "))
    }

    if (highers.size > 1) {
        quickSort(highers)
        println(highers.joinToString(" "))
    }

    var currentIdx = 0
    for (num in lowers) {
        arr[currentIdx] = num
        currentIdx += 1
    }

    arr[currentIdx] = pivot
    currentIdx += 1

    for (num in highers) {
        arr[currentIdx] = num
        currentIdx += 1
    }
}

fun main(args: Array<String>) {
    val n = readLine()!!.toInt()
    val arr = readLine()!!.split(" ").map { it.toInt() }.toMutableList()
    quickSort(arr)
    println(arr.joinToString(" "))
}
