package ae.veryHard.minimumAreaRectangle.solution2

import kotlin.math.min

fun minimumAreaRectangle(points: List<List<Int>>): Int {

    // Return value is a SortedMap. So it's sorted by key
    // Which means that X values are distributed low to high (bottom to top)
    val columnsMap = getColumnsMap(points)
    val edgesParallelToXAxis = mutableMapOf<String, Int>()

    var minArea = Integer.MAX_VALUE

    columnsMap.map { (x1, yList) ->

        // Iterate through sorted column values (left to right)
        for (i in 0 .. yList.lastIndex) {

            val y1 = yList[i]

            // Now iterate through all of the y positions on the LEFT of the current position
            // Implied by j to 0 until i
            for (j in 0 until i) {

                val y2 = yList[j]

                // Form a key with p1Y-p2Y
                // y1 is on the right and y2 is on the left (Example: 5-2)
                val parallelEdge = "$y1:$y2"

                // If in ANY OTHER row (X position) we have same y indexes (like y1 and y2)
                if (edgesParallelToXAxis.containsKey(parallelEdge)) {

                    val x2 = edgesParallelToXAxis[parallelEdge]!!

                    // We have a rectangle parallel to X and Y axes
                    // Calculate the area, remember y1 is greater than y2
                    // And x1 is greater than the previous found x2 value
                    val area = (y1 - y2) * (x1 - x2)
                    minArea = min(minArea, area)
                }

                // Save x value against this "p1Y-p2Y" "computed" key
                // We only need the LAST x value. Because of the sorted map
                // Any previous values will result in rectangle with larger area
                // While we need minimum area rectangle.
                edgesParallelToXAxis[parallelEdge] = x1
            }
        }
    }

    return if (minArea == Integer.MAX_VALUE) 0 else minArea
}

/*
{ -1 : -2, }
{  1 : 5, 2, }
{  2 : 4, 2, 5, }
{  4 : 2, 5, }
{  5 : 1, }
*/

// Group all y indexes from same x index together
fun getColumnsMap(points: List<List<Int>>): Map<Int, List<Int>> {
    val columnsMap = sortedMapOf<Int, MutableList<Int>>()

    points.forEach { point ->

        val (pX, pY) = point

        if (!columnsMap.containsKey(pX)) {
            columnsMap[pX] = mutableListOf<Int>()
        }

        columnsMap[pX]!!.add(pY)
    }

    return columnsMap.mapValues { (_, yList) ->
        // Sort y positions for current x
        // After the sorting y positions will be distributed left to right
        yList.sorted()
    }
}
