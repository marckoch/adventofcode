package year2015.day09

fun main() {
    println(getMaximalCostInHamiltonPath(TEST_INPUT.lines(), 3))

    println(getMaximalCostInHamiltonPath(INPUT.lines(), 8))
}

fun getMaximalCostInHamiltonPath(lines: List<String>, dim: Int): Int {
    val matrix = convertToMatrix(lines, dim)

    // permute all routes and calculate cost for each route, take route with minimal cost
    return permute((0 until dim).toList())
        .maxOf { ints ->
            ints.windowed(2, 1) // the routes between two cities
                .sumOf {
                    matrix[it[0]][it[1]] // cost of this route
                }
        }
}