package leetcode.countArtifactsThatCanBeExtracted.solution1

import kotlin.math.abs

class Artifact(val idx: Int, val totalIndices: Int, var uncoveredIndices: Int)

class Solution {
    fun digArtifacts(n: Int, artifacts: Array<IntArray>, dig: Array<IntArray>): Int {
        val artifactGridsMap = mutableMapOf<String, Artifact>()

        for (idx in artifacts.indices) {
            val (r1, c1, r2, c2) = artifacts[idx]

            var cellCount = (abs(r2 - r1) + 1) * (abs(c2 - c1) + 1)
            val artifactObj = Artifact(idx, cellCount, 0)

            for (r in r1 .. r2) {
                for (c in c1 .. c2) {
                    val key = "$r|$c"
                    artifactGridsMap[key] = artifactObj
                }
            }
        }

        val uncoveredArtifacts = mutableSetOf<Int>()
        for ((digX, digY) in dig) {
            val key = "${digX}|$digY"

            if (key in artifactGridsMap) {
                val artifactObj = artifactGridsMap[key]!!
                artifactObj.uncoveredIndices += 1
                if (artifactObj.uncoveredIndices == artifactObj.totalIndices) {
                    uncoveredArtifacts.add(artifactObj.idx)
                }
            }
        }

        return uncoveredArtifacts.size
    }
}