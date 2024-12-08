package year2024.day08

import kotlin.math.max

fun main() {
    AOC2024D08(SAMPLE).solvePart1().let { println(it) } // 14
    AOC2024D08(INPUT).solvePart1().let { println(it) } // 364

    AOC2024D08(SAMPLE).solvePart2().let { println(it) } // 34
    AOC2024D08(INPUT).solvePart2().let { println(it) } //
}

class AOC2024D08(input: String) {
    // a list of points for each antenna type (char)
    private val antennas: Map<Char, List<Point>> = parse(input)
    private var maxRow = 0
    private var maxCol = 0

    fun solvePart1(): Int {
        val nodes = antennas.values.flatMap { antennaGroup ->
            buildCombinations(antennaGroup)
                .flatMap { findNodesWithinMap(it.first, it.second) }
        }.toSet()
        return nodes.size
    }

    fun solvePart2(): Int {
        val nodes = antennas.values.flatMap { antennaGroup ->
            buildCombinations(antennaGroup)
                .flatMap { findNodesWithinMap2(it.first, it.second) }
        }.toSet()
        return nodes.size
    }

    private fun findNodesWithinMap(p1: Point, p2: Point): List<Point> {
        val diff = p1 - p2

        val nodes = mutableListOf<Point>()

        var newNode = p1 + diff
        if (isInMap(newNode)) {
            nodes.add(newNode)
        }

        newNode = p2 - diff
        if (isInMap(newNode)) {
            nodes.add(newNode)
        }

        return nodes
    }

    private fun findNodesWithinMap2(p1: Point, p2: Point): List<Point> {
        val diff = p1 - p2

        val nodes = mutableListOf<Point>()
        // now antennas also count
        nodes.add(p1)
        nodes.add(p2)

        var newNode = p1 + diff
        while (isInMap(newNode)) {
            nodes.add(newNode)
            newNode += diff
        }

        newNode = p2 - diff
        while (isInMap(newNode)) {
            nodes.add(newNode)
            newNode -= diff
        }

        return nodes
    }

    private fun isInMap(point: Point): Boolean {
        return point.first in 0..maxRow && point.second in 0..maxCol
    }

    // build all combinations
    // List<T> -> List<Pair<T, T>>, e.g.
    // [1,2,3] -> [ (1,2), (1,3), (2,3) ]
    private fun <T> buildCombinations(things: List<T>): List<Pair<T, T>> {
        val combinations = mutableListOf<Pair<T, T>>()
        for (i in things.indices) {
            for (j in i + 1 until things.size) {
                combinations.add(Pair(things[i], things[j]))
            }
        }
        return combinations
    }

    fun parse(input: String): Map<Char, List<Point>> {
        val antennas = mutableMapOf<Char, MutableList<Point>>()
        input.lines().withIndex().forEach { (rowIndex, line) ->
//            println(line)
            maxRow = max(maxRow, rowIndex)
            line.withIndex().forEach { (colIndex, char) ->
                maxCol = max(maxCol, colIndex)
                if (char != '.') {
                    if (antennas[char] == null) {
                        antennas[char] = mutableListOf()
                    }
                    antennas[char]?.add(Point(rowIndex, colIndex))
                }
            }
        }
        return antennas
    }
}

typealias Point = Pair<Int, Int>

operator fun Point.plus(that: Point): Point {
    return Point(this.first + that.first, this.second + that.second)
}

operator fun Point.minus(that: Point): Point {
    return Point(this.first - that.first, this.second - that.second)
}