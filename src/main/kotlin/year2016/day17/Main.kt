package year2016.day17

import adventofcode.Problem
import util.direction.Direction
import util.hex.toLowerHex
import util.point.Point
import util.point.dirTo
import util.point.neighbors
import java.security.MessageDigest

const val SAMPLE = "ihgpwlah"
const val INPUT = "bwnlcvfs"

fun main() {
    AOC2016D17(SAMPLE).run()
    AOC2016D17(INPUT).run()
}

class AOC2016D17(input: String) : Problem(input) {
    private val salt = input
    private val md5Digester = MessageDigest.getInstance("MD5")
    private val start = 1 to 1
    private val end = 4 to 4

    override fun solve1() = bfs(salt = salt, from = start, to = end)
    override fun solve2() = bfs2(salt = salt, from = start, to = end)

    // find path from 'from' to 'to'
    fun bfs(salt: String, from: Point, to: Point): String {
        val unvisited = ArrayDeque<Pair<Point, String>>().apply { add(from to "") }

        // note: no 'visited' check here, because a field can be visited multiple times
        // e.g. backtracking is allowed here

        while (unvisited.isNotEmpty()) {
            val (current, currentPath) = unvisited.removeFirst()
            if (current == to) return currentPath

            neighbors(current, currentPath, salt).forEach { (neighbor, pathToNeighbor) ->
                unvisited.add(neighbor to pathToNeighbor)
            }
        }
        error("No path to target found!")
    }

    fun bfs2(salt: String, from: Point, to: Point): Int {
        val unvisited = ArrayDeque<Pair<Point, String>>().apply { add(from to "") }

        // note: no 'visited' check here, because a field can be visited multiple times
        // e.g. backtracking is allowed here

        val pathsToEnd = mutableListOf<String>()

        while (unvisited.isNotEmpty()) {
            val (current, currentPath) = unvisited.removeFirst()
            if (current == to) {
                pathsToEnd.add(currentPath)
                continue
            }

            neighbors(current, currentPath, salt).forEach { (neighbor, pathToNeighbor) ->
                unvisited.add(neighbor to pathToNeighbor)
            }
        }
        return pathsToEnd.maxOf { it.length }
    }

    private fun neighbors(p: Point, currentPath: String, salt: String): List<Pair<Point, String>> {
        // open doors in point 'p' only depend on the currentPath, NOT 'p' itself
        val openDoors = calcOpenDoors(salt, currentPath)

        return p.neighbors()
            .filter { n -> n.first in 1..4 && n.second in 1..4 } // we have to stay in the field
            .map { n -> n to direction(from = p, to = n) }
            .filter { (_, d) -> d in openDoors }
            .map { (n, d) -> n to currentPath + d }
    }

    private fun direction(from: Point, to: Point) = when (from.dirTo(to)) {
        Direction.NORTH -> 'U'
        Direction.WEST -> 'L'
        Direction.SOUTH -> 'D'
        Direction.EAST -> 'R'
    }

    private fun calcOpenDoors(salt: String, path: String): List<Char> {
        val OPEN = "bcdef"
        val DOORS = "UDLR"

        val hash = md5Digester.digest((salt+path).toByteArray())
        val firstFour = hash.toLowerHex().take(4)

        val open = mutableListOf<Char>()
        for (i in firstFour.indices) {
            if (firstFour[i] in OPEN) open.add(DOORS[i])
        }
        return open
    }
}
