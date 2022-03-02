package facebook.portal.encryptedWords.solution1

fun main(args : Array<String>) {
    println(findEncryptedString("abc"))
    println(findEncryptedString("abcd"))
    println(findEncryptedString("abcxcba"))
    println(findEncryptedString("facebook"))
}

fun findEncryptedString(args: String): String {
    return findEncryptedStringHelper(args, 0, args.lastIndex)
}

fun findEncryptedStringHelper(str: String, left: Int, right: Int): String {
    if (left > right) return ""

    val mid = left + (right - left) / 2

    return str[mid].toString() + findEncryptedStringHelper(str, left, mid - 1) + findEncryptedStringHelper(str, mid + 1, right)
}
