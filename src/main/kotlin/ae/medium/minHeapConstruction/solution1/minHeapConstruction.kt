package ae.medium.minHeapConstruction.solution1

open class MinHeap(array: MutableList<Int>) {
    val heap = this.buildHeap(array)

    fun buildHeap(array: MutableList<Int>): MutableList<Int> {
        val firstParentIdx = (array.lastIndex - 1) / 2
        var parentIdx = firstParentIdx

        while (parentIdx >= 0) {
            siftDown(parentIdx, array.lastIndex, array)
            parentIdx -= 1
        }

        return array
    }

    fun siftDown(currentIdx: Int, endIdx: Int, heap: MutableList<Int>) {
        var mutableCurrentIdx = currentIdx

        var childOneIdx = mutableCurrentIdx * 2 + 1

        while (childOneIdx <= endIdx) {
            val childTwoIdx = if (mutableCurrentIdx * 2 + 2 <= endIdx) mutableCurrentIdx * 2 + 2 else -1
            val idxToSwap = if (childTwoIdx != -1 && heap[childTwoIdx] < heap[childOneIdx]) {
                childTwoIdx
            } else childOneIdx

            if (heap[idxToSwap] < heap[mutableCurrentIdx]) {
                swap(mutableCurrentIdx, idxToSwap, heap)
                mutableCurrentIdx = idxToSwap
                childOneIdx = mutableCurrentIdx * 2 + 1
            } else {
                // Already in the correct position
                return
            }
        }
    }

    fun siftUp(currentIdx: Int, heap: MutableList<Int>) {
        var mutableCurrentIdx = currentIdx

        var parentIdx = (mutableCurrentIdx - 1) / 2

        while (mutableCurrentIdx > 0 && heap[mutableCurrentIdx] < heap[parentIdx]) {
            swap(mutableCurrentIdx, parentIdx, heap)
            mutableCurrentIdx = parentIdx
            parentIdx = (mutableCurrentIdx - 1) / 2
        }
    }

    fun peek(): Int? {
        return heap[0]
    }

    fun remove(): Int? {
        swap(heap.lastIndex, 0, heap)
        val nodeToRemove = heap.removeAt(heap.lastIndex)
        siftDown(0, heap.lastIndex, heap)
        return nodeToRemove
    }

    fun insert(value: Int) {
        heap.add(value)
        siftUp(heap.lastIndex, heap)
    }

    fun swap(i: Int, j: Int, heap: MutableList<Int>) {
        val temp = heap[i]
        heap[i] = heap[j]
        heap[j] = temp
    }
}
