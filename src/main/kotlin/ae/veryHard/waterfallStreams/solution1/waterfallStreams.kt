package ae.veryHard.waterfallStreams.solution1

fun waterfallStreams(array: List<List<Double>>, source: Int): List<Double> {
    // First row will contain full water.
    // It is the row where the water will poured into.
    // So we don't have to do any work here other than marking the source.
    // rowAbove is a copy array because we don't want to modify the original array.
    var rowAbove = ArrayList(array[0])
    rowAbove[source] = -1.0

    // We have to operate starting from second row
    // because the first row will have the source.
    for (i in 1 .. array.lastIndex) {

        // Current row is also a copy array, because here too
        // we don't want to modify the original array.
        val currentRow = ArrayList(array[i])

        // For every item in current row
        for (j in 0 .. currentRow.lastIndex) {

            // If the above cell doesn't contain any water then continue.
            if (rowAbove[j] >= 0) {
                continue
            }

            // If current row is not a block space
            // then water will pour into it from the above cell.
            if (currentRow[j] != 1.0) {
                currentRow[j] += rowAbove[j]
                // After pouring the water we can continue.
                continue
            }

            // Otherwise current cell is an obstacle.
            // Water stream will split into two halves here.
            val splitWater = rowAbove[j] / 2.0

            // Go to the left empty cell where water can pour into
            var left = j
            // Inside the loop we will use index left - 1.
            while (left - 1 >= 0) {
                left -= 1

                // Water is blocked by the cell above. This part of water is wasted.
                if (rowAbove[left] == 1.0) {
                    break
                }

                // If current cell at left index is empty space (not a block)
                // then water will pour into it.
                if (currentRow[left] <= 0) {
                    currentRow[left] += splitWater
                    // Then we don't need to go any more left,
                    // because water got poured already.
                    break
                }
            }

            // Go to the right empty cell where water can pour into
            var right = j
            // Inside the loop we will use index right + 1.
            while (right + 1 < currentRow.size) {
                right += 1

                // Water is blocked by the cell above. This part of water is wasted.
                if (rowAbove[right] == 1.0) {
                    break
                }

                // If current cell at right index is empty space (not a block)
                // then water will pour into it.
                if (currentRow[right] <= 0) {
                    currentRow[right] += splitWater
                    // Then we don't need to go any more right,
                    // because water got poured already.
                    break
                }
            }
        }

        rowAbove = currentRow
    }

    // At this point rowAbove contains the last row
    // It's elements are stored as negative fraction
    // It doesn't contain any block (1 value)
    return rowAbove.map { it * -100 }
}
