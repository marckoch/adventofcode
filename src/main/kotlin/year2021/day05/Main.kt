package year2021.day05

fun main() {
    solve(SAMPLE, ::expandHorizontalAndVerticalLines) // 5
    solve(INPUT, ::expandHorizontalAndVerticalLines)  // 4421

    solve(SAMPLE, ::expandAllLines)   // 12
    solve(INPUT, ::expandAllLines)    // 18674
}

val LINE_PATTERN = Regex("""(\d+),(\d+) -> (\d+),(\d+)""")

fun solve(input: String, expand: (Point, Point) -> List<Point>) {
    input.lines()
        .map { parse(it) }
        .map { expand(it.first, it.second) }
        .flatten()
        .groupingBy { it.toString() }
        .eachCount()
        .filter { entry -> entry.value > 1 }
        .count()
        .let { println(it) }
}

// we get start/end points of a line and create a list of all Points
// that lie on it
fun expandHorizontalAndVerticalLines(from: Point, to: Point): List<Point> {
    return if (isHorizontal(from, to)) {   // horizontal -> y is equal/constant
        val minX = minOf(from.x, to.x)
        val maxX = maxOf(from.x, to.x)
        (minX..maxX).map { x -> Point(x, from.y) }
    } else if (isVertical(from, to)) {     // vertical -> x is equal/constant
        val minY = minOf(from.y, to.y)
        val maxY = maxOf(from.y, to.y)
        (minY..maxY).map { y -> Point(from.x, y) }
    } else {
        emptyList()
    }
}

// we get start/end coords of a line and create a list
// of all points (Pair<int, Int>) that lie on it
fun expandAllLines(from: Point, to: Point): List<Point> {
    // first try horizontal and vertical lines
    val points = expandHorizontalAndVerticalLines(from, to)
    if (points.isNotEmpty())
        return points

    // now try diagonals

    return if (isFallingDiagonal(from, to)) {
        val (left, right) = orderLeftRight(from, to)
        var step = 0
        return (left.x..right.x).map { x -> Point(x, left.y + step++) }
    } else if (isRisingDiagonal(from, to)) {
        val (left, right) = orderLeftRight(from, to)
        var step = 0
        return (left.x..right.x).map { x -> Point(x, left.y - step++) }
    } else {
        emptyList()
    }
}

fun parse(line: String): Pair<Point, Point> {
    val coords = LINE_PATTERN
        .find(line)!!
        .groupValues
        .drop(1)  // first element is original string
        .map { it.toInt() }
    return Pair(Point(coords[0], coords[1]), Point(coords[2], coords[3]))
}

fun isHorizontal(from: Point, to: Point): Boolean {
    return from.y == to.y
}

fun isVertical(from: Point, to: Point): Boolean {
    return from.x == to.x
}

fun isFallingDiagonal(from: Point, to: Point): Boolean {
    val (left, right) = orderLeftRight(from, to)
    return (right.y - left.y == right.x - left.x)
}

fun isRisingDiagonal(from: Point, to: Point): Boolean {
    val (left, right) = orderLeftRight(from, to)
    return (right.y - left.y == - (right.x - left.x))
}

fun orderLeftRight(from: Point, to: Point): Pair<Point, Point> {
    return if (from.x < to.x)
        Pair(from, to)
    else
        Pair(to, from)
}

class Point(val x: Int, val y: Int) {
    override fun toString(): String {
        return "($x,$y)"
    }
}