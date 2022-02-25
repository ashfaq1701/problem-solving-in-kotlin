package facebook.portal.rotationalCipher.solution1

fun main(args : Array<String>) {
    println(rotationalCipher("abcdefghijklmNOPQRSTUVWXYZ0123456789", 39))
}

fun rotationalCipher(input: String, rotationFactor: Int): String {
    val outputCharArray = input.map { ch ->
        when {
            ch.isLetter() -> {
                val firstLetter = if (ch.isLowerCase()) 'a' else 'A'
                val charIdx = ch - firstLetter
                val rotatedCharIdx = charIdx + rotationFactor
                val roundedRotatedCharIdx = rotatedCharIdx % 26
                firstLetter + roundedRotatedCharIdx
            }
            ch.isDigit() -> {
                val charIdx = ch - '0'
                val rotatedCharIdx = charIdx + rotationFactor
                val roundedRotatedCharIdx = rotatedCharIdx % 10
                '0' + roundedRotatedCharIdx
            }
            else -> ch
        }
    }.toCharArray()
    return String(outputCharArray)
}