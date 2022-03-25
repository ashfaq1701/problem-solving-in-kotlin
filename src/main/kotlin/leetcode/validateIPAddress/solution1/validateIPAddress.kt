package leetcode.validateIPAddress.solution1

enum class AddressType { IPV4, IPV6, INVALID }

class Solution {
    fun validIPAddress(queryIP: String): String {
        return when(getAddressType(queryIP)) {
            AddressType.IPV4 -> validateIPV4(queryIP)
            AddressType.IPV6 -> validateIPV6(queryIP)
            else -> "Neither"
        }
    }

    private fun getAddressType(ip: String): AddressType {
        val dotSeparated = ip.split(".")
        if (dotSeparated.size == 4) {
            return AddressType.IPV4
        }

        val colonSeparated = ip.split(":")
        if (colonSeparated.size == 8) {
            return AddressType.IPV6
        }

        return AddressType.INVALID
    }

    private fun validateIPV4(ip: String): String {
        val parts = ip.split(".")

        for (part in parts) {
            val partAsInt = try {
                part.toInt()
            } catch (_: Exception) {
                return "Neither"
            }

            if (partAsInt.toString() != part) return "Neither"

            if (partAsInt < 0 || partAsInt > 255) return "Neither"
        }

        return "IPv4"
    }

    private fun validateIPV6(ip: String): String {
        val parts = ip.split(":")

        for (part in parts) {
            if (part.length !in 1 .. 4) return "Neither"
            val matchesPattern = "^[0-9A-Fa-f]*$".toRegex().matches(part)
            if (!matchesPattern) return "Neither"
        }

        return "IPv6"
    }
}