package ctci.implementRand10UsingRand7.solution1

class Solution {
    fun rand7() = 0

    fun rand10(): Int {
        var idx = Int.MAX_VALUE

        while (idx > 40) {
            val row = rand7()
            val col = rand7()
            idx = (row - 1) * 7 + col
        }

        return 1 + (idx - 1) % 10
    }
}