package year2020.day10

fun main() {
    part1(SAMPLE1)
    part1(SAMPLE2)
    part1(INPUT)

    part2(SAMPLE1)
    part2(SAMPLE2)
    part2(INPUT)
}

fun part1(input: String) {
    val numbers = input.lines().map { it.toInt() }

    // always add 0 and max to list
    val newNumbers = numbers + 0 + (numbers.max() + 3)

    newNumbers.sorted()
        .windowed(2)
        .map { it.last() - it.first() }
        .groupingBy { it }
        .eachCount().let { it[1]!! * it[3]!! }
        .let { println("part1: $it") }
}

fun part2(input: String) {
    val numbers = input.lines().map { it.toInt() }

    // always add 0 and max to list
    val newNumbers = (numbers + 0 + (numbers.max() + 3)).sorted()

    val countByNumber: MutableMap<Int, Long> = mutableMapOf(0 to 1L)

    newNumbers.drop(1).forEach { n ->
        countByNumber[n] =
            countByNumber.getOrDefault(n - 1, 0)+
            countByNumber.getOrDefault(n - 2, 0)+
            countByNumber.getOrDefault(n - 3, 0)
    }

    println("part2: ${countByNumber[newNumbers.last()]}")
}
