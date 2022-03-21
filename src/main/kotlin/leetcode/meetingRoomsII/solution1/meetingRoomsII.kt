package leetcode.meetingRoomsII.solution1

import java.util.*
import kotlin.Comparator

class Solution {
    fun minMeetingRooms(intervals: Array<IntArray>): Int {
        intervals.sortWith(Comparator<IntArray> { i1, i2 ->
            i1[0].compareTo(i2[0])
        })
        val allocator = PriorityQueue<Int>()

        for ((start, end) in intervals) {
            if (allocator.isNotEmpty() && allocator.peek() <= start) {
                allocator.poll()
            }

            allocator.add(end)
        }

        return allocator.size
    }
}