package year2020.day06

fun main() {
    part1(SAMPLE)
    part1(INPUT)

    part2(SAMPLE)
    part2(INPUT)
}

fun part1(input: String) {
    readLineGroups(input)
        .sumOf { s -> s.map { it.toSet() }.reduce { acc, chars -> acc union chars }.count() }
        .let { println("part1: $it") }
}

fun part2(input: String) {
    readLineGroups(input)
        .sumOf { s -> s.map { it.toSet() }.reduce { acc, chars -> acc intersect  chars }.count() }
        .let { println("part2: $it") }
}

fun readLineGroups(input: String): List<MutableList<String>> {
    return input.lines().fold(mutableListOf()) { acc, line ->
        if (line.isEmpty()) {
            acc.add(mutableListOf())
        } else {
            if (acc.isEmpty()) {
                acc.add(mutableListOf())
            }
            acc.last().add(line)
        }
        acc
    }
}