package ae.easy.insertionSort.solution1

fun insertionSort(array: MutableList<Int>): List<Int> {
    // [8,             |         5, 2, 9, 5, 6, 3]
    // sorted sublist            unsorted sublist
    // At the beginning sorted sublist has only one number, the first element
    // Insert rest of the elements in the sorted sublist one by one
    for (i in 1 .. array.lastIndex) {
        // Track the elements, when they are continuously being swapped inside of the sorted sublist
        var j = i

        // While we didn't reach the very beginning of the array (sorted subarray) and the number at index j is out of order
        while (j > 0 && array[j] < array[j - 1]) {
            // Then swap the number
            swap(j, j - 1, array)
            // And j decrements to the previous index to keep track of the number being inserted
            j -= 1
        }
    }

    return array
}

fun swap(i: Int, j: Int, array: MutableList<Int>) {
    val temp = array[i]
    array[i] = array[j]
    array[j] = temp
}
