package ae.veryHard.numberOfBinaryTreeTopologies.solution1

// O((n*(2n)!)/(n!(n + 1)!)) Time | O(n) space
fun numberOfBinaryTreeTopologies(n: Int): Int {
    if (n == 0) return 1

    var topologies = 0

    for (l in 0 until n) {
        val r = n - 1 - l // 1 less for the root
        val fl = numberOfBinaryTreeTopologies(l)
        val fr = numberOfBinaryTreeTopologies(r)

        topologies += (fl * fr)
    }

    return topologies
}
