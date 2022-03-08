package mocks.hackerrankcert.intermediate.userFriendlyPasswordSystem.soluton1

const val MOD = 1000000007

fun authEvents(events: Array<Array<String>>): Array<Int> {
    val authenticator = PasswordAuthenticator()
    val results = mutableListOf<Int>()

    for (event in events) {
        when (event[0]) {
            "setPassword" -> authenticator.setPassword(event[1])
            "authorize" -> {
                val isValid = authenticator.validate(event[1].toInt())
                results.add(if (isValid) 1 else 0)
            }
        }
    }

    return results.toTypedArray()
}

class PasswordAuthenticator {
    private val codes = mutableListOf<Int>()
    private var passwordHash = 0
    private var shiftedHash = 0L

    init {
        for (i in 0 until 26) {
            codes.add(getCharCode('a' + i))
        }

        for (i in 0 until 26) {
            codes.add(getCharCode('A' + i))
        }

        for (i in 0 until 10) {
            codes.add(getCharCode('0' + i))
        }
    }

    fun setPassword(password: String) {
        passwordHash = calculateHash(password)
        shiftedHash = calculateShiftedHash(password)
    }

    private fun calculateShiftedHash(password: String): Long {
        var hash = 0L

        for (idx in password.lastIndex downTo 0) {
            val power = (getCharCode(password[idx]) * getPower(password.length - idx)) % MOD
            hash = (hash + power) % MOD
        }
        return hash
    }

    private fun calculateHash(password: String): Int {
        var hash = 0L

        for (idx in password.lastIndex downTo 0) {
            val power = (getCharCode(password[idx]) * getPower(password.lastIndex - idx)) % MOD
            hash = (hash + power) % MOD
        }

        return hash.toInt()
    }

    fun validate(hash: Int): Boolean {
        if (hash == passwordHash) return true

        for (code in codes) {
            if (((shiftedHash + code) % MOD).toInt() == hash) {
                return true
            }
        }

        return false
    }

    private fun getPower(n: Int): Long {
        var result = 1L
        for (i in 0 until n) {
            result = (result * 131) % MOD
        }
        return result
    }

    private fun getCharCode(ch: Char): Int {
        return ch.toByte().toInt()
    }
}

fun main() {
    val events = authEvents(
        arrayOf(
            arrayOf("setPassword", "000A"),
            arrayOf("authorize", "108738450"),
            arrayOf("authorize", "108738449"),
            arrayOf("authorize", "244736787")
        )
    )

    println(events.toList())
}

