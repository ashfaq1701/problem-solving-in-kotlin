package ae.easy.tournamentWinner.solution1

// Simulation
fun tournamentWinner(competitions: List<List<String>>, results: List<Int>): String {
    val points = mutableMapOf<String, Int>()
    var winnerTeam = ""

    for (i in 0 .. competitions.lastIndex) {
        val (homeTeam, awayTeam) = competitions[i]
        val result = results[i]

        val currentWinner = if (result == 1) homeTeam else awayTeam

        if (!points.containsKey(currentWinner)) {
            points[currentWinner] = 0
        }
        points[currentWinner] = points[currentWinner]!! + 3

        if (winnerTeam == "" || points[currentWinner]!! > points[winnerTeam]!!) {
            winnerTeam = currentWinner
        }
    }

    return winnerTeam
}
