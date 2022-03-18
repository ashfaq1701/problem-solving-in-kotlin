package leetcode.exclusiveTimeOfFunctions.solution1

import java.util.*

data class FunctionCall(val functionId: Int, val isStart: Boolean, val time: Int, var subtractionTime: Int = 0)

class Solution {

    fun exclusiveTime(n: Int, logs: List<String>): IntArray {

        val functionCallEvents = logs.map { strToEvent(it) }
        val exclusiveTimes = MutableList<Int>(n) { 0 }

        val stack = Stack<FunctionCall>()
        for (functionCall in functionCallEvents) {
            if (functionCall.isStart) {
                stack.push(functionCall)
            } else {
                val startEvent = stack.pop()
                val executionTime = functionCall.time - startEvent.time + 1 - startEvent.subtractionTime
                exclusiveTimes[functionCall.functionId] += executionTime

                if (stack.isNotEmpty()) {
                    stack.peek().subtractionTime += (functionCall.time - startEvent.time + 1)
                }
            }
        }

        return exclusiveTimes.toIntArray()
    }

    fun strToEvent(str: String): FunctionCall {
        val splitted = str.split(":")

        val functionId = splitted[0].toInt()
        val isStart = splitted[1] == "start"
        val callTime = splitted[2].toInt()

        return FunctionCall(functionId, isStart, callTime)
    }
}