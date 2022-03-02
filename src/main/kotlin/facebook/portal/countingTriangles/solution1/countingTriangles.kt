package facebook.portal.countingTriangles.solution1

fun main(args : Array<String>) {
    println(countDistinctTriangles(
        arrayOf(
            arrayOf(2, 2, 3).toIntArray(),
            arrayOf(3, 2, 2).toIntArray(),
            arrayOf(2, 5, 6).toIntArray()
        )
    ))

    println(countDistinctTriangles(
        arrayOf(
            arrayOf(8, 4, 6).toIntArray(),
            arrayOf(100, 101, 102).toIntArray(),
            arrayOf(84, 93, 173).toIntArray()
        )
    ))

    println(countDistinctTriangles(
        arrayOf(
            arrayOf(5, 8, 9).toIntArray(),
            arrayOf(5, 9, 8).toIntArray(),
            arrayOf(9, 5, 8).toIntArray(),
            arrayOf(9, 8, 5).toIntArray(),
            arrayOf(8, 9, 5).toIntArray(),
            arrayOf(8, 5, 9).toIntArray()
        )
    ))
}

fun countDistinctTriangles(args: Array<IntArray>): Int {
    val triangleSet = mutableSetOf<String>()

    for (triangle in args) {
        val sortedTriangle = triangle.sorted()
        triangleSet.add(sortedTriangle.joinToString("-"))
    }

    return triangleSet.size
}