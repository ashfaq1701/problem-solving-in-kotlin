package ae.medium.singleCycleCheck.solution1

fun hasSingleCycle(array: List<Int>): Boolean {
    var numElementsVisited = 0

    // We should start checking from index 0
    var currentIdx = 0

    // Until we visit ALL the elements
    while (numElementsVisited < array.size) {

        // If we visited some elements (not all of them - imposed by the enclosing while loop)
        // But we still came back to the starting index 0
        if (numElementsVisited > 0 && currentIdx == 0)
            return false

        numElementsVisited += 1

        // Get next index and set as currentIdx
        currentIdx = getNextIdx(array, currentIdx)
    }

    // Once the while loop breaks we visited all elements
    // Now to say that if we have a single cycle we need to check if we are back at starting index 0.
    return currentIdx == 0
}

fun getNextIdx(array: List<Int>, currentIdx: Int): Int {
    val arrayLen = array.size
    val jumpValue = array[currentIdx]

    // To roll over at an index within bound we use modulus by array length
    val nextIdx = (currentIdx + jumpValue) % arrayLen

    // If nextIdx is negative (can occur for some negative element)
    // we need to bring them back into the bound by adding arrayLen
    return if (nextIdx >= 0)  nextIdx else nextIdx + arrayLen
}
