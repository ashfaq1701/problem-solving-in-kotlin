package leetcode.validWordAbbreviation.solution1

class Solution {
    fun validWordAbbreviation(word: String, abbr: String): Boolean {
        var wordIdx = 0
        var abbrIdx = 0

        while (wordIdx < word.length && abbrIdx < abbr.length) {

            if (abbr[abbrIdx].isDigit() && abbr[abbrIdx] != '0') {
                var len = 0

                while (abbrIdx < abbr.length && abbr[abbrIdx].isDigit()) {
                    len = len * 10 + (abbr[abbrIdx] - '0')
                    abbrIdx += 1
                }

                wordIdx += len
            } else {
                if (word[wordIdx] != abbr[abbrIdx]) {
                    return false
                }

                wordIdx += 1
                abbrIdx += 1
            }
        }

        return wordIdx == word.length && abbrIdx == abbr.length
    }
}