package playground

import org.junit.jupiter.api.Test
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.Month
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoField
import java.time.temporal.ChronoUnit
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.collections.HashSet

class SyntaxesTest {
    @Test
    fun rangeTest() {
        // 1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
        for (i in 1 .. 10) {
            print("$i, ")
        }

        // 1, 2, 3, 4, 5, 6, 7, 8, 9,
        for (i in 1 until 10) {
            print("$i, ")
        }

        // 1, 3, 5, 7, 9,
        for (i in 1 .. 10 step 2) {
            print("$i, ")
        }

        // 1, 3, 5, 7, 9,
        for (i in 1.rangeTo(10).step(2)) {
            print("$i, ")
        }

        // 10, 9, 8, 7, 6, 5, 4, 3, 2, 1,
        for (i in 10 downTo 1) {
            print("$i, ")
        }

        // 10, 8, 6, 4, 2,
        for (i in 10 downTo 1 step 2) {
            print("$i, ")
        }

        // 9, 7, 5, 3, 1,
        for (i in (1 .. 10 step 2).reversed()) {
            print("$i, ")
        }

        // a, d, g, j,
        for (c in 'a' .. 'l' step 3) {
            print("$c, ")
        }

        // l, i, f, c,
        for (c in 'l' downTo  'a' step 3) {
            print("$c, ")
        }

        // j, g, d, a,
        for (c in ('a' .. 'l' step 3).reversed()) {
            print("$c, ")
        }

        // a, d,
        for (c in 'a' until 'g' step 3) {
            print("$c, ")
        }
    }

    @Test
    fun arrayIterationTest() {
        val nums = arrayOf(0, 1, 2, 3, 4, 5)

        for (i in nums) {
            print("$i, ")
        }

        nums.forEach {
            print("$it, ")
        }

        for (i in 0 .. nums.lastIndex) {
            print("${nums[i]}, ")
        }

        // 0, 2, 4,
        for (i in 0 .. nums.lastIndex step 2) {
            print("${nums[i]}, ")
        }

        // 0, 1, 2, 3, 4, 5,
        // ****************
        for (i in nums.indices) {
            print("${nums[i]}, ")
        }

        // 0, 2, 4,
        for (i in nums.indices step 2) {
            print("${nums[i]}, ")
        }

        // 5, 3, 1,
        for (i in nums.indices.reversed() step 2) {
            print("${nums[i]}, ")
        }

        // 5, 3,
        for (i in nums.lastIndex downTo 2 step 2) {
            print("${nums[i]}, ")
        }
    }

    @Test
    fun listTest() {
        // Specific index from list (ex list[3]) will be not-nullable (ex Int)
        // Specific index from map (ex map["three"]) will be nullable (ex Int?)

        val immutableList1 = listOf(1, 2, 3, 4, 5)
        val immutableList2 = List(5) { it * 2 }
        val mutableList1 = mutableListOf(1, 2, 3, 4, 5)
        val mutableList2 = MutableList(5) { it * 2 }

        val element1DArr = immutableList1[3]

        val twoDArray = List<List<Int>>(3) { List<Int>(3) { 0 } }
        val element2DArr = twoDArray[1][1]

        mutableList1.contains(3)
        mutableList1.toList()
        println(mutableList1.indexOf(3))
        mutableList1.remove(3)
        mutableList1.add(2, 8)
        mutableList1.removeAt(3)
        println(mutableList1.first())
        println(mutableList1)
        println(immutableList1.joinToString("-"))
        println(immutableList1.sorted())
        mutableList1.sort()

        val arrayList = ArrayList<Int>(5)
        arrayList.add(5)
        arrayList.add(4)

        val arrayList2 = ArrayList<Int>();

        val immutableMap = mapOf(1 to "One", 2 to "Two", 3 to "Three")
        val mutableMap = mutableMapOf(1 to "One", 2 to "Two", 3 to "Three")
        mutableMap[4] = "Four"
        mutableMap[5] = "Five"
        mutableMap.put(6, "Six")
        mutableMap.containsKey(5)

        val elementMap = mutableMap[4]

        mutableMap[4]?.let {
            println("$it found for key 4")
        }

        println(mutableMap.values.maxOrNull())

        val list = listOf("a", "b")
        val (s1, s2) = list
        println("$s1 , $s2")

        val hashMap = HashMap<Int, String>()
        hashMap[1] = "One"
        hashMap[2] = "Two"
        hashMap[3] = "Three"
        hashMap.put(4, "Four")
        hashMap.containsKey(4)

        val value = hashMap.getValue(2)

        immutableMap.keys.forEach {
            println("Key is $it")
        }

        immutableMap.entries.forEach {
            println("Key is ${it.key}, Value is ${it.value}")
        }

        mutableMap.remove(6)
        immutableMap.toSortedMap()
        immutableMap.values.sorted()

        val valueCollection = mutableMap.values

        val set = setOf(1, 2, 3, 4, 5)
        val mutableSet = mutableSetOf(1, 2, 3, 4, 5)
        if (mutableSet.add(6)) {
            println("Insertion of 6 is successful")
        }
        if (!mutableSet.add(2)) {
            println("Insertion of 2 is unsuccessful")
        }

        mutableSet.remove(5)

        val hashSet = HashSet<Int>()
        hashSet.add(1)
        hashSet.add(2)

        hashSet.contains(2)

        hashSet.remove(2)

        val treeSet = TreeSet<Int>()
        treeSet.add(2)
        treeSet.add(3)
        treeSet.remove(2)

        data class Item(val id: Int, val value: Int)

        val heap = PriorityQueue<Item> { a: Item, b: Item -> a.value - b.value }
        heap.add(Item(5, 1))
        heap.add(Item(65, 3))
        heap.add(Item(587, 12))
        heap.add(Item(76, 68))
        heap.add(Item(34, 91))
        println(heap)

        println(heap.isNotEmpty())
        println(heap.isEmpty())
        println(heap.poll())
    }

    @Test
    fun stringTest() {
        val string = "Hello world!"
        println(string[1])
        println(string.length)

        println(string.substring(6))
        println(string.substring(6, 9))
        println(string.substring(6..9)) // 6 to 9 inclusive

        println(string.compareTo("Hello world!")) // Equal
        println(string.compareTo("Int")) // Smaller
        println(string.compareTo("Abcdef")) // Greater

        println(string.plus(" Hi Kotlin!"))

        println(string.startsWith("hello", true))
        println(string.endsWith("world!", true))

        println(string.drop(3))
        println(string.dropLast(3))

        println(string.contains(Regex("^H..lo")))
        println(string.contains("^H..lo".toRegex()))

        println(string.split("\\s".toRegex()))

        println(string.split(" "))

        println(string.replace('l', 'z'))
        println(string.replace("world", "Google"))

        println(string.indexOf('l', 6))
        println(string.slice(2..5))

        println(string.substringBefore(' '))
        println(string.substringAfter(' '))

        println(string.reversed())

        val strTokens = StringTokenizer("John,Jim,Jimmy,Simba,Sheela,Sukumvit", ",")
        while (strTokens.hasMoreTokens()) {
            println(strTokens.nextToken())
        }
    }

    fun functionalManipulationTest() {
        val nums = listOf(3, 6, 9, 19, 2, 98, 56, 43, 21, 12, 101, 103, 203, 204, 65, 75, 69, 17, 19, 96, 92, 14, 18, 23, 26, 28, 26, 34, 43, 46, 62, 74, 82, 97, 83)
        val evenOdd = nums.map {
            if (it % 2 == 0) "Even" else "Odd"
        }
        println(evenOdd)

        val numsEqualValue = nums.mapIndexed { idx, value ->
            if (idx == value) 0 else value
        }
        println(numsEqualValue)

        val firstDivisibleByFour = nums.find { it % 4 == 0 }
        println(firstDivisibleByFour)

        val lastDivisibleByFour = nums.findLast { it % 4 == 0 }
        println(lastDivisibleByFour)

        val reduced = nums.reduce { acc, n ->
            if (n % 4 == 0) {
                acc * 10
            } else {
                acc * 10 + n
            }
        }
        println(reduced)

        val evensFiltered = nums.filter { it % 2 == 0 }
        println(evensFiltered)

        val oddsFiltered = nums.filter { it % 2 == 0 }
        println(oddsFiltered)

        val valuesEqualToIdx = nums.filterIndexed { idx, value ->
            idx == value
        }
        println(valuesEqualToIdx)

        val listWithNull = listOf("abc", null, "def", null, "ghi", null, "jkl")
        val notNullList = listWithNull.filterNotNull()
        println(notNullList)

        val reducedIndexed = notNullList.reduceIndexed { idx, acc, s ->
            "$acc$s-->$idx"
        }
        println(reducedIndexed)

        val areAnyDivisibleByFour = nums.fold(false) { acc, curr ->
            acc || curr % 4 == 0
        }
        println(areAnyDivisibleByFour)

        val rightDiff = nums.foldRight(0) { curr, acc ->
            acc - curr
        }
        println(rightDiff)

        val areAnyIdxEqualsValue = nums.foldIndexed(false) { idx, acc, curr ->
            acc || idx == curr
        }
        println(areAnyIdxEqualsValue)

        val areAnyIdxEqualsValue2 = nums.foldRightIndexed(false) { idx, curr, acc ->
            acc || idx == curr
        }
        println(areAnyIdxEqualsValue2)

        val listOfLists = listOf(
            listOf(3, 6, 9, 19, 2, 98),
            listOf(56, 43, 21, 12, 101),
            listOf(103, 203, 204, 65, 75),
            listOf(69, 17, 19, 96, 92),
            listOf(14, 18, 23, 26, 28),
            listOf(26, 34, 43, 46, 62),
            listOf(26, 34, 43, 46, 62),
            listOf(74, 82, 97, 83, 87)
        )

        val evensFlattened = listOfLists.flatMap { innerLoop ->
            innerLoop.filter { it % 2 == 0 }
        }
        println(evensFlattened)

        val oddsFlattened = listOfLists.flatMap { innerLoop ->
            innerLoop.filter { it % 2 == 1 }
        }
        println(oddsFlattened)

        println(listOfLists.flatten())

        val strs = listOf("", "", "", "", "abc", "def", "", "")
        val dropStartingEmptyStrs = strs.dropWhile { it.isEmpty() }
        println(dropStartingEmptyStrs)

        val keepStartingEmptyStrs = strs.takeWhile { it.isEmpty() }
        println(keepStartingEmptyStrs)

        val dropEndingEmptyStrs = strs.dropLastWhile { it.isEmpty() }
        println(dropEndingEmptyStrs)

        val keepEndingEmptyStrs = strs.takeLastWhile { it.isEmpty() }
        println(keepEndingEmptyStrs)

        val map = mapOf(1 to "One", 2 to "Two", 3 to "Three")
        val transformedList = map.map { (k, v) ->
            "$k-$v"
        }

        println(transformedList)

        val filteredNot = map.filterNot { (k, _) ->
            k == 1
        }
        println(filteredNot)

        val filtered = map.filter { (k, _) ->
            k != 1
        }
        println(filtered)

        val filteredKeys = map.filterKeys { k ->
            k != 1
        }
        println(filteredKeys)

        val filteredValues = map.filterValues { v ->
            v != "Two"
        }
        println(filteredValues)

        val prefixSum = nums.fold(listOf(0)) { acc, i ->
            acc.plus(acc.last() + i)
        }
        println(prefixSum)

        val groupedBy = nums.groupBy { it % 3 }
        println(groupedBy)

        val zipped = nums.zip(strs)
        println(zipped)

        val zippedAsStr = nums.zip(strs) { num, str ->
            "$num$str"
        }
        println(zippedAsStr)

        val partitioned = nums.partition {
            it % 2 == 0
        }
        println(partitioned)

        val treeMap = TreeMap<Int, String>()
        treeMap[1] = "One"
        treeMap[2] = "Two"
        treeMap[3] = "Three"

        val newMap = treeMap.mapValues { (_, s) ->
            "$s-modified"
        }
        println(newMap)

        val newMap2: SortedMap<Int, String> = treeMap.mapKeys { (k, _) ->
            10 - k
        }.toSortedMap()
        println(newMap2)

        val sortedMap: SortedMap<Int, String> = sortedMapOf<Int, String>()
        sortedMap[1] = "One"
        sortedMap[2] = "Two"
        sortedMap[3] = "Three"
        sortedMap.mapValues { (_, v) ->
            "$v-modified"
        }
        val newMap3: SortedMap<Int, String> = sortedMap.mapKeys { (k, _) ->
            10 - k
        }.toSortedMap()
        println(newMap3)
    }

    @Test
    fun higherOrderFunctionTest() {
        fun myWith(name: String, block: String.() -> Unit) {
            name.block()
        }

        class Fish (val name: String)

        fun fishExamples() {
            val fish = Fish("splashy")  // all lowercase
            myWith (fish.name) {
                println(capitalize())
            }
        }

        fishExamples()
    }

    @Test
    fun genericsTest() {

        // Removing the out from the type definition of Aquarium will not work
        // Because removing the out, Aquarium<TapWater> will not be a subtype of Aquarium<WaterSupply>
        // Similarly Aquarium<FishStoreWater> will not be a subtype of Aquarium<WaterSupply>
        val aquarium = Aquarium(TapWater())
        addItemTo(aquarium)

        isWaterClean(aquarium)
        println(aquarium.hasWaterSupplyOfType<TapWater>())

        val cleaner = TapWaterCleaner()
        val aquarium2 = Aquarium(TapWater())
        aquarium2.addWater(cleaner)
    }

    @Test
    fun dateTest() {
        // # Legacy Date
        val date = Date()
        println(date.time)

        val c1 = Calendar.getInstance()
        val gregorianCalendar = GregorianCalendar()
        println(c1.get(Calendar.YEAR))
        println(c1.get(Calendar.MONTH))
        println(c1.get(Calendar.MINUTE))

        println(c1.time)

        c1.set(2020, 5, 5)
        c1.set(2020, 0, 1)

        c1.add(Calendar.DATE, 50)
        c1.add(Calendar.DATE, 500)

        val c2 = Calendar.getInstance()
        c2.roll(Calendar.DATE, 50)

        c1.set(2020, 0, 1000)
        c1.isLenient = false

        // # Java 8 LocalDateTime
        val localDate1 = LocalDate.now()
        val localDate2 = LocalDate.of(2020, 1, 1)
        println(localDate2.dayOfMonth)
        println(localDate2.monthValue)
        println(localDate2.year)

        localDate1.plusMonths(50)
        localDate1.plusDays(5)
        localDate1.minusMonths(3)
        localDate1.minusWeeks(2)

        localDate1.minus(5, ChronoUnit.YEARS)

        val localDateTime = localDate1.atTime(15, 0, 5)
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")
        println(localDateTime.format(formatter))

        val localDate3 = LocalDate.of(2021, Month.FEBRUARY, 2)
        val localDate4 = localDate3.withYear(2020)
        val localDate5 = localDate4.withMonth(Month.DECEMBER.value)
        val localDate6 = localDate5.withMonth(3)
        val localDate7 = localDate6.with(ChronoField.DAY_OF_YEAR, 100)
        println(localDate7)

        // # Legacy Date
        val dt = Date()
        val simpleDateFormat = SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss a zzz")
        println(simpleDateFormat.format(dt))

        val simpleDateFormat2 = SimpleDateFormat("yyyy-MM-dd")
        val dt2 = simpleDateFormat2.parse("2020-11-10")

        val start = System.currentTimeMillis()

        val c3 = Calendar.getInstance()
        val gregorianCalendar2 = GregorianCalendar()
        println(gregorianCalendar2.get(Calendar.MONTH))
        println(gregorianCalendar2.get(Calendar.DATE))
        println(gregorianCalendar2.get(Calendar.YEAR))
        println(gregorianCalendar2.get(Calendar.HOUR))
        println(gregorianCalendar2.get(Calendar.MINUTE))
        println(gregorianCalendar2.get(Calendar.SECOND))
        println(gregorianCalendar2.isLeapYear(gregorianCalendar2.get(Calendar.YEAR)))

        gregorianCalendar2.set(Calendar.DATE, 5)
        gregorianCalendar2.set(Calendar.MONTH, 0)
        gregorianCalendar2.set(Calendar.YEAR, 2021)
        gregorianCalendar2.set(2021, 0, 5)
        gregorianCalendar2.add(Calendar.DATE, 5)
        gregorianCalendar2.add(Calendar.MONTH, 2)
        gregorianCalendar2.add(Calendar.YEAR, 3)
    }

    @Test
    fun miscTest() {
        val pairData = 1 to "One"
        val (i1, str) = pairData

        val tripleData = Triple(1, "One", "ek")
        val (i2, eStr1, bStr) = tripleData

        fun String.hasSpaces() = find { it == ' ' } != null

        println("This string has string".hasSpaces())
    }
}

open class WaterSupply(var needsProcessing: Boolean)

class TapWater : WaterSupply(true) {
    fun addChemicalCleaners() {
        needsProcessing = false
    }
}

class FishStoreWater : WaterSupply(false)

class LakeWater : WaterSupply(true) {
    fun filter() {
        needsProcessing = false
    }
}

// class Aquarium<T>(val waterSupply: T)

// is same as
// class Aquarium<T: Any?>(val waterSupply: T)

// To not allow null
// class Aquarium<T: Any>(val waterSupply: T)

fun addItemTo(aquarium: Aquarium<WaterSupply>) = println("item added")

interface Cleaner<in T: WaterSupply> {
    fun clean(waterSupply: T)
}

class TapWaterCleaner : Cleaner<TapWater> {
    override fun clean(waterSupply: TapWater) =  waterSupply.addChemicalCleaners()
}

// T extends non-nullable WaterSupply
class Aquarium<out T: WaterSupply>(val waterSupply: T) {
    fun addWater(cleaner: Cleaner<T>) {
        if (waterSupply.needsProcessing) {
            cleaner.clean(waterSupply)
        }
        println("water added")
    }
}

inline fun <reified R: WaterSupply> Aquarium<*>.hasWaterSupplyOfType() = waterSupply is R

fun <T: WaterSupply> isWaterClean(aquarium: Aquarium<T>) {
    println("aquarium water is clean: ${!aquarium.waterSupply.needsProcessing}")
}