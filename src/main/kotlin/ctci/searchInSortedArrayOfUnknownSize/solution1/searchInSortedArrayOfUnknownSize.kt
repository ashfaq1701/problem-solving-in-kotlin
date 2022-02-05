package ctci.searchInSortedArrayOfUnknownSize.solution1

class ArrayReader {
    fun get(index: Int): Int { return 0 }
}

class Solution {
    fun search(reader: ArrayReader, target: Int): Int {
        var left = 0
        var right = 1

        while (reader.get(right) < target) {
            left = right
            // Or right = right * 2
            right = right shl 2
        }

        while (left <= right) {
            val mid = (left + right) / 2

            if (reader.get(mid) == target) {
                return mid
            } else if (target < reader.get(mid)) {
                right = mid - 1
            } else {
                left = mid + 1
            }
        }

        return -1
    }
}