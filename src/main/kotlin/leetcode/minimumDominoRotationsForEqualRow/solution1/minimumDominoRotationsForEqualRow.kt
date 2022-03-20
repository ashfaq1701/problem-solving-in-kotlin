package leetcode.minimumDominoRotationsForEqualRow.solution1

import kotlin.math.min

class Solution {
    fun minDominoRotations(tops: IntArray, bottoms: IntArray): Int {
        var rotations = check(tops[0], tops, bottoms)

        return if (rotations != -1 || tops[0] == bottoms[0]) {
            rotations
        } else {
            check(bottoms[0], tops, bottoms)
        }
    }

    fun check(x: Int, a: IntArray, b: IntArray): Int {
        var rotationsA = 0
        var rotationsB = 0

        for (i in a.indices) {
            if (a[i] != x && b[i] != x) {
                return -1
            }

            if (a[i] != x) {
                rotationsA += 1
            }

            if (b[i] != x) {
                rotationsB += 1
            }
        }

        return min(rotationsA, rotationsB)
    }
}