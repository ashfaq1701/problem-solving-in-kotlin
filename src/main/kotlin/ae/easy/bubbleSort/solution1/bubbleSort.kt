package ae.easy.bubbleSort.solution1

fun bubbleSort(array: MutableList<Int>): List<Int> {
    // At the point our sorting loop didn't have any swap, we are done and our array is sorted.
    // This variable will facilitate that
    var isSorted = false

    // This variable is used for optimization.
    // After each iteration the last element in the array is the largest element (i.e. in it's sorted position)
    // So we don't need to consider it in the next iteration. It's already in it's appropriate place. To facilitate this, we use this counter variable.
    var counter = 0

    while (!isSorted) {
        isSorted = true

        // We need to loop till array.size() - 1 - counter
        // array.size() - 1 because we will compare i with i + 1
        // counter because in each step we have to skip the last element
        for (i in 0 until array.lastIndex - counter) {
            if (array[i] > array[i + 1]) {
                swap(i, i + 1, array)

                // In this step we made a swap. So the array is not sorted
                isSorted = false
            }
        }

        // After each step we increase the counter to count the number of elements reached their sorted position so that we can skip them.
        counter += 1
    }

    return array
}

fun swap(i: Int, j: Int, array: MutableList<Int>) {
    val temp = array[i]
    array[i] = array[j]
    array[j] = temp
}
