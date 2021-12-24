package ae.easy.firstNonRepeatingCharacter.solution1

fun firstNonRepeatingCharacter(string: String): Int {
    for (i in string.indices) {
        var foundDuplicate = false
        for (j in string.indices) {
            if (i != j && string[i] == string[j]) {
                foundDuplicate = true
            }
        }

        if (!foundDuplicate) {
            return i
        }
    }
    return -1
}
