package ctci.courseScheduleII.solution1

import java.util.*

class Solution {
    fun findOrder(numCourses: Int, prerequisites: Array<IntArray>): IntArray {
        val courseGraph = CourseGraph(numCourses)

        for ((dep, prereq) in prerequisites) {
            courseGraph.createConnection(prereq, dep)
        }

        return topologicalSortCourses(courseGraph, numCourses).toIntArray()
    }

    fun topologicalSortCourses(courseGraph: CourseGraph, numCourses: Int): List<Int> {
        val sortedNodes = mutableListOf<Int>()
        val coursesWithNoPrereqs = LinkedList<CourseNode>(
            courseGraph.courses.filter { it.prereqCount == 0 }
        )

        while (coursesWithNoPrereqs.isNotEmpty()) {
            val currentCourse = coursesWithNoPrereqs.pop()

            sortedNodes.add(currentCourse.course)

            for (dep in currentCourse.deps) {
                dep.prereqCount -= 1
                if (dep.prereqCount == 0) {
                    coursesWithNoPrereqs.add(dep)
                }
            }
        }

        return if (sortedNodes.size == numCourses) sortedNodes else listOf()
    }
}

class CourseNode(val course: Int) {

    var prereqCount = 0
    val deps = mutableListOf<CourseNode>()
}

class CourseGraph(val numCourses: Int) {

    val courses = mutableListOf<CourseNode>()

    init {
        createCourseNodes()
    }

    fun createCourseNodes() {
        for (i in 0 until numCourses) {
            courses.add(CourseNode(i))
        }
    }

    fun createConnection(prereq: Int, dep: Int) {
        val prereqNode = courses[prereq]
        val depNode = courses[dep]

        prereqNode.deps.add(depNode)
        depNode.prereqCount += 1
    }
}