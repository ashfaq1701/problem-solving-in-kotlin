package ae.veryHard.lruCache.solution1

class LRUCache(var maxSize: Int) {

    var currentSize = 0
    val listOfMostRecent = DoublyLinkedList()
    val cache = mutableMapOf<String, DoublyLinkedListNode>()

    init {
        maxSize = if (maxSize > 1) maxSize else 1
        currentSize = 0
    }

    fun insertKeyValuePair(key: String, value: Int) {
        if (!cache.containsKey(key)) {
            if (currentSize == maxSize) {
                evictLeastRecentNode()
            } else {
                currentSize += 1
            }

            cache[key] = DoublyLinkedListNode(key, value)
        } else {
            updateValueForKey(key, value)
        }

        updateMostRecentNode(cache[key]!!)
    }

    fun getValueFromKey(key: String): Int? {
        if (!cache.containsKey(key)) {
            return null
        }

        updateMostRecentNode(cache[key]!!)
        return cache[key]!!.value
    }

    fun getMostRecentKey(): String? {
        if (listOfMostRecent.head == null) {
            return null
        }

        return listOfMostRecent.head!!.key
    }

    private fun evictLeastRecentNode() {
        val keyToRemove = listOfMostRecent.tail?.key

        keyToRemove ?: return

        listOfMostRecent.removeTail()
        cache.remove(keyToRemove)
    }

    private fun updateMostRecentNode(node: DoublyLinkedListNode) {
        listOfMostRecent.setHeadTo(node)
    }

    private fun updateValueForKey(key: String, value: Int) {
        cache[key]!!.value = value
    }
}

data class DoublyLinkedListNode(val key: String, var value: Int) {
    var prev: DoublyLinkedListNode? = null
    var next: DoublyLinkedListNode? = null

    fun removeBindings() {
        if (prev != null) {
            prev!!.next = next
        }

        if (next != null) {
            next!!.prev = prev
        }

        prev = null
        next = null
    }
}

class DoublyLinkedList {

    var head: DoublyLinkedListNode? = null
    var tail: DoublyLinkedListNode? = null

    fun setHeadTo(node: DoublyLinkedListNode) {
        if (node == head) {
            return
        }

        if (head == null) {
            head = node
            tail = node
        } else if (head == tail) {
            head = node
            head!!.next = tail
            tail!!.prev = head
        } else {
            if (node == tail) {
                removeTail()
            }

            node.removeBindings()
            node.next = head
            head!!.prev = node
            head = node
        }
    }

    fun removeTail() {
        if (tail == null) {
            return
        }

        if (head == tail) {
            head = null
            tail = null
            return
        }

        tail = tail!!.prev
        tail!!.next = null
    }
}
