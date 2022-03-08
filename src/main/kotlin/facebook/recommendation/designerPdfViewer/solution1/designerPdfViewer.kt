package facebook.recommendation.designerPdfViewer.solution1

import kotlin.math.max

fun designerPdfViewer(h: Array<Int>, word: String): Int {
    val width = word.length
    var maxHeight = Int.MIN_VALUE

    for (ch in word) {
        val chIdx = ch - 'a'
        val height = h[chIdx]
        maxHeight = max(maxHeight, height)
    }

    return maxHeight * width
}