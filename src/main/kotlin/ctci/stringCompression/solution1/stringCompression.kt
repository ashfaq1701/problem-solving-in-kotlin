package ctci.stringCompression.solution1

class Solution {

    fun compress(chars: CharArray): Int {
        var writeIdx = 0
        var runCount = 1

        for (readIdx in 1 until chars.size) {
            if (chars[readIdx] != chars[readIdx - 1]) {
                writeIdx = writeCharWithCount(chars, chars[readIdx - 1], runCount, writeIdx)
                runCount = 0
            }

            runCount += 1
        }

        writeIdx = writeCharWithCount(chars, chars.last(), runCount, writeIdx)

        return writeIdx
    }

    fun writeCharWithCount(chars: CharArray, char: Char, runCount: Int, writeIdx: Int): Int {
        var currentWriteIdx = writeIdx
        chars[currentWriteIdx] = char
        currentWriteIdx += 1

        if (runCount > 1) {
            val charList = intToCharList(runCount)

            charList.forEach { digit ->
                chars[currentWriteIdx] = digit
                currentWriteIdx += 1
            }
        }

        return currentWriteIdx
    }

    fun intToCharList(num: Int): List<Char> {
        var currentNum = num

        val charList = mutableListOf<Char>()

        while (currentNum > 0) {
            val digit = '0' + (currentNum % 10)
            charList.add(digit)
            currentNum = currentNum / 10
        }

        return charList.reversed()
    }
}
