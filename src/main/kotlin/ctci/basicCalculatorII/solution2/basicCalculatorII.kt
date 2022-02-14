package ctci.basicCalculatorII.solution2

class Solution {
    fun calculate(s: String): Int {
        var lastNum = 0
        var currentNum = 0
        var result = 0
        var lastSign = '+'

        for (i in s.indices) {
            val c = s[i]

            if (c.isDigit()) {
                currentNum = currentNum * 10 + (c - '0')
            }

            if ((!c.isDigit() && c != ' ') || (i == s.lastIndex)) {
                lastNum = when (lastSign) {
                    in listOf('+', '-') -> {
                        result += lastNum
                        if (lastSign == '+') currentNum else -currentNum
                    }
                    '*' -> {
                        lastNum * currentNum
                    }
                    '/' -> {
                        lastNum / currentNum
                    }
                    else -> {
                        result += lastNum
                        currentNum
                    }
                }

                lastSign = c
                currentNum = 0
            }
        }

        return result + lastNum
    }
}