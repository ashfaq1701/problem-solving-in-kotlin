package ae.medium.phoneNumberMnemonics.solution1

fun phoneNumberMnemonics(phoneNumber: String): List<String> {
    val keyMap = mapOf(
        0 to "0",
        1 to "1",
        2 to "abc",
        3 to "def",
        4 to "ghi",
        5 to "jkl",
        6 to "mno",
        7 to "pqrs",
        8 to "tuv",
        9 to "wxyz"
    )

    val mnemonics = mutableListOf<String>()
    helper(phoneNumber, mnemonics, keyMap, mutableListOf(), 0)

    return mnemonics
}

fun helper(phoneNumber: String, mnemonics: MutableList<String>, keyMap: Map<Int, String>, current: MutableList<Char>, idx: Int) {
    if (idx == phoneNumber.length) {
        mnemonics.add(String(current.toCharArray()))
        return
    }

    val keyChars = keyMap[phoneNumber[idx] - '0']!!
    for (c in keyChars) {
        current.add(c)
        helper(phoneNumber, mnemonics, keyMap, current, idx + 1)
        current.removeAt(current.lastIndex)
    }
}
