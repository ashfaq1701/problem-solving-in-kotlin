package ae.hard.topologicalSort.solution1

fun topologicalSort(jobs: List<Int>, deps: List<List<Int>>): List<Int> {
    val jobGraph = createJobGraph(jobs, deps)
    return getSortedNodes(jobGraph)
}

fun createJobGraph(jobs: List<Int>, deps: List<List<Int>>): JobGraph {
    return JobGraph(jobs).also {
        deps.forEach { dep ->
            it.addDependent(dep[0], dep[1])
        }
    }
}

fun getSortedNodes(jobGraph: JobGraph): List<Int> {
    val sortedNodes = mutableListOf<Int>()

    val nodesWithNoPrereq = jobGraph.nodes.filter { it.prereqCount == 0 }.toMutableList()

    while (nodesWithNoPrereq.size > 0) {
        val node = nodesWithNoPrereq.removeAt(nodesWithNoPrereq.lastIndex)
        sortedNodes.add(node.job)
        removeDeps(node, nodesWithNoPrereq)
    }

    val stillContainEdges = jobGraph.nodes.any { it.prereqCount > 0 }
    if (stillContainEdges) return listOf()

    return sortedNodes
}

fun removeDeps(jobNode: JobNode, nodesWithNoPrereq: MutableList<JobNode>) {
    while (jobNode.deps.size > 0) {
        val dep = jobNode.deps.removeAt(jobNode.deps.lastIndex)
        dep.prereqCount -= 1
        if (dep.prereqCount == 0) {
            nodesWithNoPrereq.add(dep)
        }
    }
}

class JobGraph(val jobs: List<Int>) {
    val nodes = mutableListOf<JobNode>()
    val graph = mutableMapOf<Int, JobNode>()

    init {
        jobs.forEach {
            createNode(it)
        }
    }

    fun createNode(job: Int) {
        graph[job] = JobNode(job).also {
            nodes.add(it)
        }
    }

    fun addDependent(job: Int, dependent: Int) {
        val jobNode = getJobNode(job)
        val dependentNode = getJobNode(dependent)
        dependentNode.prereqCount += 1
        jobNode.deps.add(dependentNode)
    }

    fun getJobNode(job: Int): JobNode {
        if (!graph.containsKey(job)) {
            createNode(job)
        }
        return graph[job]!!
    }
}

class JobNode(val job: Int) {
    val deps = mutableListOf<JobNode>()
    var prereqCount = 0
}
