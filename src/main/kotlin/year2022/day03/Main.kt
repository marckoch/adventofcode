package year2022.day03

fun main() {
    part1()

    part2()
}

fun part1() {
    INPUT.lines()
        .map { it.chunked(it.length / 2) }
        .map { findCommonCharIn(it) }
        .sumOf { itemPriorityOf(it) }
        .let { println(it) }
}

fun part2() {
    INPUT.lines()
        .chunked(3)
        .map { findCommonCharIn(it) }
        .sumOf { itemPriorityOf(it) }
        .let { println(it) }
}

fun findCommonCharIn(strings: List<String>): Char {
    return strings
        .map { charsOf(it) }
        .reduce { acc, i -> (acc intersect i).toList() }
        .first()
}

fun itemPriorityOf(c: Char): Int {
    when (c) {
        in 'a'..'z' -> return c.code - 97 + 1   // 'a' == 97 in ASCII
        in 'A'..'Z' -> return c.code - 65 + 27  // 'A' == 65 in ASCII
    }
    throw IllegalArgumentException("$c is invalid argument")
}

// convert weird IntStream that 'chars()' returns to nice List<Char>
fun charsOf(s: String): List<Char> {
    return s.chars()
        .boxed()
        .map { it.toChar() }
        .toList()
}