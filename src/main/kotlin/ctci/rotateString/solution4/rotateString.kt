package ctci.rotateString.solution4

class Solution {
    fun rotateString(s: String, goal: String): Boolean {
        if (s.length != goal.length) return false

        if (s.isEmpty()) return true

        for (rotationCount in 0 .. s.lastIndex) {
            if (isRotatedAroundIdx(s, goal, rotationCount)) {
                return true
            }
        }

        return false
    }

    fun isRotatedAroundIdx(s: String, goal: String, rotationCount: Int): Boolean {
        // Rotation Count: 2 | Shift count: 5 - 2 = 3 : abcde -> cdeab
        val shiftCount = s.length - rotationCount
        for (strIdx in 0 .. s.lastIndex) {
            if (s[(strIdx + shiftCount) % s.length] != goal[strIdx]) {
                return false
            }
        }

        return true
    }
}