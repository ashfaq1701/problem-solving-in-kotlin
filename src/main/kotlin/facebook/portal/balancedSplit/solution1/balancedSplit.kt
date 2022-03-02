package facebook.portal.balancedSplit.solution1

fun main(args : Array<String>) {
    println(balancedSplitExists(arrayOf(1, 5, 7, 1)))
    println(balancedSplitExists(arrayOf(12, 7, 6, 7, 6)))
}

fun balancedSplitExists(args: Array<Int>): Boolean {
    args.sort()
    val totalSum = args.sum()

    var rightSum = 0
    for (idx in args.lastIndex downTo 0) {
        val currentValue = args[idx]
        rightSum += currentValue

        if ((idx == 0 || args[idx - 1] != currentValue) && totalSum - rightSum == rightSum) {
            return true
        }
    }

    return false
}