package mocks.hackerrankcert.basic.roadRepair.solution1

import kotlin.math.abs

fun minCost(worker_id: Array<Int>, job_id: Array<Int>): Long {
    worker_id.sort()
    job_id.sort()

    var cost = 0L
    for (i in worker_id.indices) {
        cost += abs(worker_id[i] - job_id[i])
    }

    return cost
}