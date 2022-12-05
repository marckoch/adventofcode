package year2022.day04

fun main() {
    part1(SAMPLE)
    part1(INPUT)

    part2(SAMPLE)
    part2(INPUT)
}

fun part1(input: String) {
    input.lines()
        .count { oneAreaCoversOtherArea(it) }
        .let { println(it) }
}

fun part2(input: String) {
    input.lines()
        .count { hasOverlap(it) }
        .let { println(it) }
}

fun oneAreaCoversOtherArea(line: String): Boolean {
    val (start1, end1, start2, end2) = parse(line)

    val firstCoversSecond = (start1 <= start2 && end2 <= end1)
    val secondCoversFirst = (start2 <= start1 && end1 <= end2)

    return firstCoversSecond || secondCoversFirst
}

fun hasOverlap(line: String): Boolean {
    val (start1, end1, start2, end2) = parse(line)

    val firstBeforeSecond = end1 < start2   // without overlap
    val firstAfterSecond = end2 < start1    // without overlap

    return !(firstBeforeSecond || firstAfterSecond)
}

fun parse(s: String) = s.split(",", "-").map { it.toInt() }