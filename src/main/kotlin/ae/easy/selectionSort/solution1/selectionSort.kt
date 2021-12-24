package ae.easy.selectionSort.solution1

fun selectionSort(array: MutableList<Int>): List<Int> {
    // Intitialize unsortedStaringIdx. It is the starting index of the unsorted subarray
    var unsortedStaringIdx = 0

    // Loop until the unsorted subarray has only one element. At that point the unsorted subarray is effectively sorted.
    while (unsortedStaringIdx < array.lastIndex) {
        // We assume that the smallest element in the unsorted subarray is the first element inside it.
        var smallestElementIdx = unsortedStaringIdx

        // Loop until we consider all elements in the unsorted sublist as the potential smallest element
        for (i in unsortedStaringIdx + 1 .. array.lastIndex) {
            // If current element is smaller than the current smallest element, than update the smallest element index
            if (array[i] < array[smallestElementIdx]) {
                smallestElementIdx = i
            }
        }

        // We found the smallest element in the unsorted sublist. Swap it with the first element of the unsorted sublist
        swap(unsortedStaringIdx, smallestElementIdx, array)

        // Smallest element in the unsorted sublist is moved to it's first position. So now the unsorted sublist starts from the next position of it.
        unsortedStaringIdx += 1
    }

    return array
}

fun swap(i: Int, j: Int, array: MutableList<Int>) {
    val temp = array[i]
    array[i] = array[j]
    array[j] = temp
}
