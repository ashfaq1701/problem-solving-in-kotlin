package ctci.shuffleAnArray.solution1

import kotlin.random.Random

class Solution(nums: IntArray) {

    private var mainArray: IntArray = nums
    private var backupArray: IntArray = cloneArray(mainArray)

    fun reset(): IntArray {
        mainArray = cloneArray(backupArray)
        return mainArray
    }

    fun shuffle(): IntArray {
        val tempBackup = cloneArray(mainArray).toMutableList()

        for (i in mainArray.indices) {
            val randIdx = Random.nextInt(tempBackup.size)
            mainArray[i] = tempBackup[randIdx]
            tempBackup.removeAt(randIdx)
        }

        return mainArray
    }

    private fun cloneArray(array: IntArray): IntArray {
        return array.map { it }.toIntArray()
    }
}