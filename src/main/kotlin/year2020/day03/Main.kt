package year2020.day03

fun main() {
    part1(SAMPLE)
    part1(INPUT)

    part2(SAMPLE)
    part2(INPUT)
}

fun part1(input: String) {
    listOf(Pair(3, 1))
        .map { pair -> checkSlope(input, pair.first, pair.second) }
        .reduce { acc, i -> acc * i }
        .let { println("part1: $it") }
}

fun part2(input: String) {
    listOf(Pair(1, 1), Pair(3, 1), Pair(5, 1), Pair(7, 1), Pair(1, 2))
        .map { pair -> checkSlope(input, pair.first, pair.second) }
        .reduce { acc, i -> acc * i }
        .let { println("part2: $it") }
}

fun checkSlope(input: String, right: Int, down: Int): Int {
    return input.lines().withIndex().fold(0) { acc, s ->
        acc + if ((s.index % down == 0) && s.value[(s.index * right / down) % s.value.length] == '#') 1 else 0
    }
}