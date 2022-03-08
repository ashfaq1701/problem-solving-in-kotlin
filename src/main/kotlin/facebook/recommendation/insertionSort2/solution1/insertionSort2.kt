package facebook.recommendation.insertionSort2.solution1

fun insertionSort2(n: Int, arr: Array<Int>): Unit {
    for (i in 1 until arr.size) {
        for (j in i - 1 downTo 0) {
            if (arr[j] < arr[j + 1]) break

            arr[j] = arr[j + 1].also {
                arr[j + 1] = arr[j]
            }
        }

        println(arr.toList().joinToString(" "))
    }
}

fun main(args: Array<String>) {
    val n = readLine()!!.trim().toInt()

    val arr = readLine()!!.trimEnd().split(" ").map{ it.toInt() }.toTypedArray()

    insertionSort2(n, arr)
}