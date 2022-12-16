package year2021.day15

import java.util.PriorityQueue
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

@OptIn(ExperimentalTime::class)
fun main() {
    measureTime { part1(SAMPLE) }.let { println(it) }
    measureTime { part1(INPUT) }.let { println(it) }

    measureTime { part2(SAMPLE) }.let { println(it) }
    measureTime { part2(INPUT) }.let { println(it) }
}

fun part1(input: String) {
    val cost = input.lines().map { s -> s.map { c -> c.digitToInt() } }
//    cost.forEach { println(it) }

    val start = Point(0, 0)
    val end = Point(cost.lastIndex, cost.last().lastIndex)

    val dijkstra = dijkstra(cost, start)
//    dijkstra.forEach { println(it) }
    println(dijkstra[end.first][end.second])
}

fun part2(input: String) {
    val cost = input.lines().map { s -> s.map { c -> c.digitToInt() } }
//    cost.forEach { println(it) }

    val bigMap = expand(cost, 5)
//    bigMap.forEach { println(it) }

    val start = Point(0, 0)
    val end = Point(bigMap.lastIndex, bigMap.last().lastIndex)

    val dijkstra = dijkstra(bigMap, start)
//    dijkstra.forEach { println(it) }
    println(dijkstra[end.first][end.second])
}

fun expand(map: List<List<Int>>, factor: Int): List<List<Int>> {
    val maxR = map.size
    val maxC = map[0].size

    val bigMap = List(maxR * factor) { MutableList(maxC * factor) { Int.MAX_VALUE } }

    for (r in 0 until maxR * factor) {
        for (c in 0 until maxC * factor) {
            val newVal = (map[r % maxR][c % maxC] + (r / maxR) + (c / maxC))
            if (newVal > 9)
                bigMap[r][c] = newVal - 9
            else
                bigMap[r][c] = newVal
        }
    }
    return bigMap
}


// https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
// https://www.atomiccommits.io/dijkstras-algorithm-in-kotlin
fun dijkstra(map: List<List<Int>>, start: Point): List<MutableList<Int>> {
    val maxR = map.size
    val maxC = map[0].size

    val costMap = List(maxR) { MutableList(maxC) { Int.MAX_VALUE } }
    costMap[start.first][start.second] = 0

    val nextPoints = PriorityQueue<Pair<Point, Int>> { o1, o2 -> o1.second.compareTo(o2.second) }
    nextPoints.add(Pair(start, 0))

    val visited = mutableSetOf<Point>()
    while (nextPoints.isNotEmpty()) {
        // find unvisited point with minimal cost
        val (point, cost) = nextPoints.poll()
        if (visited.contains(point)) continue

        for (n in neighbors(point.first, point.second, maxR, maxC)) {
            val newCost = cost + map[n.first][n.second]
            if (newCost < costMap[n.first][n.second]) {
                costMap[n.first][n.second] = newCost
            }
            nextPoints.add(Pair(n, costMap[n.first][n.second]))
        }

        visited.add(point)
    }
    return costMap
}

fun neighbors(row: Int, col: Int, rMax: Int, cMax: Int): List<Point> {
    return listOf(
        Point(row - 1, col),
        Point(row + 1, col),
        Point(row, col - 1),
        Point(row, col + 1)
    ).filter { it.first in 0 until rMax &&
               it.second in 0 until cMax }
}

typealias Point = Pair<Int, Int> // (row, col), e.g. (y, x)