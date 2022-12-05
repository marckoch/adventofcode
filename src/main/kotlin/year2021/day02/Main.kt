package year2021.day02

fun main() {
    part1(SAMPLE)
    part1(INPUT)

    part2(SAMPLE)
    part2(INPUT)
}

fun part1(input: String) {
    solve(Part1.start, Part1::proceed, input)
}

fun part2(input: String) {
    solve(Part2.start, Part2::proceed, input)
}

object Part1 {
    val start = listOf(0, 0)

    fun proceed(acc: List<Int>, move: Move): List<Int> {
        val newHorizontal = acc[0] + move.first
        val newDepth = acc[1] + move.second

        return listOf(newHorizontal, newDepth)
    }
}

object Part2 {
    val start = listOf(0, 0, 0)

    fun proceed(acc: List<Int>, move: Move): List<Int> {
        val newHorizontal = acc[0] + move.first
        val newDepth = acc[1] + acc[2] * move.first
        val newAim = acc[2] + move.second

        return listOf(newHorizontal, newDepth, newAim)
    }
}

fun solve(start: List<Int>, proceed: (List<Int>, Move) -> List<Int>, input: String) {
    input.lines()
        .fold(start) { acc, s -> proceed(acc, parse(s)) }
        .let { println(it[0] * it[1]) }
}

// parse input line and return Pair of steps in (forward, up/down)
// up is negative because depth is decreasing
fun parse(line: String): Move {
    val (command, number) = line.split(" ")
    return when (command) {
        "forward" -> Pair(number.toInt(), 0)
        "up" -> Pair(0, -number.toInt())
        "down" -> Pair(0, number.toInt())
        else -> throw IllegalArgumentException("problem with $line")
    }
}

typealias Move = Pair<Int, Int>