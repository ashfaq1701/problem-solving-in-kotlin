package ae.veryHard.calendarMatching.solution1

import kotlin.math.max

fun calendarMatching(calendar1: List<List<String>>, dailyBounds1: List<String>, calendar2: List<List<String>>, dailyBounds2: List<String>, meetingDuration: Int): List<List<String>> {
    val updatedCalendar1 = updateCalendar(calendar1, dailyBounds1)
    val updatedCalendar2 = updateCalendar(calendar2, dailyBounds2)

    val mergedCalendar = mergeCalendars(updatedCalendar1, updatedCalendar2)
    val mergedIntervals = mergeIntervals(mergedCalendar)
    val availableSlots = getAvailableSlots(mergedIntervals, meetingDuration)

    return availableSlots.map { it.toListBounds() }
}

fun updateCalendar(calendar: List<List<String>>, dailyBound: List<String>): List<IntMeeting> {
    val updatedCalendar = mutableListOf(StringMeeting("00:00", dailyBound[0]))
    calendar.forEach { meeting ->
        updatedCalendar.add(StringMeeting(meeting[0], meeting[1]))
    }
    updatedCalendar.add(StringMeeting(dailyBound[1], "23:59"))

    return updatedCalendar.map { it.toIntMeeting() }
}

fun mergeCalendars(calendar1: List<IntMeeting>, calendar2: List<IntMeeting>): List<IntMeeting> {
    val mergedCalendar = mutableListOf<IntMeeting>()

    var p1 = 0
    var p2 = 0

    while (p1 < calendar1.size && p2 < calendar2.size) {
        if (calendar1[p1].start < calendar2[p2].start) {
            mergedCalendar.add(calendar1[p1])
            p1 += 1
        } else {
            mergedCalendar.add(calendar2[p2])
            p2 += 1
        }
    }

    while (p1 < calendar1.size) {
        mergedCalendar.add(calendar1[p1])
        p1++
    }

    while (p2 < calendar2.size) {
        mergedCalendar.add(calendar2[p2])
        p2++
    }

    return mergedCalendar
}

fun mergeIntervals(calendar: List<IntMeeting>): List<IntMeeting> {
    val mergedIntervals = mutableListOf<IntMeeting>()
    mergedIntervals.add(calendar.first())

    for (i in 1 .. calendar.lastIndex) {
        val currentMeeting = calendar[i]

        if (currentMeeting.start > mergedIntervals.last().end) {
            mergedIntervals.add(currentMeeting)
        } else {
            mergedIntervals.last().end = max(currentMeeting.end, mergedIntervals.last().end)
        }
    }

    return mergedIntervals
}

fun getAvailableSlots(calendar: List<IntMeeting>, meetingDuration: Int): List<IntMeeting> {

    val availableSlots = mutableListOf<IntMeeting>()

    for (i in 1 .. calendar.lastIndex) {
        val currentMeeting = calendar[i]
        val lastMeeting = calendar[i - 1]

        if (currentMeeting.start - lastMeeting.end >= meetingDuration) {
            availableSlots.add(IntMeeting(lastMeeting.end, currentMeeting.start))
        }
    }

    return availableSlots
}

data class StringMeeting(val start: String, val end: String) {
    fun toIntMeeting() = IntMeeting(toIntTime(start), toIntTime(end))
}

data class IntMeeting(var start: Int, var end: Int) {
    private fun toStringMeeting() = StringMeeting(toStringTime(start), toStringTime(end))

    fun toListBounds(): List<String> {
        val stringMeeting = toStringMeeting()
        return listOf(stringMeeting.start, stringMeeting.end)
    }
}

fun toIntTime(time: String): Int {
    val parts = time.split(":")
    val hour = parts[0].toInt()
    val minute = parts[1].toInt()
    return hour * 60 + minute
}

fun toStringTime(time: Int): String {
    val hour = time / 60
    val minute = time % 60

    val hourStr = hour.toString()
    val minuteStr = if (minute < 10) "0$minute" else minute.toString()

    return "$hourStr:$minuteStr"
}
