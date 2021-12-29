package ae.hard.largestRange.solution1

fun largestRange(array: List<Int>): Pair<Int, Int> {
    var bestRange = array[0] to array[0]
    var longestLength = 0
    val nums = array.associateWith { true }.toMutableMap()

    for (num in array) {
        if (!nums[num]!!) {
            continue
        }

        nums[num] = false
        var currentLength = 1
        var left = num - 1
        var right = num + 1

        while (nums.containsKey(left)) {
            nums[left] = false
            currentLength += 1
            left -= 1
        }

        while (nums.containsKey(right)) {
            nums[right] = false
            currentLength += 1
            right += 1
        }

        if (currentLength > longestLength) {
            longestLength = currentLength
            bestRange = left + 1 to right - 1
        }
    }

    return bestRange
}
