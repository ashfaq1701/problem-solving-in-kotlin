package ae.medium.taskAssignment.solution1

// Sort the map and assign one longest task and one shortest task
// (e.g. one from both ends) to each of the k workers.
fun taskAssignment(k: Int, tasks: List<Int>): List<List<Int>> {
    val taskPairs = mutableListOf<List<Int>>()
    val taskDurationsToIndices = getTaskDurationsToIndices(tasks)

    val sortedTasks = tasks.sorted().toMutableList()

    for (i in 0 until k) {
        val task1Duration = sortedTasks[i]
        val task1Indices = taskDurationsToIndices[task1Duration]!!
        val task1Index = task1Indices.removeAt(task1Indices.lastIndex)

        val task2Idx = sortedTasks.lastIndex - i
        val task2Duration = sortedTasks[task2Idx]
        val task2Indices = taskDurationsToIndices[task2Duration]!!
        val task2Index = task2Indices.removeAt(task2Indices.lastIndex)

        taskPairs.add(listOf(task1Index, task2Index))
    }

    return taskPairs
}

fun getTaskDurationsToIndices(tasks: List<Int>): Map<Int, MutableList<Int>> {
    val taskDurationsToIndices = mutableMapOf<Int, MutableList<Int>>()

    tasks.forEachIndexed { idx, task ->
        if (!taskDurationsToIndices.containsKey(task)) {
            taskDurationsToIndices[task] = mutableListOf<Int>()
        }

        taskDurationsToIndices[task]!!.add(idx)
    }

    return taskDurationsToIndices
}

