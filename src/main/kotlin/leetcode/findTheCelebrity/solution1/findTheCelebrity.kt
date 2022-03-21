package leetcode.findTheCelebrity.solution1

abstract class Relation {

    abstract fun findCelebrity(n: Int): Int

    fun knows(a: Int, b: Int): Boolean = false
}

class Solution: Relation() {
    override fun findCelebrity(n: Int) : Int {
        var celebrityCandidate = 0

        for (i in 0 until n) {
            if (knows(celebrityCandidate, i)) {
                celebrityCandidate = i
            }
        }

        return if (isCelebrity(celebrityCandidate, n)) celebrityCandidate else -1
    }

    private fun isCelebrity(candidate: Int, n: Int): Boolean {
        for (i in 0 until n) {
            if (i != candidate) {
                if (!knows(i, candidate) || knows(candidate, i)) {
                    return false
                }
            }
        }

        return true
    }
}