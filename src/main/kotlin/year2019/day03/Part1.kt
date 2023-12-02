package year2019.day03

import kotlin.math.absoluteValue

fun main() {
    findClosestIntersection(SAMPLE1) // 6
    findClosestIntersection(SAMPLE2) // 159
    findClosestIntersection(SAMPLE3) // 135
    findClosestIntersection(INPUT)   // 855
}

fun findClosestIntersection(input: String) {
    val instructionsWire1 = input.lines().first().split(",")
    val instructionsWire2 = input.lines().last().split(",")

    val pointsWire1 = getPoints(instructionsWire1)
    val pointsWire2 = getPoints(instructionsWire2)

    // find all points where wire1 and wire2 intersect
    val intersections = pointsWire1.intersect(pointsWire2)

    val distances = intersections.map { manhattanDistance(it) }
    distances.min().let { println(it) }
}

// manhattan distance to the origin 0/0
fun manhattanDistance(point: Point): Int {
    return point.first.absoluteValue + point.second.absoluteValue
}

// take the list of instructions (e.g. "R13") and generate a Set of all Points covered by this wire,
// we are starting from the origin 0/0
fun getPoints(instructions: List<String>): Set<Point> {
    var x = 0
    var y = 0

    val points = mutableSetOf<Point>()

    instructions.forEach {
        val direction = it.first()
        val distance = it.substring(1).toInt()
        when (direction) {
            'R' -> {
                for (i in 1..distance) {
                    x++
                    points.add(Point(x, y))
                }
            }
            'L' -> {
                for (i in 1..distance) {
                    x--
                    points.add(Point(x, y))
                }
            }
            'U' -> {
                for (i in 1..distance) {
                    y++
                    points.add(Point(x, y))
                }
            }
            'D' -> {
                for (i in 1..distance) {
                    y--
                    points.add(Point(x, y))
                }
            }
        }
    }
    return points
}

typealias Point = Pair<Int, Int>