package leetcode.flatten2DVector.solution1

class Vector2D(val vec: Array<IntArray>) {

    var currentRow = 0
    var currentCol = 0

    fun next(): Int {

        adjustRows()

        return vec[currentRow][currentCol].also {
            currentCol += 1
        }
    }

    fun hasNext(): Boolean {

        adjustRows()

        return currentRow < vec.size && currentCol < vec[currentRow].size
    }

    private fun adjustRows() {
        while (currentRow < vec.size && currentCol >= vec[currentRow].size) {
            currentRow += 1
            currentCol = 0
        }
    }

}