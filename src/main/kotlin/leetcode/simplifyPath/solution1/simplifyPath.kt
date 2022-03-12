package leetcode.simplifyPath.solution1

import java.util.*

fun main() {
    println(Solution().simplifyPath("/home//../a/./foo/"))
}

const val SLASH = '/'

class Solution {
    fun simplifyPath(path: String): String {
        var currentIdx = 0
        val startsWithRoot = if (path[0] == SLASH) {
            currentIdx += 1
            true
        } else false

        val tokens = path.split(SLASH)
        val importantTokens = tokens.filter { it.isNotEmpty() && it != "." }

        val stack = Stack<String>()

        for (token in importantTokens) {
            when (token) {
                ".." -> if (stack.isNotEmpty()) {
                    stack.pop()
                }
                else -> stack.push(token)
            }
        }

        val joinedPath = stack.toList().joinToString(SLASH.toString())

        return if (startsWithRoot) "/$joinedPath" else joinedPath
    }
}