package year2021.day01

fun main() {
    part1(SAMPLE)
    part1(INPUT)

    part2(SAMPLE)
    part2(INPUT)
}

fun part1(input: String) {
    input.lines()
        .map { s -> s.toInt() }
        .windowed(2)
        .count { ints -> ints[1] > ints[0] }
        .let { println(it) }
}

fun part2(input: String) {
    input.lines()
        .map { s -> s.toInt() }
        .windowed(3)
        .map { ints -> ints.sum() }
        .windowed(2)
        .count { ints -> ints[1] > ints[0] }
        .let { println(it) }
}