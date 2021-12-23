package ae.veryHard.airportConnections.solution1

fun airportConnections(airports: List<String>, routes: List<Pair<String, String>>, startingAirport: String): Int {
    val graph = buildGraph(airports, routes)
    val unreachableAirports = populateUnreachableAirports(graph, airports, startingAirport)
    markUnreachableAirportsWithAirportsTheyUnlock(graph, unreachableAirports)
    return getNumberOfNewConnectionsRequired(graph, unreachableAirports)
}

data class AirportNode(val airport: String) {
    val connections = mutableListOf<String>()
    var isReachable = true
    var unlocksUnreachableAirports = mutableListOf<String>()
}

fun buildGraph(airports: List<String>, routes: List<Pair<String, String>>): MutableMap<String, AirportNode> {
    val graph = airports.map { airport ->
        airport to AirportNode(airport)
    }.toMap().toMutableMap()

    for ((airport, connection) in routes) {
        graph[airport]!!.connections.add(connection)
    }

    return graph
}

fun populateUnreachableAirports(graph: MutableMap<String, AirportNode>, airports: List<String>, startingAirport: String): List<AirportNode> {
    val startingNode = graph[startingAirport]!!
    val visited = mutableSetOf<String>()

    dfsVisitAirports(startingNode, graph, visited)

    return airports.filter { airport ->
        !visited.contains(airport)
    }.map {
        graph[it]!!.apply {
            isReachable = false
        }
    }
}

fun markUnreachableAirportsWithAirportsTheyUnlock(graph: MutableMap<String, AirportNode>, unreachableAirports: List<AirportNode>) {
    for (unreachableAirport in unreachableAirports) {
        val visited = mutableSetOf<String>()
        dfsPopulateUnlockedUnreachableNodes(unreachableAirport, graph, visited)
        unreachableAirport.unlocksUnreachableAirports = visited.toMutableList()
    }
}

fun dfsVisitAirports(node: AirportNode, graph: MutableMap<String, AirportNode>, visited: MutableSet<String>) {
    val airport = node.airport
    if (visited.contains(airport)) return
    visited.add(airport)
    for (connection in node.connections) {
        dfsVisitAirports(graph[connection]!!, graph, visited)
    }
}

fun dfsPopulateUnlockedUnreachableNodes(node: AirportNode, graph: MutableMap<String, AirportNode>, visited: MutableSet<String>) {
    val airport = node.airport
    if (visited.contains(airport)) return
    if (node.isReachable) return
    visited.add(airport)
    for (connection in node.connections) {
        dfsPopulateUnlockedUnreachableNodes(graph[connection]!!, graph, visited)
    }
}

fun getNumberOfNewConnectionsRequired(graph: MutableMap<String, AirportNode>, unreachableAirportNodes: List<AirportNode>): Int {
    val sortedUnreachableAirports = unreachableAirportNodes.sortedWith(Comparator<AirportNode> { airportNode1, airportNode2 ->
        airportNode2.unlocksUnreachableAirports.size.compareTo(airportNode1.unlocksUnreachableAirports.size)
    })

    var numberOfNewConnectionsRequired = 0

    for (unreachableAirportNode in sortedUnreachableAirports) {
        if (unreachableAirportNode.isReachable) continue
        numberOfNewConnectionsRequired += 1

        for (unlockedUnreachableAirport in unreachableAirportNode.unlocksUnreachableAirports) {
            graph[unlockedUnreachableAirport]!!.isReachable = true
        }
    }

    return numberOfNewConnectionsRequired
}