package leetcode.accountsMerge.solution1

class Solution {
    fun accountsMerge(accounts: List<List<String>>): List<List<String>> {
        val adjList = prepareAdjacencyList(accounts)
        val result = mutableListOf<List<String>>()

        val visited = mutableSetOf<String>()
        for (account in accounts) {
            val name = account[0]
            val connectedItems = mutableListOf<String>()

            for (emailIdx in 1 until account.size) {
                val email = account[emailIdx]

                if (email !in visited) {
                    dfs(email, adjList, visited, connectedItems)
                }
            }

            connectedItems.sort()
            if (connectedItems.isNotEmpty()) {
                result.add(listOf(name).plus(connectedItems))
            }
        }

        return result
    }

    fun dfs(email: String, adjList: Map<String, List<String>>, visited: MutableSet<String>, connectedItems: MutableList<String>) {
        if (email in visited) return

        connectedItems.add(email)
        visited.add(email)

        for (adj in adjList[email]!!) {
            dfs(adj, adjList, visited, connectedItems)
        }
    }

    fun prepareAdjacencyList(accounts: List<List<String>>): Map<String, List<String>> {
        val adjList = mutableMapOf<String, MutableList<String>>()

        for (account in accounts) {
            val firstEmail = account[1]
            if (firstEmail !in adjList) {
                adjList[firstEmail] = mutableListOf()
            }

            for (idx in 2 until account.size) {
                val nextEmail = account[idx]

                adjList[firstEmail]!!.add(nextEmail)

                if (nextEmail !in adjList) {
                    adjList[nextEmail] = mutableListOf()
                }
                adjList[nextEmail]!!.add(firstEmail)
            }
        }

        return adjList.mapValues { (_, list) -> list.toList() }
    }
}