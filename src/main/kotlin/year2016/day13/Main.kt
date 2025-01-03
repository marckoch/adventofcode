package year2016.day13

import adventofcode.Problem
import util.ints.isOdd
import util.point.Point
import util.point.neighbors

fun main() {
    AOC2016D13(SAMPLE).run()
    AOC2016D13(INPUT).run()
}

fun f(x: Int, y: Int, fav: Int) : Int = x*x + 3*x + 2*x*y + y + y*y + fav

class AOC2016D13(input: String) : Problem(input) {
    private val favoriteNumber = inputLines().first().toInt()
    private val to = inputLines().drop(1).first().split(",").map { it.toInt() }.let { list -> Point(list[0], list[1]) }

    override fun solve1() = bfs(from = 1 to 1, to, favoriteNumber)
    override fun solve2() = bfs(from = 1 to 1, limit = 50, favoriteNumber)

    // find path from 'from' to 'to'
    private fun bfs(from: Point, to: Point, fav: Int): Int {
        val unvisited = ArrayDeque<Point>().apply { add(from) }

        // remember visited points and length to this point
        val visited = mutableMapOf(from to 0)

        while (unvisited.isNotEmpty()) {
            val current = unvisited.removeFirst()
            val currentDistance = visited.getValue(current)
            if (current == to) return currentDistance

            neighbors(current, fav).forEach { n ->
                if (n !in visited) {
                    unvisited.add(n)
                    val nextDistance = currentDistance + 1
                    visited[n] = nextDistance
                }
            }
        }
        error("No path to target found!")
    }

    // visit all points max 'limit' away from 'from'
    private fun bfs(from: Point, limit: Int, fav: Int): Int {
        val unvisited = ArrayDeque<Point>().apply { add(from) }

        // remember visited points and length to this point
        val visited = mutableMapOf(from to 0)

        while (unvisited.isNotEmpty()) {
            val current = unvisited.removeFirst()
            val currentDistance = visited.getValue(current)

            // ignore fields that are too far away
            if (currentDistance >= limit) {
                continue
            }

            neighbors(current, fav).forEach { n ->
                if (n !in visited) {
                    unvisited.add(n)
                    val nextDistance = currentDistance + 1
                    visited[n] = nextDistance
                }
            }
        }
        return visited.size
    }

    fun neighbors(p: Point, fav: Int): List<Point> {
        return p.neighbors()
            .filter { it.first >= 0 && it.second >= 0 } // "negative values are invalid"
            .filter { !it.isWall(fav) }
    }

    fun print(fav: Int, x: Int, y: Int) {
        println("fav: $fav: $x, $y")
        for (row in 0..y) {
            for (col in 0..x) {
                val c = if (Point(col, row).isWall(fav)) '#' else '.'
                print(c)
            }
            println()
        }
    }
}

fun Point.isWall(fav: Int) = f(this.first, this.second, fav).toString(2).count { it == '1' }.isOdd()