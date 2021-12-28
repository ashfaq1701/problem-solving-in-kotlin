package ae.hard.fourNumberSum.solution1

// To avoid duplicates we need following mechanism.
// Loop through each element i in the array
//   For each item j after i compute sum = array[i] + array[j]
//   If diff = target - sum is found in the map then build quadruplets with
//   array[i], array[j] and items found in the map
//   If diff is not found then DO NOT add the pair in the map.
//   For each element k from 0 until i compute sum = array[i] + array[k]
//   Add { array[k], array[i] } against sum into the map
fun fourNumberSum(array: MutableList<Int>, targetSum: Int): List<List<Int>> {
    val allPairSums = mutableMapOf<Int, MutableList<List<Int>>>()
    val quadruplets = mutableListOf<List<Int>>()

    for (i in 1 until array.lastIndex) {

        for (j in i + 1 .. array.lastIndex) {
            val currentSum = array[i] + array[j]
            val difference = targetSum - currentSum

            if (allPairSums.contains(difference)) {
                for (pair in allPairSums[difference]!!) {
                    val quadruplet = pair.plus(listOf(array[i], array[j]))
                    quadruplets.add(quadruplet)
                }
            }
        }

        for (k in 0 until i) {
            val currentSum = array[i] + array[k]

            if (allPairSums.contains(currentSum)) {
                allPairSums[currentSum]!!.add(listOf(array[k], array[i]))
            } else {
                allPairSums[currentSum] = mutableListOf(listOf(array[k], array[i]))
            }
        }
    }

    return quadruplets
}
