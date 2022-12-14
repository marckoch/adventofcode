package year2022.day14

import kotlin.math.max
import kotlin.math.min

fun main() {
    part1(SAMPLE)
    part1(INPUT)
}

fun part1(input: String) {
    val walls = input.lines()
        .map { s -> s.split(" -> ").toList() }
        .map { strings -> buildWall(strings) }

    val allPoints = walls.flatten()

    var sand = emptyList<Point>()

    while (true) {
        val newSand = dropOnePiece(allPoints, sand)
        if (newSand != sand) {
            sand = newSand
        } else break
    }

    print(allPoints, sand)
    println("part1: " + sand.size)
}

val StopPoint = Point(Int.MAX_VALUE, Int.MAX_VALUE)
val FallOffPoint = Point(Int.MAX_VALUE, Int.MIN_VALUE)

fun dropOnePiece(allPoints: List<Point>, sand: List<Point>): List<Point> {
    var p: Point = Pair(500, 0)
    while (true) {
        val nextPos = nextStep(p, allPoints, sand)
        if (nextPos == FallOffPoint)
            return sand
        if (nextPos == StopPoint) {
            return sand + p
        } else {
            p = nextPos
        }
    }
}

fun nextStep(p: Point, allPoints: List<Point>, sand: List<Point>): Point {
    if (p.second > allPoints.maxOfOrNull { it.second }!!) // we fell off
        return FallOffPoint

    val down = Pair(p.first, p.second + 1)
    if (down !in allPoints && down !in sand) return down
    val left = Pair(p.first - 1, p.second + 1)
    if (left !in allPoints && left !in sand) return left
    val right = Pair(p.first + 1, p.second + 1)
    if (right !in allPoints && right !in sand) return right

    return StopPoint // this piece can not move any further
}

fun buildWall(points: List<String>): List<Point> {
    println(points)
    return points.map { s ->
        val (x, y) = s.split(",")
        Pair(x.toInt(), y.toInt())
    }
        .windowed(2, 1)
        .flatMap { pairs ->
            val from = pairs.first()
            val to = pairs.last()
            if (from.first == to.first) {
                val minSec = min(from.second, to.second)
                val maxSec = max(from.second, to.second)
                (minSec..maxSec).map { s -> Pair(from.first, s) }
            } else {
                val minFirst = min(from.first, to.first)
                val maxFirst = max(from.first, to.first)
                (minFirst..maxFirst).map { f -> Pair(f, from.second) }
            }
        }
        .toSet().toList()
}

fun print(allPoints: List<Point>, sand: List<Point>) {
    val maxRow = allPoints.maxOfOrNull { p -> p.second }!!
    val minCol = allPoints.minOfOrNull { p -> p.first }!!
    val maxCol = allPoints.maxOfOrNull { p -> p.first }!!
    println("$minCol..$maxCol 0..$maxRow")

    (0..maxRow).forEach {
        r -> (minCol..maxCol).forEach { c ->
            if (allPoints.contains(Pair(c, r)))
                print("#")
            else if (sand.contains(Pair(c, r)))
                print("O")
            else if (r == 0 && c == 500)
                print("+")
            else
                print(".")
        }
        println()
    }
}

typealias Point = Pair<Int, Int> // (row, col), e.g. (y, x)