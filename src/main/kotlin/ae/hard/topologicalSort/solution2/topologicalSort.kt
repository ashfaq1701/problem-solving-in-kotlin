package ae.hard.topologicalSort.solution2

fun topologicalSort(jobs: List<Int>, deps: List<List<Int>>): List<Int> {
    val jobGraph = buildJobGraph(jobs, deps)
    return getOrderedJobs(jobGraph)
}

fun buildJobGraph(jobs: List<Int>, deps: List<List<Int>>): JobGraph {
    return JobGraph(jobs).also { jobGraph ->
        deps.forEach { dep ->
            jobGraph.addPrereq(dep[1], dep[0])
        }
    }
}

fun getOrderedJobs(jobGraph: JobGraph): List<Int> {
    val orderedJobs = mutableListOf<Int>()
    val jobs = jobGraph.nodes

    while (jobs.isNotEmpty()) {
        val job = jobs.removeAt(jobs.lastIndex)
        val hasCycle = dfsJob(job, orderedJobs)
        if (hasCycle) return listOf()
    }
    return orderedJobs
}

fun dfsJob(jobNode: JobNode, orderedJobs: MutableList<Int>): Boolean {
    if (jobNode.visited) return false
    if (jobNode.visiting) return true

    jobNode.visiting = true
    jobNode.prereqs.forEach { prereq ->
        if (dfsJob(prereq, orderedJobs)) {
            return true
        }
    }

    jobNode.visited = true
    jobNode.visiting = false

    orderedJobs.add(jobNode.job)
    return false
}

class JobGraph(val jobs: List<Int>) {
    val nodes = mutableListOf<JobNode>()
    val graph = mutableMapOf<Int, JobNode>()

    init {
        jobs.forEach {
            addNode(it)
        }
    }

    fun addNode(job: Int) {
        graph[job] = JobNode(job).also {
            nodes.add(it)
        }
    }

    fun addPrereq(job: Int, prereq: Int) {
        val jobNode = getNode(job)
        val prereqNode = getNode(prereq)
        jobNode.prereqs.add(prereqNode)
    }

    fun getNode(job: Int): JobNode {
        if (!graph.containsKey(job)) {
            addNode(job)
        }
        return graph[job]!!
    }

}

class JobNode(val job: Int) {
    val prereqs = mutableListOf<JobNode>()
    var visited = false
    var visiting = false
}
