package ctci.implementRand10UsingRand7.solution2

class Solution {
    fun rand7() = 0

    fun rand10(): Int {
        var idx = 0

        while (true) {
            var a = rand7()
            var b = rand7()
            idx = (a - 1) * 7 + b
            if (idx <= 40) break

            a = idx - 40
            b = rand7()
            idx = (a - 1) * 7 + b
            if (idx <= 60) break

            a = idx - 60
            b = rand7()
            idx = (a - 1) * 7 + b
            if (idx <= 20) break
        }

        return 1 + (idx - 1) % 10
    }
}