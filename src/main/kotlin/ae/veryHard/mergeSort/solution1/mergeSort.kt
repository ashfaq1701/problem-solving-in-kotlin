package ae.veryHard.mergeSort.solution1

fun mergeSort(array: MutableList<Int>): List<Int> {
    if (array.size == 1) return array

    val middle = array.size / 2
    val leftHalf = array.subList(0, middle)
    val rightHalf = array.subList(middle, array.size)
    return mergeSortedArrays(mergeSort(leftHalf), mergeSort(rightHalf))
}

fun mergeSortedArrays(leftHalf: List<Int>, rightHalf: List<Int>): List<Int> {
    var i = 0
    var j = 0

    val resultArray = mutableListOf<Int>()

    while (i < leftHalf.size && j < rightHalf.size) {
        if (leftHalf[i] < rightHalf[j]) {
            resultArray.add(leftHalf[i])
            i += 1
        } else {
            resultArray.add(rightHalf[j])
            j += 1
        }
    }

    while (i < leftHalf.size) {
        resultArray.add(leftHalf[i])
        i += 1
    }

    while (j < rightHalf.size) {
        resultArray.add(rightHalf[j])
        j += 1
    }

    return resultArray
}
