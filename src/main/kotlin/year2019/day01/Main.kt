package year2019.day01

fun main() {
    part1(SAMPLE)
    part1(INPUT) // 3252897

    part2(SAMPLE)
    part2(INPUT) // 4876469
}

fun part1(input: String) {
    input.lines()
        .sumOf { fuel(it.toInt()) }
        .let { println(it) }
}

fun part2(input: String) {
    input.lines()
        .sumOf { totalFuel(it.toInt()) }
        .let { println(it) }
}

fun totalFuel(initialMass: Int): Int {
    return generateSequence(initialMass) { fuel(it) }
        .drop(1) // drop initial mass
        .takeWhile { it > 0 }
        .sum()
}

fun fuel(mass: Int): Int {
    val fuel = mass / 3 - 2
    return if (fuel > 0) fuel else 0
}