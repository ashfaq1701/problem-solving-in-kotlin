package facebook.portal.minimizingPermutations.solution1

import java.util.LinkedList

fun main(args : Array<String>) {
    println(minOperations(arrayOf(1, 2, 5, 4, 3)))
    println(minOperations(arrayOf(3, 1, 2)))
}

fun minOperations(arr: Array<Int>): Int {
    val sortedArray = arr.sorted().toTypedArray()

    val visited = mutableListOf<Array<Int>>()

    val queue = LinkedList<Pair<Array<Int>, Int>>()
    queue.add(arr to 0)

    while (queue.isNotEmpty()) {
        val (currentPermutation, level) = queue.poll()

        if (currentPermutation in visited) continue

        visited.add(currentPermutation)

        if (currentPermutation.contentEquals(sortedArray)) {
            return level
        }

        for (i in currentPermutation.indices) {
            for (j in i + 1 until currentPermutation.size) {
                val reversedPermutation = reverse(currentPermutation, i, j)
                if (reversedPermutation !in visited) {
                    queue.add(reversedPermutation to level + 1)
                }
            }
        }
    }

    return -1
}

fun reverse(arr: Array<Int>, start: Int, end: Int): Array<Int> {
    val copiedArray = arr.map { it }.toTypedArray()

    var left = start
    var right = end

    while (left < right) {
        copiedArray[left] = copiedArray[right].also {
            copiedArray[right] = copiedArray[left]
        }

        left += 1
        right -= 1
    }

    return copiedArray
}