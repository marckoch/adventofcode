package year2015.day13

import adventofcode.Problem
import util.lists.permute
import util.parser.findTokens

val LINE_PATTERN = """(\w*) would (gain|lose) (\d*) happiness units by sitting next to (\w*).""".toRegex()

fun main() {
    AOC2015D13(SAMPLE).run()
    AOC2015D13(INPUT).run()
}

class AOC2015D13(input: String) : Problem(input) {
    override fun solve1() = getMaximalHappiness(inputLines())

    // including me in the matrix is like adding one row of 0's and one column of 0's
    // we can do this easily by increasing the dimension by 1
    override fun solve2() = getMaximalHappiness(inputLines(), true)

    // permute all seating arrangements and calculate total happiness for each arrangement,
    // take the one with maximal happiness
    private fun getMaximalHappiness(lines: List<String>, addMe: Boolean = false): Int {
        val matrix = buildAdjacencyMatrix(lines, addMe)

        val allArrangements = matrix.indices.toList().permute()
        return allArrangements.maxOf { happinessOfArrangement(matrix, it) }
    }

    private fun happinessOfArrangement(matrix: Array<IntArray>, arrangement: List<Int>): Int {
        with(arrangement) {
            val happiness = windowed(2, 1).sumOf {
                    // count both happiness values! A to B and B to A !
                    matrix[it[0]][it[1]] + matrix[it[1]][it[0]]
                }
            // add wrap around happiness! e.g. first and last of list!
            return happiness + matrix[first()][last()] + matrix[last()][first()]
        }
    }

    // adjacencyMatrix
    private fun buildAdjacencyMatrix(lines: List<String>, addMe: Boolean = false): Array<IntArray> {
        val allNodes = lines.flatMap { line ->
            LINE_PATTERN.findTokens(line).take(1)
        }.distinct().sorted()
        val nodeCount = allNodes.size + if (addMe) 1 else 0
        val matrix = Array(nodeCount) { IntArray(nodeCount) }

        lines.forEach { line ->
            val (from, gainOrLose, happiness, to) = LINE_PATTERN.findTokens(line)

            val indexOfFrom = allNodes.indexOf(from)
            val indexOfTo = allNodes.indexOf(to)

            matrix[indexOfFrom][indexOfTo] = if (gainOrLose == "lose") -happiness.toInt() else happiness.toInt()
        }

        // printMatrix(matrix, "\t")
        return matrix
    }

    private fun printMatrix(matrix: Array<IntArray>, delimiter: String = "") {
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
}

