package year2022.day12

import java.util.*
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

@OptIn(ExperimentalTime::class)
fun main() {
    measureTime {  part1(SAMPLE) }.let { println(it) }
    measureTime {  part1(INPUT) }.let { println(it) }

    measureTime {  part2(SAMPLE) }.let { println(it) }
    measureTime {  part2(INPUT) }.let { println(it) }
}

fun part1(input: String) {
    val map = input.lines()

    val start = find(map, 'S')
    val end = find(map, 'E')

    fun validNeighborElevations(c: Char) = 'a'..c + 1
    val costMap = dijkstra(map, start, ::validNeighborElevations)
    println("part1: ${costMap[end]}")
}

fun part2(input: String) {
    // we replace the 'S' with a 'a' to make things easier
    val map = input.lines().map { row -> row.map { c -> if (c == 'S') 'a' else c }.joinToString("") }

    val end = find(map, 'E')

    // basic idea: we go backwards from 'E' and filter all 'a' fields and find minimum

    // because we go backwards (climb down) we have to adjust the filer for valid neighbors
    fun validNeighborElevations(c: Char) = c - 1..'z'

    val costMap = dijkstra(map, end, ::validNeighborElevations)
    costMap.filter { entry ->
        val p = entry.key
        map[p.first][p.second] == 'a'
    }.minBy { it.value }
        .let {
            println("part2: ${it.value}")
        }
}

// https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
// https://www.atomiccommits.io/dijkstras-algorithm-in-kotlin
fun dijkstra(map: List<String>, start: Point, validNeighborElevations: (Char) -> CharRange): MutableMap<Point, Int> {
    val costMap = allPoints(map).associateWith { Int.MAX_VALUE }.toMutableMap()
    costMap[start] = 0

    val nextPoints = PriorityQueue<Pair<Point, Int>> { o1, o2 -> o1.second.compareTo(o2.second) }
    nextPoints.add(Pair(start, 0))

    val path = emptyMap<Point, Point>().toMutableMap()

    val visited = mutableSetOf<Point>()
    while (nextPoints.isNotEmpty()) {
        // find point with minimal cost
        val (point, cost) = nextPoints.poll()
        if (visited.contains(point)) continue

        for (n in findNeighbors(map, point, validNeighborElevations)) {
            val newCost = cost + 1 // cost to next field is just 1 (1 step)
            if (newCost < costMap[n]!!) {
                costMap[n] = newCost
                path[n] = point
            }
            nextPoints.add(Pair(n, costMap[n]!!))
        }

        visited.add(point)
    }
//    println(path)
    return costMap
}

fun allPoints(map: List<String>): List<Point> =
    (0..map.lastIndex).flatMap { row ->
        (0..map[0].lastIndex).map { col ->
            Point(row, col)
        }
    }

fun find(map: List<String>, c: Char): Point {
    val targetRow = map.withIndex().first { row ->
        row.value.contains(c)
    }
    val col = targetRow.value.indexOfFirst { it == c }
    return Pair(targetRow.index, col)
}

fun findNeighbors(map: List<String>, p: Point, validNeighborElevations: (Char) -> CharRange): List<Point> {
    val north = Pair(p.first - 1, p.second)
    val south = Pair(p.first + 1, p.second)
    val west = Pair(p.first, p.second - 1)
    val east = Pair(p.first, p.second + 1)

    val currentElevation = elevation(map, p)

    return listOf(north, south, west, east)
        .filter { isInside(map, it) }
        // part1: 'a' .. currentElevation + 1
        // part2: currentElevation - 1 .. 'z'
        .filter { elevation(map, it) in validNeighborElevations(currentElevation) }
}

fun isInside(map: List<String>, p: Point): Boolean {
    val validRows = map.indices
    val validCols = map[0].indices
    return p.first in validRows && p.second in validCols
}

fun elevation(map: List<String>, p: Point): Char {
    return when (val c = map[p.first][p.second]) {
        'S' -> 'a'
        'E' -> 'z'
        else -> c
    }
}

typealias Point = Pair<Int, Int> // (row, col), e.g. (y, x)
