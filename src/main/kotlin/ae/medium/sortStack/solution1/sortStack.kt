package ae.medium.sortStack.solution1

fun sortStack(stack: MutableList<Int>): MutableList<Int> {
    if (stack.isEmpty()) {
        return stack
    }

    val top = stack.last()
    stack.removeAt(stack.lastIndex)

    sortStack(stack)
    insertInSortedStack(stack, top)

    return stack
}

fun insertInSortedStack(stack: MutableList<Int>, value: Int) {
    if (stack.isEmpty() || value >= stack.last()) {
        stack.add(value)
        return
    }

    val top = stack.last()
    stack.removeAt(stack.lastIndex)

    insertInSortedStack(stack, value)

    stack.add(top)
}
