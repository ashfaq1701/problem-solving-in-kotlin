package leetcode.expressionAddOperators.solution1

class Solution {
    private val exprs = mutableListOf<String>()
    private val buff = mutableListOf<Char>()

    fun addOperators(num: String, target: Int): List<String> {
        helper(0, num, 0L, 0L, 0L, '+', target.toLong())
        return exprs
    }

    fun helper(idx: Int, num: String, calc: Long, curr: Long, prev: Long, op: Char, target: Long) {
        if (idx == num.length) {
            val (newCalc, _) = evalOpWithPriv(curr, prev, calc, op)

            if (newCalc == target) {
                exprs.add(String(buff.toCharArray()))
            }

            return
        }

        val ch = num[idx]
        val digit = ch - '0'

        if (idx > 0) {
            val (newCalc, nextPrev) = evalOpWithPriv(curr, prev, calc, op)

            listOf('*', '+', '-').forEach {
                buff.add(it)
                buff.add(ch)
                helper(idx + 1, num, newCalc, digit.toLong(), nextPrev, it, target)
                buff.removeAt(buff.lastIndex)
                buff.removeAt(buff.lastIndex)
            }
        }

        if (idx == 0 || curr != 0L) {
            val newCurr = curr * 10 + digit
            buff.add(ch)
            helper(idx + 1, num, calc, newCurr, prev, op, target)
            buff.removeAt(buff.lastIndex)
        }
    }

    private fun evalOpWithPriv(curr: Long, prev: Long, calc: Long, op: Char): Pair<Long, Long> {
        return when(op) {
            '+' -> calc + curr to curr
            '-' -> calc - curr to -curr
            '*' -> curr * prev + calc - prev to curr * prev
            else -> throw Exception("Invalid operator")
        }
    }
}