package ae.veryHard.numberOfBinaryTreeTopologies.solution3

// O(n ^ 2) Time | O(n) Space
fun numberOfBinaryTreeTopologies(n: Int): Int {
    val memo = mutableListOf<Int>(1)

    for (m in 1 .. n) {

        var numTopologies = 0

        for (l in 0 until m) {
            val r = m - 1 - l
            val fl = memo[l]
            val fr = memo[r]
            numTopologies += (fl * fr)
        }

        memo.add(numTopologies)
    }

    return memo.last()
}
