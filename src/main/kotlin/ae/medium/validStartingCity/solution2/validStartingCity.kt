package ae.medium.validStartingCity.solution2

fun validStartingCity(distances: List<Int>, fuel: List<Int>, mpg: Int): Int {
    val numCities = distances.size

    var startingCityCandidate = 0
    var milesRemainingInStartingCityCandidate = 0

    var arrivedToCurrentCityWithMiles = 0

    for (currentCityIdx in 1 until numCities) {
        val fuelInPreviousCity = fuel[currentCityIdx - 1]
        val distanceFromPreviousCity = distances[currentCityIdx - 1]

        arrivedToCurrentCityWithMiles += fuelInPreviousCity * mpg - distanceFromPreviousCity

        if (arrivedToCurrentCityWithMiles < milesRemainingInStartingCityCandidate) {
            milesRemainingInStartingCityCandidate = arrivedToCurrentCityWithMiles
            startingCityCandidate = currentCityIdx
        }
    }

    return startingCityCandidate
}
