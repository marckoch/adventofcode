package year2015.day09

import adventofcode.Problem
import util.lists.permute
import util.parser.findTokens

fun main() {
    AOC2015D09(SAMPLE).run1()
    AOC2015D09(INPUT).run1()

    AOC2015D09(SAMPLE).run2()
    AOC2015D09(INPUT).run2()
}

val LINE_PATTERN = """(.*) to (.*) = (\d*)""".toRegex()

class AOC2015D09(input: String) : Problem(input) {
    override fun solve1() = getMinimalCostInHamiltonPath(inputLines())

    override fun solve2() = getMaximalCostInHamiltonPath(inputLines())
}

fun getMinimalCostInHamiltonPath(lines: List<String>): Int {
    val distances = buildAdjacencyMatrix(lines)

    // permute all routes and calculate cost for each route, take route with minimal cost
    val allPaths = distances.indices.toList().permute()
    return allPaths.minOf { totalDistanceOfPath(it, distances) }
}

fun getMaximalCostInHamiltonPath(lines: List<String>): Int {
    val distances = buildAdjacencyMatrix(lines)

    // permute all routes and calculate cost for each route, take route with maximal cost
    val allPaths = distances.indices.toList().permute()
    return allPaths.maxOf { totalDistanceOfPath(it, distances) }
}

fun totalDistanceOfPath(path: List<Int>, distanceMatrix: Array<IntArray> ): Int {
    return path.windowed(2, 1).sumOf { distanceMatrix[it[0]][it[1]] }
}

// convert lines of format "<from> to <to> = <distance>" to matrix form
// adjacencyMatrix
fun buildAdjacencyMatrix(lines: List<String>): Array<IntArray> {
    val allNodes = lines.flatMap { line ->
        LINE_PATTERN.findTokens(line).take(2) // first 2 tokens are nodes/cities
    }.distinct().sorted()
    val nodeCount = allNodes.size
    val matrix = Array(nodeCount) { IntArray(nodeCount) }

    lines.forEach { line ->
        val (from, to, distance) = LINE_PATTERN.findTokens(line)

        val indexOfFrom = allNodes.indexOf(from)
        val indexOfTo = allNodes.indexOf(to)

        matrix[indexOfFrom][indexOfTo] = distance.toInt()
        matrix[indexOfTo][indexOfFrom] = distance.toInt()
    }

    // printMatrix(matrix, "\t")
    return matrix
}

fun printMatrix(matrix: Array<IntArray>, delimiter: String = "") {
    println("-------")
    for (row in matrix) {
        var del = ""
        for (i in row) {
            print(del + i)
            del = delimiter
        }
        println()
    }
}

