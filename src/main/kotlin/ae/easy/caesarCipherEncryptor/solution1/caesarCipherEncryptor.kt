package ae.easy.caesarCipherEncryptor.solution1

fun caesarCipherEncryptor(string: String, key: Int): String {
    val roundedKey = key % 26

    val charArray = string.map { c ->
        val charValue = c - 'a'
        val shiftedCharValue = (charValue + roundedKey) % 26
        'a' + shiftedCharValue
    }.toCharArray()

    return String(charArray)
}
