package year2020.day05

fun main() {
    part1(SAMPLE)
    part1(INPUT)

    part2(INPUT)
}

fun part1(input: String) {
    input.lines()
        .map { decode(it) }
        .map { it.first * 8 + it.second }
        .max()
        .let { println("part1: $it") }
}

fun part2(input: String) {
    input.lines()
        .map { decode(it) }
        .map { it.first * 8 + it.second }.sorted()
        .windowed(2, 1)
        .filter { it[0] + 2 == it[1] }
        .map { it[0] + 1 }
        .let { println("part2: $it") }
}

fun decode(s: String): Pair<Int, Int> {
    val row = decodeRow(s)
    val col = decodeCol(s)
    return Pair(row, col)
}

fun decodeRow(s: String): Int {
    return s.substring(0, 7)
        .map { c ->
            when (c) {
                'F' -> '0'
                'B' -> '1'
                else -> throw IllegalArgumentException("computer says not: $c")
            }
        }.joinToString("").toInt(2)
}

fun decodeCol(s: String): Int {
    return s.substring(7)
        .map { c ->
            when (c) {
                'L' -> '0'
                'R' -> '1'
                else -> throw IllegalArgumentException("computer says not: $c")
            }
        }.joinToString("").toInt(2)
}