package facebook.portal.contiguousSubarrays.solution1

fun main(args : Array<String>) {
    println(countSubarrays(arrayOf(3, 4, 1, 6, 2)).toList())
}

fun countSubarrays(args: Array<Int>): Array<Int> {
    return args.mapIndexed { idx, element ->
        var count = 1

        var left = idx - 1
        while (left >= 0 && args[left] < element) {
            count += 1
            left -= 1
        }

        var right = idx + 1
        while (right < args.size && args[right] < element) {
            count += 1
            right += 1
        }

        count
    }.toTypedArray()
}