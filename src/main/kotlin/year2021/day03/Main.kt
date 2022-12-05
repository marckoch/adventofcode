package year2021.day03

fun main() {
    part1(List(5) { 0 }, SAMPLE)
    part1(List(12) { 0 }, INPUT)

    part2(SAMPLE)
    part2(INPUT)
}

// for each column we count how many 1 or zero are there,
// we start with 0, a 1 will inc the count by 1, a 0 will dec by one
// if at the end the number is positive we know we had more 1s than 0s
// if at the end the number is negative we know we had more 0s than 1s
fun part1(start: List<Int>, input: String) {
    val freq = input.lines()
        .fold(start) { acc, s ->
            acc.zip(parse(s)) { x, y ->
                if (y == 1) x + 1 else x - 1
            }
        }

    val gamma = decode(freq) { if (it > 0) '1' else '0' }

    val epsilon = decode(freq) { if (it > 0) '0' else '1' }

    println("${gamma * epsilon}")
}


fun part2(input: String) {
    val oxyBinary = find(input.lines(), ::findMostCommonCharInPosition)
    val co2Binary = find(input.lines(), ::findLeastCommonCharInPosition)

    val oxy = Integer.parseInt(oxyBinary, 2)
    val co2 = Integer.parseInt(co2Binary, 2)

    println("${oxy * co2}")
}

fun find(lines: List<String>, findCharInPos: (List<String>, Int) -> Char): String {
    var pos = 0
    var list = lines

    do {
        val c = findCharInPos(list, pos)
        list = list.filter { it[pos] == c }
        pos += 1
    } while (list.size > 1)

    return list.first()
}

fun findMostCommonCharInPosition(lines: List<String>, pos: Int): Char {
    val freq = charFreqAtPosition(lines, pos)

    // tiebreaker: if 0 and 1 are equally frequent we take '1'
    if (freq.values.toSet().size == 1)
        return '1'

    return freq.maxBy { it.value }.key
}

fun findLeastCommonCharInPosition(lines: List<String>, pos: Int): Char {
    val freq = charFreqAtPosition(lines, pos)

    // tiebreaker: if 0 and 1 are equally frequent we take '0'
    if (freq.values.toSet().size == 1)
        return '0'

    return freq.minBy { it.value }.key
}

fun charFreqAtPosition(lines: List<String>, pos: Int): Map<Char, Int> {
    return lines
        .map { s -> s[pos] }
        .groupingBy { it }
        .eachCount()
}

fun decode(freq: List<Int>, toChar: (Int) -> Char): Int {
    return freq
        .map { toChar(it) }
        .joinToString("")
        .let { Integer.parseInt(it, 2) }
}

fun parse(line: String): List<Int> {
    return line.toCharArray().toList().map { it.digitToInt() }
}