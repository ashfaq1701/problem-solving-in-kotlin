package ctci.integerToEnglishWords.solution1

class Solution {
    fun numberToWords(num: Int): String {
        if (num == 0) {
            return "Zero"
        }

        val billion = num / 1_000_000_000
        val million = (num - billion * 1_000_000_000) / 1_000_000
        val thousand = (num - billion * 1_000_000_000 - million * 1_000_000) / 1000
        val rest = num - billion * 1_000_000_000 - million * 1_000_000 - thousand * 1000

        var result = ""

        if (billion != 0) {
            result += "${three(billion)} Billion"
        }

        if (million != 0) {
            if (result.isNotEmpty()) {
                result += " "
            }

            result += "${three(million)} Million"
        }

        if (thousand != 0) {
            if (result.isNotEmpty()) {
                result += " "
            }

            result += "${three(thousand)} Thousand"
        }

        if (rest != 0) {
            if (result.isNotEmpty()) {
                result += " "
            }

            result += three(rest)
        }

        return result
    }

    private fun one(num: Int): String {
        return when(num) {
            1 -> "One"
            2 -> "Two"
            3 -> "Three"
            4 -> "Four"
            5 -> "Five"
            6 -> "Six"
            7 -> "Seven"
            8 -> "Eight"
            9 -> "Nine"
            else -> throw IllegalArgumentException("Invalid number")
        }
    }

    private fun two(num: Int): String {
        return when {
            num == 0 -> ""
            num < 10 -> one(num)
            num < 20 -> twoLessThanTwenty(num)
            else -> {
                val tenner = num / 10
                val rest = num % 10

                if (rest != 0) {
                    "${ten(tenner)} ${one(rest)}"
                } else {
                    ten(tenner)
                }
            }
        }
    }

    private fun three(num: Int): String {
        val hundred = num / 100
        val rest = num % 100

        return when {
            hundred * rest != 0 -> "${one(hundred)} Hundred ${two(rest)}"
            rest != 0 -> two(rest)
            else -> "${one(hundred)} Hundred"
        }
    }

    private fun twoLessThanTwenty(num: Int): String {
        return when(num) {
            10 -> "Ten"
            11 -> "Eleven"
            12 -> "Twelve"
            13 -> "Thirteen"
            14 -> "Fourteen"
            15 -> "Fifteen"
            16 -> "Sixteen"
            17 -> "Seventeen"
            18 -> "Eighteen"
            19 -> "Nineteen"
            else -> throw IllegalArgumentException("Invalid number")
        }
    }

    private fun ten(num: Int): String {
        return when(num) {
            2 -> "Twenty"
            3 -> "Thirty"
            4 -> "Forty"
            5 -> "Fifty"
            6 -> "Sixty"
            7 -> "Seventy"
            8 -> "Eighty"
            9 -> "Ninety"
            else -> throw IllegalArgumentException("Invalid number")
        }
    }
}