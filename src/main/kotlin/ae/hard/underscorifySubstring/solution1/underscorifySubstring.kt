package ae.hard.underscorifySubstring.solution1

fun underscorifySubstring(string: String, substring: String): String {
    val mergedLocations = mergeLocations(getLocations(string, substring))
    return underscorify(string, mergedLocations)
}

// Merge intervals algorithm (for locations)
fun mergeLocations(locations: List<List<Int>>): List<List<Int>> {
    // Edge case - if locations array is empty then return
    if (locations.isEmpty()) return locations

    // Push first interval into merged intervals
    val mergedLocations = MutableList<List<Int>>(1) { locations.first() }

    // For all other locations
    for (i in 1 until locations.size) {
        // Get current location
        val currentLocation = locations[i]
        val lastLocation = mergedLocations.last()

        // If the locations does not overlap then push the current location into merged location.
        if (currentLocation[0] > lastLocation[1]) {
            mergedLocations.add(currentLocation)
            // If they overlap then change the end location of the last merged location.
        } else {
            mergedLocations[mergedLocations.lastIndex] = listOf(lastLocation[0], currentLocation[1])
        }
    }

    return mergedLocations
}

// Get locations of substr inside str using language default find function.
fun getLocations(string: String, substring: String): List<List<Int>> {
    val locations = mutableListOf<List<Int>>()

    // Start from index 0.
    var startIdx = 0

    // While we have more valid start index position
    while (startIdx < string.length) {

        // Find the position of substr inside of str starting from start index using language default find function
        val nextIdx = string.indexOf(substring, startIdx)

        // If substring can't be found inside str starting from start index, then it can't be found anymore, so break.
        if (nextIdx == -1) break

        // Otherwise insert { nextIdx, nextIdx + substring size } inside of locations.
        locations.add(listOf(nextIdx, nextIdx + substring.length))

        // Then set startIdx is the next index of the first index of the substr location.
        // abcdddeeefabcfff, substr = abc
        // For first istance of abc, next startIdx will be 1 (b)
        startIdx = nextIdx + 1
    }

    return locations
}

fun underscorify(string: String, locations: List<List<Int>>): String {
    // Pointer inside string
    var strIdx = 0

    // Pointer inside locations array
    var locationIdx = 0

    // Underscorified result string
    val finalChars = mutableListOf<Char>()

    // If current character is between underscores. Initially it is false.
    var inBetweenUnderscores = false

    // The index inside the interval under consideration.
    // Either 0 or 1, means either starting index or ending index.
    // Initially starting index.
    var i = 0

    // As long we are inside of the string and inside of the locations array
    while (strIdx < string.length && locationIdx < locations.size) {
        val currentLocation = locations[locationIdx]

        // If current string index is equal to current interval's current index (0 or 1)
        // Either start or end index
        if (strIdx == currentLocation[i]) {
            // Insert _ inside result.
            finalChars.add('_')

            // Revert inBetweenUnderscores.
            // If starting an interval then revert inBetweenUnderscores to true.
            // If ending an interval then revert inBetweenUnderscores to false.
            inBetweenUnderscores = !inBetweenUnderscores

            // Revert the index inside of the interval.
            // If starting an interval then toggle it to 1, otherwise toggle it to 0.
            i = if (i == 0) 1 else 0

            // If inBetweenUnderscores is reverted back to false, then it means that we are done with previous interval
            // Now we will consider the next interval. So we will increment the index of the location.
            if (!inBetweenUnderscores) {
                locationIdx += 1
            }
        }

        // Regardless we will insert current character into result array and increment the index to the next character.
        finalChars.add(string[strIdx])
        strIdx += 1
    }

    // Consider this case
    // str = test abctest, substr = test
    // Result of this should be _test_ abc_test_
    // Now from string the locations returned will be like
    // [0, 4], [8, 12]
    // Now from the [8, 12] interval, we will break from loop before we reach 12 (because it is str.size())
    // So the inner logic will never run to insert the last underscore.
    // And here we will be at locations.size() - 1.
    // So we need to insert the last underscore in this case.
    if (locationIdx < locations.size) {
        finalChars.add('_')
    }

    // Consider this case,
    // str = test abc, substr = test
    // Result of this should be _test_ abc
    // After the occurance of substr we will break out from the loop.
    // So to insert the remaining " abc", we need to run the below loop and and them to result.
    while (strIdx < string.length) {
        finalChars.add(string[strIdx])
        strIdx += 1
    }

    // Construct a string from result vector and return.
    return String(finalChars.toCharArray())
}
