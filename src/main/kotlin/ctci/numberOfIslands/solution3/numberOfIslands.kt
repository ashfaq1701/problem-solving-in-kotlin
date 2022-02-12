package ctci.numberOfIslands.solution3

class Solution {
    fun numIslands(grid: Array<CharArray>): Int {
        val unionFind = UnionFind(grid)

        for (r in grid.indices) {
            for (c in grid[0].indices) {
                if (grid[r][c] == '1') {
                    grid[r][c] = '0'

                    val neighbors = getNeighbors(r, c, grid.size, grid[0].size)

                    for ((adjR, adjC) in neighbors) {
                        if (grid[adjR][adjC] == '1') {
                            unionFind.union(r to c, adjR to adjC)
                        }
                    }
                }
            }
        }

        return unionFind.count
    }

    fun getNeighbors(r: Int, c: Int, rows: Int, cols: Int): List<Pair<Int, Int>> {
        return listOf(
            r - 1 to c,
            r to c - 1,
            r + 1 to c,
            r to c + 1
        ).filter { (row, col) ->
            row in 0 until rows && col in 0 until cols
        }
    }
}

class UnionFind(val grid: Array<CharArray>) {

    val rows = grid.size
    val cols = grid[0].size
    val roots = MutableList(rows * cols) { 0 }
    val ranks = MutableList(rows * cols) { 0 }

    var count = 0

    init {
        for (r in 0 until rows) {
            for (c in 0 until cols) {
                if (grid[r][c] == '1') {
                    val idx = pointToIdx(r to c)
                    roots[idx] = idx
                    ranks[idx] = 1

                    count += 1
                }
            }
        }
    }

    fun union(p1: Pair<Int, Int>, p2: Pair<Int, Int>) {
        val p1Idx = pointToIdx(p1)
        val p2Idx = pointToIdx(p2)

        val p1Root = find(p1Idx)
        val p2Root = find(p2Idx)

        if (p1Root != p2Root) {
            if (ranks[p1Root] > ranks[p2Root]) {
                roots[p2Root] = p1Root
            } else if (ranks[p2Root] > ranks[p1Root]) {
                roots[p1Root] = p2Root
            } else {
                roots[p2Root] = p1Root
                ranks[p1Root] += 1
            }

            count -= 1
        }
    }

    fun find(p1Idx: Int): Int {
        if (roots[p1Idx] == p1Idx) return p1Idx

        roots[p1Idx] = find(roots[p1Idx])
        return roots[p1Idx]
    }

    private fun pointToIdx(p: Pair<Int, Int>): Int {
        val (r, c) = p
        return r * cols + c
    }
}

