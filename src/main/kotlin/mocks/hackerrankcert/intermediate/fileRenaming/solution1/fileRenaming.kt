package mocks.hackerrankcert.intermediate.fileRenaming.solution1

const val MOD = 1000000007

fun renameFile(newName: String, oldName: String): Int {
    val cache = mutableMapOf<String, Int>()
    return renameFileHelper(newName, oldName, 0, 0, cache) % MOD
}

fun renameFileHelper(newName: String, oldName: String, p1: Int, p2: Int, cache: MutableMap<String, Int>): Int {
    if (p1 == newName.length) return 1

    if (p2 == oldName.length) return 0

    val key = "$p1|$p2"

    if (key in cache) return cache[key]!!

    cache[key] = if (newName[p1] == oldName[p2]) {
        (renameFileHelper(newName, oldName, p1 + 1, p2 + 1, cache) % MOD + renameFileHelper(newName, oldName, p1, p2 + 1, cache) % MOD) % MOD
    } else {
        renameFileHelper(newName, oldName, p1, p2 + 1, cache) % MOD
    }

    return cache[key]!!
}

fun main() {
    println(renameFile("aba", "ababa"))
}