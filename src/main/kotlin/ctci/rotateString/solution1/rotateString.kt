package ctci.rotateString.solution1

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

        return s.substring(rotationIdx) == goal.substring(0, leftSideLen) &&
                s.substring(0, rotationIdx) == goal.substring(goal.length - rightSideLen)
    }
}