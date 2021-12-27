package ae.medium.validIpAddresses.solution1

import kotlin.math.min

fun validIPAddresses(string: String): List<String> {
    val ipAddresses = mutableListOf<String>()

    for (i in 1 until min(string.length, 4)) {
        val ipAddressParts = MutableList(4) { "" }

        ipAddressParts[0] = string.substring(0, i)
        if (!isValidPart(ipAddressParts[0])) {
            continue
        }

        for (j in i + 1 until min(i + 4, string.length)) {
            ipAddressParts[1] = string.substring(i, j)
            if (!isValidPart(ipAddressParts[1])) {
                continue
            }

            for (k in j + 1 until min(j + 4, string.length)) {
                ipAddressParts[2] = string.substring(j, k)
                ipAddressParts[3] = string.substring(k)

                if (!isValidPart(ipAddressParts[2]) || !isValidPart(ipAddressParts[3])) {
                    continue
                }

                ipAddresses.add(ipAddressParts.joinToString("."))
            }
        }
    }

    return ipAddresses
}

fun isValidPart(str: String): Boolean {
    val strAsInt = str.toInt()
    if (strAsInt > 255) {
        return false
    }
    return str.length == strAsInt.toString().length
}
