package leetcode.zigzagConversion.solution1

class Solution {
    fun convert(s: String, numRows: Int): String {
        val rows = MutableList(numRows) { mutableListOf<Char>() }

        var currPtr = 0

        while (currPtr < s.length) {

            for (i in 0 until numRows) {
                if (currPtr == s.length) break

                rows[i].add(s[currPtr])
                currPtr += 1
            }

            for (i in numRows - 2 downTo 1) {
                if (currPtr == s.length) break

                rows[i].add(s[currPtr])
                currPtr += 1
            }
        }

        val finalList = mutableListOf<Char>()

        for (row in rows) {
            finalList.addAll(row)
        }

        return String(finalList.toCharArray())
    }
}