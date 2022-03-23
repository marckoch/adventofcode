package year2015.day02

fun main() {
    sides
        .sumOf { calcRibbon(it[0], it[1], it[2]) }
        .let { println(it) }
}

fun calcRibbon(a: Int, b: Int, c: Int): Int {
    val minimum = minOf(a + b, b + c, c + a)
    return 2 * minimum + a * b * c
}