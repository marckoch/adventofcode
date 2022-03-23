package year2015.day02

fun main() {
    sides
        .sumOf { calcArea(it[0], it[1], it[2]) }
        .let { println(it) }
}

fun calcArea(a: Int, b: Int, c: Int): Int {
    val minimum = minOf(a * b, b * c, c * a)
    return 2 * (a * b) + 2 * (b * c) + 2 * (c * a) + minimum
}