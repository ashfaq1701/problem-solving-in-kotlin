package facebook.recommendation.qHeap1.solution1

fun main(args: Array<String>) {
    val heap = HeapIndexed()

    val n = readLine()!!.toInt()

    for (i in 0 until n) {
        val input = readLine()!!.split(" ").map { it.toInt() }
        val operation = input[0]

        when(operation) {
            1 -> {
                val data = input[1]
                heap.push(data)
            }
            2 -> {
                val data = input[1]
                heap.remove(data)
            }
            3 -> {
                println(heap.peek())
            }
        }
    }
}

class HeapIndexed {
    val arr = mutableListOf<Int>()
    val nodeToIdx = mutableMapOf<Int, Int>()

    fun push(num: Int) {
        arr.add(num)
        nodeToIdx[num] = arr.lastIndex

        siftUp(arr.lastIndex)
    }

    fun poll(): Int {
        swap(0, arr.lastIndex)
        val polledValue = arr.removeAt(arr.lastIndex)
        nodeToIdx.remove(polledValue)
        siftDown(0)
        return polledValue
    }

    fun peek(): Int {
        return if (arr.isNotEmpty()) arr[0] else -1
    }

    private fun siftUp(idx: Int) {
        var currentIdx = idx
        var parentIdx = (currentIdx - 1) / 2

        while (parentIdx >= 0 && arr[currentIdx] < arr[parentIdx]) {
            swap(currentIdx, parentIdx)
            currentIdx = parentIdx
            parentIdx = (currentIdx - 1) / 2
        }
    }

    private fun siftDown(idx: Int) {
        var currentIdx = idx
        var firstChildIdx = currentIdx * 2 + 1

        while (firstChildIdx < arr.size) {
            val secondChildIdx = currentIdx * 2 + 2

            val firstChild = arr[firstChildIdx]
            val secondChild = if (secondChildIdx < arr.size) arr[secondChildIdx] else Int.MAX_VALUE

            val idxToSwap = if (firstChild < secondChild) firstChildIdx else secondChildIdx

            if (arr[idxToSwap] > arr[currentIdx]) {
                break
            }

            swap(idxToSwap, currentIdx)
            currentIdx = idxToSwap
            firstChildIdx = currentIdx * 2 + 1
        }
    }

    private fun swap(i: Int, j: Int) {
        arr[i] = arr[j].also {
            arr[j] = arr[i]
        }

        nodeToIdx[arr[i]] = i
        nodeToIdx[arr[j]] = j
    }

    fun remove(num: Int) {
        val idx = nodeToIdx[num] ?: return
        swap(idx, arr.lastIndex)

        arr.removeAt(arr.lastIndex)
        nodeToIdx.remove(num)

        siftDown(idx)
    }
}