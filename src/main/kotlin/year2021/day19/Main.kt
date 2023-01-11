package year2021.day19

import kotlin.math.abs
import kotlin.math.sqrt

fun main() {
    part1(SAMPLE)
//    part1(INPUT)
}

fun part1(input: String) {
    readLineGroups(input).let { g -> g.forEach { println(it) } }

    val scanners = readLineGroups(input).map { readPointsFromScanner(it) }
    scanners.forEach { println(it) }

    val diffs = scanners.map { getDiffsSquared(it) }
    diffs.forEach { println(it.sorted()) }

    diffs.indices.map { s1 ->
        diffs.indices.map { s2 ->
            if (s1 < s2) {
                val seenByBoth = diffs[s1] intersect diffs[s2]
                if (seenByBoth.isNotEmpty()) {
                    val beacons = getBeacons(seenByBoth.size)
                    println("scanner $s1 and $s2 see ${seenByBoth.size} differences that corresponds to $beacons beacons")
                }
            }
        }
    }
}

// n beacons have n * (n-1) / 2 differences between each other
// here we calculate n given the number of seen differences
fun getBeacons(diffs: Int): Int {
    val d2 = sqrt(2 * diffs + 0.25)
    return (d2 + 0.5).toInt() // -0.5 is also valid and would correspond to (n-1) above, but we want n
}

data class Point(val x: Int, val y: Int, val z: Int) {
    fun diffSquaredTo(other: Point): Long {
        return abs(x.toLong() - other.x) * abs(x - other.x) +
                abs(y - other.y) * abs(y - other.y) +
                abs(z - other.z) * abs(z - other.z)
    }
}

// we calculate the difference between every two different beacons
// to avoid rounding issues we use the diffSquared
fun getDiffsSquared(points: Set<Point>): Set<Long> {
    return points.flatMap { p1 ->
        points.mapNotNull { p2 ->
            if (p1 != p2)
                p1.diffSquaredTo(p2)
            else
                null
        }
    }.toSet()
}

fun readPointsFromScanner(lines: List<String>): Set<Point> {
    return lines.drop(1)
        .map { line ->
            val (x, y, z) = line.split(",").map { it.toInt() }
            Point(x, y, z)
        }.toSet()
}

fun readLineGroups(input: String): List<MutableList<String>> {
    return input.lines().fold(mutableListOf()) { acc, line ->
        if (line.isEmpty()) {
            acc.add(mutableListOf())
        } else {
            if (acc.isEmpty()) {
                acc.add(mutableListOf())
            }
            acc.last().add(line)
        }
        acc
    }
}