package commonAlgo.unionFind

class UnionFind(val size: Int) {

    private val roots = MutableList(size) { 0 }
    private val ranks = MutableList(size) { 0 }

    init {
        for (i in 0 until size) {
            roots[i] = i
            ranks[i] = 1
        }
    }

    fun union(x: Int, y: Int) {
        val rootX = find(x)
        val rootY = find(y)

        if (rootX != rootY) {
            if (ranks[x] > ranks[y]) {
                roots[rootY] = rootX
            } else if (ranks[y] > ranks[x]) {
                roots[rootX] = rootY
            } else {
                roots[rootY] = rootX
                ranks[rootX] += 1
            }
        }
    }

    fun find(x: Int): Int {
        if (x == roots[x]) return x

        roots[x] = find(roots[x])
        return roots[x]
    }

    fun isConnected(x: Int, y: Int) = find(x) == find(y)
}