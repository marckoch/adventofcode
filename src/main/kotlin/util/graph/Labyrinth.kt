package util.graph

import util.point.Point
import util.point.neighbors
import java.util.*
import kotlin.collections.ArrayDeque

class Labyrinth(
    private val width: Int,
    private val height: Int,
    obstacles: List<Point>,
    private val start: Point = Point(0, 0),
    private val end: Point = Point(width - 1, height - 1)
) {
    private val fields = List(height) { CharArray(width) { '.' } }
    init {
        obstacles.forEach { obstacle -> fields[obstacle.first][obstacle.second] = '#' }
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

        val nextPoints = PriorityQueue<Pair<Point, Int>> { o1, o2 -> o1.second.compareTo(o2.second) }
        nextPoints.add(start to 0)

        val visited = mutableSetOf<Point>()
        while (nextPoints.isNotEmpty()) {
            // find unvisited point with minimal cost
            val (point, cost) = nextPoints.poll()
            if (visited.contains(point)) continue

            for (n in validNeighborsOf(point)) {
                val newCost = cost + n.first + n.second
                if (newCost < costs[n.first][n.second]) {
                    costs[n.first][n.second] = newCost
                    nextPoints.add(n to newCost)
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

    private fun validNeighborsOf(from: Point): List<Point> {
        return from.neighbors()
            .filter { isInsideMap(it) }
            .filter { isNotObstacle(it)}
    }

    private fun isInsideMap(p: Point): Boolean {
        return p.first in 0 until width && p.second in 0 until height
    }

    private fun isNotObstacle(p: Point): Boolean {
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