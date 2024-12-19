package util.graph

import util.point.Point
import util.point.neighbors
import java.util.*
import java.util.function.BiFunction
import kotlin.collections.ArrayDeque

enum class Direction {
    NORTH, WEST, SOUTH, EAST;

    fun turnLeft(): Direction {
        return when (this) {
            NORTH -> WEST
            WEST -> SOUTH
            SOUTH -> EAST
            EAST -> NORTH
        }
    }

    fun turnRight(): Direction {
        return when (this) {
            NORTH -> EAST
            EAST -> SOUTH
            SOUTH -> WEST
            WEST -> NORTH
        }
    }
}

class Labyrinth(private val fields: List<CharArray>,
                val start: Point = Point(0, 0),
                val end: Point = Point(fields.first().size - 1,  fields.size - 1)) {
    val height: Int = fields.size
    val width: Int = fields.first().size

    companion object {
        fun fromObstacleList(width: Int, height: Int, obstacles: List<Point>): Labyrinth {
            val fields = List(height) { CharArray(width) { '.' } }
            obstacles.forEach { obstacle -> fields[obstacle.first][obstacle.second] = '#' }
            return Labyrinth(fields)
        }

        /**
         * expect a list of strings, 'S' indicates start, 'E' indicates end point
         * #######
         * #.#..E#
         * #S..#.#
         * #######
         */
        fun fromStrings(strings: List<String>): Labyrinth {
            val height: Int = strings.size
            val width: Int = strings.first().length
            var start: Point = Point(0, 0)
            var end: Point = Point(width - 1, height - 1)
            val fields = List(height) { CharArray(width) { '.' } }

            strings.withIndex().forEach { (row, line) ->
                line.withIndex().forEach { (column, char) ->
                    fields[row][column] = char
                    if (char == 'S') {
                        start = Point(row, column)
                    } else if (char == 'E') {
                        end = Point(row, column)
                    }
                }
            }
            return Labyrinth(fields, start, end)
        }
    }

    fun dfs(): List<List<Point>> {
        val paths = mutableListOf<List<Point>>()
        val visited = mutableListOf<Point>()
        dfs(start, end, ArrayDeque(), paths, visited)
        return paths
    }

    // https://www.baeldung.com/cs/simple-paths-between-two-vertices
    private fun dfs(
        from: Point, to: Point,
        currentPath: ArrayDeque<Point>,
        paths: MutableList<List<Point>>,
        visited: MutableList<Point>
    ) {
        if (visited.contains(from)) {
            return
        }

        visited.add(from)
        currentPath.addLast(from)

        if (from == to) {
            paths.add(currentPath.toMutableList()) // make copy by calling toMutableList
            visited.remove(from)
            currentPath.removeLast()
            return
        }

        for (next in validNeighborsOf(from)) {
            dfs(next, to, currentPath, paths, visited)
        }

        visited.remove(from)
        currentPath.removeLast()
    }

    fun bfs(): List<List<Point>> {
        val paths = mutableListOf<List<Point>>()
        val visited = mutableListOf<Point>()
        bfs(start, end, ArrayDeque(), paths, visited)
        return paths
    }

    private fun bfs(
        from: Point, to: Point,
        currentPath: ArrayDeque<Point>,
        paths: MutableList<List<Point>>,
        visited: MutableList<Point>
    ) {
        if (visited.contains(from)) {
            return
        }

        visited.add(from)
        currentPath.addLast(from)

        if (from == to) {
            paths.add(currentPath.toMutableList()) // make copy by calling toMutableList
            visited.remove(from)
            currentPath.removeLast()
            return
        }

        val q = ArrayDeque<Point>()
        q.addAll(validNeighborsOf(from))

        while (q.isNotEmpty()) {
            val next = q.removeFirst()
            bfs(next, to, currentPath, paths, visited)
        }

        visited.remove(from)
        currentPath.removeLast()
    }

    // https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
    // https://www.atomiccommits.io/dijkstras-algorithm-in-kotlin
    fun dijkstraCosts(): List<List<Int>> {
        val costs = List(height) { MutableList(width) { Int.MAX_VALUE } }
        costs[start.first][start.second] = 0

        val unvisited = PriorityQueue<Pair<Point, Int>> { o1, o2 -> o1.second.compareTo(o2.second) }
        unvisited.add(start to 0)

        val visited = mutableSetOf<Point>()
        while (unvisited.isNotEmpty()) {
            // find unvisited point with minimal cost
            val (point, cost) = unvisited.poll()
            if (visited.contains(point)) continue

            for (n in validNeighborsOf(point)) {
                val newCost = cost + n.first + n.second
                if (newCost < costs[n.first][n.second]) {
                    costs[n.first][n.second] = newCost
                    unvisited.add(n to newCost)
                }
            }

            visited.add(point)
        }
        return costs
    }

    fun dijkstraCosts(costFn: BiFunction<Point, Point, Int>): List<List<Int>> {
        val costs = List(height) { MutableList(width) { Int.MAX_VALUE } }
        costs[start.first][start.second] = 0

        val unvisited = PriorityQueue<Pair<Point, Int>> { o1, o2 -> o1.second.compareTo(o2.second) }
        unvisited.add(start to 0)

        val visited = mutableSetOf<Point>()
        while (unvisited.isNotEmpty()) {
            // find unvisited point with minimal cost
            val (point, cost) = unvisited.poll()
            if (visited.contains(point)) continue

            for (n in validNeighborsOf(point)) {
                val newCost = cost + costFn.apply(point, n)
                if (newCost < costs[n.first][n.second]) {
                    costs[n.first][n.second] = newCost
                    unvisited.add(n to newCost)
                }
            }

            visited.add(point)
        }
        return costs
    }

    fun dijkstraPath(): List<Point> {
        return reconstructPath(dijkstraCosts())
    }

    // from the end point follow the points of minimal costs back to start,
    // reverse that list and return
    private fun reconstructPath(costs: List<List<Int>>): List<Point> {
        val path = mutableListOf<Point>()

        var currentPoint = end
        while (currentPoint != start) {
            path.add(currentPoint)
            currentPoint = validNeighborsOf(currentPoint).minBy {
                costs[it.first][it.second]
            }
        }
        path.add(start)
        path.reverse()

//        print(path)

        return path
    }

    fun validNeighborsOf(from: Point): List<Point> {
        return from.neighbors()
            .filter { isInsideMap(it) }
            .filter { isNotObstacle(it)}
    }

    fun isInsideMap(p: Point): Boolean {
        return p.first in 0 until width && p.second in 0 until height
    }

    fun isNotObstacle(p: Point): Boolean {
        return fields[p.first][p.second] != '#'
    }

    fun print(path: List<Point> = emptyList()) {
        for (w in 0 until width) {
            for (h in 0 until height) {
                if (w to h in path) {
                    print('O')
                } else
                    print(fields[w][h])
            }
            println()
        }
    }
}