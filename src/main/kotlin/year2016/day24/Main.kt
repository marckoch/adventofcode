package year2016.day24

import adventofcode.Problem
import util.graph.Labyrinth
import util.lists.cartesianProduct
import util.lists.permute
import java.util.LinkedList
import java.util.Queue

fun main() {
//    AOC2016D24(SAMPLE).run()
    AOC2016D24(INPUT).run()
    otherSolve(INPUT)
    doBFS(INPUT)
}

class AOC2016D24(input: String) : Problem(input) {
    override fun solve1() = solve()  // 498 [0, 1, 6, 4, 2, 3, 7, 5]
    override fun solve2() = solve(true) // 812 too high

    fun solve(closeRoute : Boolean = false) : Int {
        val nodes = Regex("\\d").findAll(input).map { it.value.first() }.toList().sorted()
//        nodes.also { println(it) }
        val distanceMatrix = nodes.cartesianProduct(nodes)
            .filter { (start, end) -> start < end }
            .map { (start, end) ->
                val lab = Labyrinth.fromStrings(inputLines(), start, end)
                val path = lab.dijkstraPath()
//                val path  = doBFS(input)
//                println("$start -> $end : ${path.size}")
                Triple(start.digitToInt(), end.digitToInt(), path.size - 1) // -1 because path contains start and end
            }
            // fold to adjacencyMatrix
            .fold(Array(nodes.size) { IntArray(nodes.size) }) { acc, (from, to, distance) ->
                acc[from][to] = distance
                acc[to][from] = distance
                acc
            }

        // permute all routes and calculate cost for each route, take route with minimal cost
        val allPaths =
            distanceMatrix.indices
            .toList().permute()
            .filter { it.first() == 0 } // route must start at '0'
            .map { path -> if (closeRoute) path.toMutableList().apply { add(0) } else path }
            .toList()

        distanceMatrix.forEach { println(it.contentToString()) }
        allPaths.minBy { totalDistanceOfPath(it, distanceMatrix) }.also { println(it) }
        return allPaths.minOf { totalDistanceOfPath(it, distanceMatrix) }
    }

    private fun totalDistanceOfPath(path: List<Int>, distanceMatrix: Array<IntArray> ): Int {
        return path.windowed(2, 1).sumOf { distanceMatrix[it[0]][it[1]] }
    }
}

// https://github.com/KoxAlen/AdventOfCode2016/tree/master
// this gives correct result, what is wrong with my code? :-/
fun otherSolve(input: String) {
    val map = input.lines().map(String::toCharArray).toTypedArray()

    val mapPoints = map.withIndex().flatMap { (x, l) -> l.withIndex().filter { it.value.isDigit() }.map { (y, v) -> Pair(v-'0', Pair(x, y)) } }
    val distances = calculateDistances(map, mapPoints)

    val locations =  mapPoints.map { it.first }
    val initial = locations.find { it == 0 }!!
    val toVisit = locations - initial

    var best = Int.MAX_VALUE
    for (permutation in toVisit.permute()) {
        best = best.coerceAtMost(otherSolve2(distances, listOf(initial)+permutation))
    }
    println("[Part 1] Distance: $best")

    best = Int.MAX_VALUE
    for (permutation in toVisit.permute()) {
        best = best.coerceAtMost(otherSolve2(distances, listOf(initial)+permutation+initial))
    }
    println("[Part 2] Distance: $best")
}

fun otherSolve2(distances: Array<IntArray>, path: List<Int>): Int {
    return path.drop(1).fold(Pair(path[0], 0)) {
            (from, dist), to ->
        Pair(to, dist+distances[from][to])
    }.second
}

// do a BFS from each location to each other end point and store the distance in a distanceMatrix
// map is unweighted graph so BFS will find the shortest path
fun calculateDistances(map: Array<CharArray>, locations: List<Pair<Int, Pair<Int, Int>>>): Array<IntArray> {
    val distances = Array(locations.size) { IntArray(locations.size) }
    locations.forEach {
            (ch, from) ->
        val unvisited = ArrayDeque<Pair<Int, Pair<Int,Int>>>()
        unvisited.add(Pair(0, from))

        val visited = mutableSetOf(from)
        while (unvisited.isNotEmpty()) {
            val (steps, current) = unvisited.removeFirst()

            // we have reached one destination
            map[current.first][current.second].let {
                if (it.isDigit()) {
                    distances[ch][it - '0'] = steps
                }
            }

            // find each valid neighbor and store it in queue (with distance increased by one step)
            listOf(Pair(1,0), Pair(-1,0), Pair(0,1), Pair(0,-1)) //Movements
                .map { (dx,dy) -> Pair(current.first+dx, current.second+dy) } //Possible positions
                .filter { (x,y) -> map[x][y] != '#' } //Not a wall
                .filter { it !in visited } //Prune repeated positions
                .forEach { unvisited.add(Pair(steps + 1, it)); visited.add(it) }
        }
    }
    distances.forEach { println(it.contentToString()) }
    return distances
}

data class Point(val x: Int, val y: Int)

fun doBFS(input: String) {
    val map = input.lines().map(String::toCharArray).toTypedArray()

    val mapPoints = map.withIndex().flatMap { (x, l) -> l.withIndex().filter { it.value.isDigit() }.map { (y, v) -> Pair(v-'0', Point(x, y)) } }
    val path = bfsShortestPath(map, mapPoints.first { it.first == 0}.second, mapPoints.first { it.first == 2 }.second)
    println(path?.size)
    println(path)

}

fun bfsShortestPath(grid: Array<CharArray>, start: Point, end: Point): List<Point>? {
    // Define directions for movement (up, down, left, right)
    val directions = arrayOf(
        Point(0, -1), // up
        Point(0, 1),  // down
        Point(-1, 0), // left
        Point(1, 0)   // right
    )

    fun Point.isInField() = this.x in grid.indices && this.y in grid[0].indices
    fun Point.isNotWall() = grid[this.x][this.y] != '#'

    // Create a queue for BFS
    val queue: Queue<Pair<Point, List<Point>>> = LinkedList()
    queue.add(Pair(start, listOf(start))) // Pair of current position and path to reach it

    // Create a visited set to avoid revisiting nodes
    val visited = mutableSetOf<Point>()
    visited.add(start)

    while (queue.isNotEmpty()) {
        val (current, path) = queue.poll()

        // If we've reached the destination, return the path
        if (current == end) {
            return path
        }

        // Explore all possible directions
        for (dir in directions) {
            val newX = current.x + dir.x
            val newY = current.y + dir.y
            val neighbor = Point(newX, newY)

            if (neighbor.isInField() && neighbor.isNotWall() && neighbor !in visited) {
                visited.add(neighbor)
                queue.add(Pair(neighbor, path + neighbor))
            }
        }
    }

    // Return null if no path is found
    return null
}