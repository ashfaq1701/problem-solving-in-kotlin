package ctci.floodFill.solution1

class Solution {
    fun floodFill(image: Array<IntArray>, sr: Int, sc: Int, newColor: Int): Array<IntArray> {
        val oldColor = image[sr][sc]
        floodFillHelper(image, sr, sc, newColor, oldColor)
        return image
    }

    fun floodFillHelper(image: Array<IntArray>, r: Int, c: Int, newColor: Int, oldColor: Int) {
        if (image[r][c] == newColor || image[r][c] != oldColor) {
            return
        }

        image[r][c] = newColor

        for (neighbor in getNeighbors(r, c, image.size, image[0].size)) {
            val (adjR, adjC) = neighbor
            floodFillHelper(image, adjR, adjC, newColor, oldColor)
        }
    }

    fun getNeighbors(r: Int, c: Int, rows: Int, cols: Int): List<Pair<Int, Int>> {
        return listOf(
            r - 1 to c,
            r to c - 1,
            r + 1 to c,
            r to c + 1
        ).filter { (adjR, adjC) ->
            adjR in 0 until rows && adjC in 0 until cols
        }
    }
}