package ae.medium.validStartingCity.solution1

fun validStartingCity(distances: List<Int>, fuel: List<Int>, mpg: Int): Int {
    val numCities = distances.size

    for (startingCityIdx in 0 until numCities) {
        var milesRemaining = 0

        for (currentCityIdx in startingCityIdx until startingCityIdx + numCities) {
            val currentCityIdxTranslated = currentCityIdx % numCities

            val fuelInCurrentCity = fuel[currentCityIdxTranslated]
            val distanceToNextCity = distances[currentCityIdxTranslated]

            milesRemaining += fuelInCurrentCity * mpg - distanceToNextCity

            if (milesRemaining < 0) {
                break
            }
        }

        if (milesRemaining >= 0) {
            return startingCityIdx
        }
    }

    return -1
}
