package year2022.day15

import kotlin.math.abs

fun main() {
    part1(SAMPLE, 10)
    part1(INPUT, 2000000)

    part2(SAMPLE, 20)
    part2(INPUT, 4000000)

//     bruteForce(SAMPLE)
    // bruteForce(INPUT) too slow
}

val LINE_PATTERN = "Sensor at x=(-?\\d*), y=(-?\\d*): closest beacon is at x=(-?\\d*), y=(-?\\d*)".toRegex()

fun part1(input: String, row: Int) {
    val points = parse(input)

    val beacons = points.map { it.second }

    val minX = points.minOf { (s, b) -> s.first - dist(s, b) }
    val maxX = points.maxOf { (s, b) -> s.first + dist(s, b) }

    (minX..maxX).map { col ->
        Point(col, row)
    }.count { isCovered(it, points) && it !in beacons }
        .let { println("part1: $it") }
}

fun part2(input: String, limit: Int) {
    val points = parse(input)

    val beacons = points.map { it.second }

    val free = candidates(points)
        .filter { it.first in 0..limit && it.second in 0..limit }
        .find { !isCovered(it, points) && it !in beacons }!!
    val sol = free.first * 4_000_000L + free.second
    println("part2: free field is at $free, solution = $sol")
}

// for all sensors get all points that are one step further out than the beacon
fun candidates(points: List<Pair<Point, Point>>): Set<Point> {
    return points.flatMap { (s, b) ->
        val dist = dist(s, b) + 1
        candidates(s, dist)
    }.toSet()
}

// get all points around dist of given point
fun candidates(p: Point, dist: Int): Set<Point> {
    return (- dist..dist).flatMap { dx ->
        val dy = dist - abs(dx)
        setOf(Pair(p.first + dx, p.second + dy), Pair(p.first + dx, p.second - dy))
    }.toSet()
}

// for each sensor build a set of all points that are covered by it.
// looks nice in the sample but does not work for the real deal ;-)
fun bruteForce(input: String) {
    val points = parse(input)
    // points.forEach { println(it) }

    val sensors = points.map { it.first }
    val beacons = points.map { it.second }
    //print(sensors, beacons, emptySet())

    val covered = points.fold(mutableListOf<Point>()) { acc, (s, b) ->
        acc.addAll(coverage(s, b))
        acc
    }.toSet()

    print(sensors, beacons, covered)

    (covered - sensors.toSet() - beacons.toSet())
        .count { (_, y) -> y == 10 }
        .let { println(it) }
}

fun isCovered(p: Point, points: List<Pair<Point, Point>>): Boolean {
    return points.any { (s, b) -> isCovered(p, s, b) }
}

fun isCovered(p: Point, s: Point, b: Point): Boolean {
    return dist(p, s) <= dist(s, b)
}

fun parse(input: String): List<Pair<Point, Point>> {
    return input.lines().map { l ->
        val (sx, sy, bx, by) = LINE_PATTERN.find(l)!!.destructured
        Pair(Point(sx.toInt(), sy.toInt()), Point(bx.toInt(), by.toInt()))
    }
}

fun coverage(s: Point, b: Point): List<Point> {
    val dist = dist(s, b)
    println("$s -> $b dist=$dist")

    val points = (s.first - dist .. s.first + dist).flatMap { x ->
        (s.second - dist .. s.second + dist).map { y ->
            Point(x, y)
        }
    }.filter { dist(it, s) <= dist }

    return points
}

fun dist(a: Point, b: Point) = abs(a.first - b.first) + abs(a.second - b.second)

fun print(sensors: List<Point>, beacons: List<Point>, covered: Set<Point>) {
    val points = sensors + beacons + covered

    val minX = points.minOf { p -> p.first }
    val maxX = points.maxOf { p -> p.first }
    val minY = points.minOf { p -> p.second }
    val maxY = points.maxOf { p -> p.second }

    println("x: $minX..$maxX, y:$minY..$maxY")

    (minY..maxY).forEach { y ->
        print("$y ".padStart(3, ' '))

        (minX..maxY).forEach { x ->
            if (sensors.contains(Pair(x, y)))
                print("S")
            else if (beacons.contains(Pair(x, y)))
                print("B")
            else if (covered.contains(Pair(x, y)))
                print("#")
            else
                print(".")
        }
        println()
    }
}

typealias Point = Pair<Int, Int> // (row, col), e.g. (y, x)