package playground

import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.collections.HashSet

fun rangeTest() {
    // 1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
    /*for (i in 1 .. 10) {
        print("$i, ")
    }*/

    // 1, 2, 3, 4, 5, 6, 7, 8, 9,
    /*for (i in 1 until 10) {
        print("$i, ")
    }*/

    // 1, 3, 5, 7, 9,
    /*for (i in 1 .. 10 step 2) {
        print("$i, ")
    }*/

    // 1, 3, 5, 7, 9,
    /*for (i in 1.rangeTo(10).step(2)) {
        print("$i, ")
    }*/

    // 10, 9, 8, 7, 6, 5, 4, 3, 2, 1,
    /*for (i in 10 downTo 1) {
        print("$i, ")
    }*/

    // 10, 8, 6, 4, 2,
    /*for (i in 10 downTo 1 step 2) {
        print("$i, ")
    }*/

    // 9, 7, 5, 3, 1,
    /*for (i in (1 .. 10 step 2).reversed()) {
        print("$i, ")
    }*/

    // a, d, g, j,
    /*for (c in 'a' .. 'l' step 3) {
        print("$c, ")
    }*/

    // l, i, f, c,
    /*for (c in 'l' downTo  'a' step 3) {
        print("$c, ")
    }*/

    // j, g, d, a,
    /*for (c in ('a' .. 'l' step 3).reversed()) {
        print("$c, ")
    }*/

    // a, d,
    /* for (c in 'a' until 'g' step 3) {
        print("$c, ")
    }*/
}

fun arrayIterationTest() {
    val nums = arrayOf(0, 1, 2, 3, 4, 5)

    /*for (i in nums) {
        print("$i, ")
    }*/

    /*nums.forEach {
        print("$it, ")
    }*/

    /*for (i in 0 .. nums.lastIndex) {
        print("${nums[i]}, ")
    }*/

    // 0, 2, 4,
    /*for (i in 0 .. nums.lastIndex step 2) {
        print("${nums[i]}, ")
    }*/

    // 0, 1, 2, 3, 4, 5,
    // ****************
    /*for (i in nums.indices) {
        print("${nums[i]}, ")
    }*/

    // 0, 2, 4,
    /*for (i in nums.indices step 2) {
        print("${nums[i]}, ")
    }*/

    // 5, 3, 1,
    /*for (i in nums.indices.reversed() step 2) {
        print("${nums[i]}, ")
    }*/

    // 5, 3,
    /*for (i in nums.lastIndex downTo 2 step 2) {
        print("${nums[i]}, ")
    }*/
}

fun listTest() {
    val immutableList1 = listOf(1, 2, 3, 4, 5)
    val immutableList2 = List(5) { it * 2 }
    val mutableList1 = mutableListOf(1, 2, 3, 4, 5)
    val mutableList2 = MutableList(5) { it * 2 }
    mutableList1.contains(3)
    mutableList1.remove(3)
    mutableList1.add(2, 8)
    mutableList1.removeAt(3)
    println(mutableList1)

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
    mutableMap[4]?.let {
        println("$it found for key 4")
    }

    val hashMap = HashMap<Int, String>()
    hashMap[1] = "One"
    hashMap[2] = "Two"
    hashMap[3] = "Three"
    hashMap.put(4, "Four")
    hashMap.containsKey(4)

    immutableMap.keys.forEach {
        println("Key is $it")
    }

    immutableMap.entries.forEach {
        println("Key is ${it.key}, Value is ${it.value}")
    }

    mutableMap.remove(6)

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
}