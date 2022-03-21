package leetcode.readNCharactersGivenRead4.solution1

abstract class Reader4 {

    fun read4(buf4: CharArray): Int = 0

    abstract fun read(buf: CharArray, n: Int): Int
}

class Solution: Reader4() {
    /**
     * @param buf Destination buffer
     * @param n Number of characters to read
     * @return The number of actual characters read
     */
    override fun read(buf:CharArray, n:Int): Int {
        var currentIdx = 0

        val buf4 = CharArray(4) { '0' }
        var charRead = read4(buf4)
        while (charRead > 0 && currentIdx < n) {
            for (idx in 0 until charRead) {
                buf[currentIdx] = buf4[idx]
                currentIdx += 1

                if (currentIdx == n) {
                    break
                }
            }
            charRead = read4(buf4)
        }

        return currentIdx
    }
}