package kickStart.y2022.practiceSession1.sampleProblem

fun distributeCandies() {
    val numTestCases = readLine()!!.toInt()
    var currentTest = 1

    while (currentTest <= numTestCases) {
        val (n, m) = readLine()!!.split(" ").map{ it.toInt() }
        val candies = readLine()!!.split(" ").map { it.toInt() }
        val totalCandies = candies.sum()
        val leftOver = totalCandies % m
        println("Case #$currentTest: $leftOver")
    }
}
