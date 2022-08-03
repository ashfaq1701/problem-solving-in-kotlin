package leetcode.myCalendarI.solution1

import java.util.*

class MyCalendar() {

    val calendar = TreeMap<Int, Int>()

    fun book(start: Int, end: Int): Boolean {
        val prev = calendar.floorKey(start)
        val next = calendar.ceilingKey(start)

        if ((prev == null || start >= calendar[prev]!!) && (next == null || end <= next)) {
            calendar[start] = end
            return true
        }

        return false
    }

}