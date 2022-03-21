package leetcode.readNCharactersGivenRead4IICallmultipleTimes.solution1

abstract class Reader4 {

    fun read4(buf4: CharArray): Int = 0

    abstract fun read(buf: CharArray, n: Int): Int
}

class Solution:Reader4() {
    private val internalBuffer = CharArray(4) { '0' }
    private var leftOnInternalBuffer = 0
    private var readIdx = 0

    /**
     * @param  buf Destination buffer
     * @param  n   Number of characters to read
     * @return     The number of actual characters read
     */
    override fun read(buf:CharArray, n:Int): Int {
        var writeIdx = 0

        while (writeIdx < n) {

            while (writeIdx < n && leftOnInternalBuffer > 0) {
                buf[writeIdx] = internalBuffer[readIdx]
                readIdx += 1
                writeIdx += 1
                leftOnInternalBuffer -= 1
            }

            if (writeIdx == n) break

            leftOnInternalBuffer = read4(internalBuffer)

            if (leftOnInternalBuffer == 0) {
                break
            }

            readIdx = 0
        }

        return writeIdx
    }
}