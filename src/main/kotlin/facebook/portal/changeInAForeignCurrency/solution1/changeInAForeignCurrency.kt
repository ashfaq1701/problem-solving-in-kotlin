package facebook.portal.changeInAForeignCurrency.solution1

fun main(args : Array<String>) {
    println(canGetExactChange(94, arrayOf(5, 10, 25, 100, 200)))
    println(canGetExactChange(75, arrayOf(4, 17, 29)))
}

fun canGetExactChange(target: Int, denominations: Array<Int>): Boolean {
    return canGetExactChangeHelper(target, denominations, mutableMapOf())
}

fun canGetExactChangeHelper(target: Int, denominations: Array<Int>, memo: MutableMap<Int, Boolean>): Boolean {
    if (target == 0) return true

    if (target in memo) return memo[target]!!

    memo[target] = false

    for (denomination in denominations) {
        if (target >= denomination && canGetExactChangeHelper(target - denomination, denominations, memo)) {
            memo[target] = true
            break
        }
    }

    return memo[target]!!
}