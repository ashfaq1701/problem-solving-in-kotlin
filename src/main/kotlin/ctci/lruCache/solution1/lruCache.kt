package ctci.lruCache.solution1

class LRUCache(capacity: Int) {

    private val maxCapacity: Int
    private val nodeList = DoublyLinkedList()
    private val cache = mutableMapOf<Int, DoublyLinkedListNode>()

    init {
        maxCapacity = capacity
    }

    fun get(key: Int): Int {
        if (key !in cache) return -1
        val node = cache[key]!!
        updateMostRecent(node)
        return node.value
    }

    fun put(key: Int, value: Int) {
        if (key !in cache) {
            if (cache.size == maxCapacity) {
                evictLeastRecent()
            }
            cache[key] = DoublyLinkedListNode(key, value)
        } else {
            cache[key]!!.value = value
        }

        updateMostRecent(cache[key]!!)
    }

    fun updateMostRecent(node: DoublyLinkedListNode) {
        nodeList.setHeadTo(node)
    }

    fun evictLeastRecent() {
        val leastRecentKey = nodeList.tail?.key ?: return

        nodeList.removeTail()
        cache.remove(leastRecentKey)
    }
}

class DoublyLinkedList {
    var head: DoublyLinkedListNode? = null
    var tail: DoublyLinkedListNode? = null

    fun setHeadTo(node: DoublyLinkedListNode) {
        if (node == head) return

        if (head == null) {
            head = node
            tail = node
        } else if (head == tail) {
            node.next = head
            head!!.prev = node
            head = node
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
        if (head == tail) {
            head = null
            tail = null
            return
        }

        val prev = tail!!.prev
        tail!!.removeBindings()
        tail = prev
    }
}

class DoublyLinkedListNode(val key: Int, var value: Int) {
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
