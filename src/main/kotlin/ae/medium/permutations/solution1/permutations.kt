package ae.medium.permutations.solution1

// [1, 2, 3]
// permutationsHelper(0, [1, 2, 3], permutations)
//   j = 0
//   [1, 2, 3]
//   permutationsHelper(1, [1, 2, 3], permutations)
//     j = 1
//     [1, 2, 3]
//     permutationsHelper(2, [1, 2, 3], permutations)
//       j = 2
//       [1, 2, 3]
//       permutationsHelper(3, [1, 2, 3], permutations) - PUSH
//       [1, 2, 3]
//     [1, 2, 3]
//
//     j = 2
//     [1, 3, 2]
//     permutationsHelper(2, [1, 3, 2], permutations)
//       j = 2
//       [1, 3, 2]
//       permutationsHelper(3, [1, 3, 2], permutations) - PUSH
//       [1, 3, 2]
//     [1, 2, 3]
//	 [1, 2, 3]
//
//   j = 1
//   [2, 1, 3]
//   permutationsHelper(1, [2, 1, 3], permutations)
//     j = 1
//     [2, 1, 3]
//     permutationsHelper(2, [2, 1, 3], permutations)
//       j = 2
//       [2, 1, 3]
//       permutationsHelper(3, [2, 1, 3], permutations) - PUSH
//       [2, 1, 3]
//     [2, 1, 3]
//
//     j = 2
//     [2, 3, 1]
//     permutationsHelper(2, [2, 3, 1], permutations)
//       j = 2
//       [2, 3, 1]
//       permutationsHelper(3, [2, 3, 1], permutations) - PUSH
//       [2, 3, 1]
//     [2, 1, 3]
//   [1, 2, 3]
//
//   j = 2
//   [3, 2, 1]
//   permutationsHelper(1, [3, 2, 1], permutations)
//     j = 1
//     [3, 2, 1]
//     permutationsHelper(2, [3, 2, 1], permutations)
//       j = 2
//       [3, 2, 1]
//       permutationsHelper(3, [3, 2, 1], permutations) - PUSH
//       [3, 2, 1]
//     [3, 2, 1]
//
//     j = 2
//     [3, 1, 2]
//     permutationsHelper(2, [3, 1, 2], permutations)
//       j = 2
//       [3, 1, 2]
//       permutationsHelper(3, [3, 1, 2], permutations) - PUSH
//       [3, 1, 2]
//     [3, 2, 1]
//   [1, 2, 3]

fun getPermutations(array: List<Int>): List<List<Int>> {
    val permutations = mutableListOf<List<Int>>()

    if (array.isEmpty()) return permutations

    permutationsHelper(0, array.toMutableList(), permutations)
    return permutations
}

fun permutationsHelper(i: Int, array: MutableList<Int>, permutations: MutableList<List<Int>>) {
    if (i == array.size) {
        permutations.add(array.toList())
    }

    for (j in i .. array.lastIndex) {
        swap (array, i, j)
        permutationsHelper(i + 1, array, permutations)
        swap(array, j, i)
    }
}

fun swap(array: MutableList<Int>, i: Int, j: Int) {
    array[i] = array[j].also {
        array[j] = array[i]
    }
}