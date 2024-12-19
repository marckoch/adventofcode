package util.point

typealias Point = Pair<Int, Int> // (row, col), e.g. (y, x)

fun Point.neighbors(): List<Point> {
    val north = this.copy(first = first - 1)
    val south = this.copy(first = first + 1)
    val west = this.copy(second = second - 1)
    val east = this.copy(second = second + 1)
    return listOf(north, south, west, east)
}