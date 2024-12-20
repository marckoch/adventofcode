package util.point

import util.graph.Direction
import kotlin.math.abs

typealias Point = Pair<Int, Int> // (row, col), e.g. (y, x)

fun Point.neighbors(): List<Point> {
    val north = this.copy(first = first - 1)
    val south = this.copy(first = first + 1)
    val west = this.copy(second = second - 1)
    val east = this.copy(second = second + 1)
    return listOf(north, south, west, east)
}

fun Point.dirTo(to: Point): Direction {
    return when (val diff = to.first - this.first to to.second - this.second) {
        -1 to 0 -> Direction.NORTH
        1 to 0 -> Direction.SOUTH
        0 to -1 -> Direction.WEST
        0 to 1 -> Direction.EAST
        else -> throw IllegalArgumentException("Unknown direction: $diff based on $this to $to")
    }
}

fun Point.manhattanDistanceTo(other: Point): Int {
    return abs(this.first - other.first) + abs(this.second - other.second)
}
