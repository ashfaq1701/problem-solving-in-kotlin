package ctci.rotateString.solution2

class Solution {
    fun rotateString(s: String, goal: String): Boolean {
        if (s.length != goal.length) return false

        for (rotationIdx in 0 .. s.lastIndex) {
            if (verifyRotationStartingAt(s, goal, rotationIdx)) {
                return true
            }
        }

        return false
    }

    fun verifyRotationStartingAt(s: String, goal: String, rotationIdx: Int): Boolean {
        val leftSideLen = s.length - rotationIdx
        val rightSideLen = rotationIdx

        var sLeft = rotationIdx
        var goalLeft = 0

        while (goalLeft < leftSideLen) {
            if (s[sLeft] != goal[goalLeft]) {
                return false
            }

            sLeft += 1
            goalLeft += 1
        }

        var sRight = 0
        var goalRight = s.length - rightSideLen

        while (goalRight < s.length) {
            if (s[sRight] != goal[goalRight]) {
                return false
            }

            sRight += 1
            goalRight += 1
        }

        return true
    }
}