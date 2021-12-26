package ae.medium.firstDuplicateValue.solution2

import kotlin.math.abs

//      [ 2.  1,  5,  4,  3,  3,  4]
//  2 - [ 2, -1,  5,  4,  3,  3,  4]
//  1 - [-2, -1,  5,  4,  3,  3,  4]
//  5 - [-2, -1,  5,  4, -3,  3,  4]
//  4 - [-2, -1,  5, -4, -3,  3,  4]
// -3 - mappedIndex = abs(-3) - 1 = 2
//			(array[2] > 0)
//			array[2] *= -1
//      [-2, -1, -5, -4, -3,  3,  4]
//  3 - mappedIndex = abs(3) - 1 = 2
//  		(array[2] < 0)
//				return abs(3) = 3
fun firstDuplicateValue(array: MutableList<Int>): Int {

    for (i in array) {
        val mappedIndex = abs(i) - 1

        if (array[mappedIndex] < 0) {
            return abs(i)
        }

        array[mappedIndex] *= -1
    }

    return -1
}
