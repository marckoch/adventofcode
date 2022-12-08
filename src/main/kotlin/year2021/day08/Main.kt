package year2021.day08

fun main() {
    part1(SAMPLE)
    part1(INPUT)

    part2(SAMPLE)
    part2(INPUT)
}

val digits = mapOf(
    Pair("abcefg", 0),
    Pair("cf", 1),
    Pair("acdeg", 2),
    Pair("acdfg", 3),
    Pair("bcdf", 4),
    Pair("abdfg", 5),
    Pair("abdefg", 6),
    Pair("acf", 7),
    Pair("abcdefg", 8),
    Pair("abcdfg", 9)
)

fun part1(input: String) {
    input.lines()
        .map { s ->
            s.split("|")[1].trim()
        }.sumOf { countUnique(it) }
        .let { println("part1: $it") }
}

fun part2(input: String) {
    input.lines().sumOf { line ->
        decodeSingleNumber(line)
    }.let { println("part2: $it") }
}

fun decodeSingleNumber(line: String): Int {
    val (sig, d) = line.trim().split("|")

    val signalPatterns = sig.trim().split(" ").sortedBy { it.length }.map { sortChars(it) }
    val patterns = decodePatterns(signalPatterns)

    val digits = d.trim().split(" ").map { sortChars(it) }
    val decodedDigits = decodeDigits(patterns, digits)

    return Integer.valueOf(decodedDigits.joinToString(""))
}

fun decodeDigits(patterns: String, digits: List<String>): List<Int> {
    val numbers = digits.map { s ->
        when (s.length) {
            // for len 2,3,4,6 we don't even need decoding, because there is only one matching digit
            2 -> 1 // '1' is the only digit with 2 segments turned on
            3 -> 7 // '7' is the only digit with 3 segments turned on
            4 -> 4 // '4' is the only digit with 4 segments turned on
            7 -> 8 // '8' is the only digit with all 7 segments turned on
            in (5..6) -> decode(patterns, s)
            else -> throw IllegalArgumentException("digit string [$s] has illegal length")
        }
    }
    return numbers
}

fun decode(patterns: String, segmentsTurnedOn: String): Int {
    val decodedDigit = segmentsTurnedOn.map { c ->
        "abcdefg"[patterns.indexOf(c)]
    }.joinToString("")
        .let { sortChars(it) }
    return digits[decodedDigit]!!
}

fun countUnique(line: String): Int {
    return line.split(" ")
        .map { it.trim() }
        .count { it.length in listOf(2, 3, 4, 7) }
}

fun decodePatterns(p: List<String>): String {
    val segsFor1 = p[0].toSet()
    val segsFor7 = p[1].toSet()
    val segsFor4 = p[2].toSet()

    // top is in set for number 7 and not in set for number 1
    val top = segsFor7 - segsFor1

    // top, middle and bottom are common in all sets of 5 elements (numbers 2, 3, 5)
    val topMiddleBottom = p[3].toSet() intersect p[4].toSet() intersect p[5].toSet()

    val middleBottom = topMiddleBottom - top

    // middle is in both: 4 and in middleBottom
    val middle = segsFor4 intersect middleBottom

    val bottom = middleBottom - middle

    // topLeft is in set for 4 minus middle minus both of 1
    val topLeft = segsFor4 - middle - segsFor1

    // if we subtract segsFor4 and topMiddleBottom from the sets with 5 segs (numbers 2, 3, 5)
    // only one will have 1 seg left (number 2) which will be bottomLeft
    val bottomLeft = (3..5)
        .map { i -> p[i].toSet() - topMiddleBottom - segsFor4 }
        .first { it.isNotEmpty() }

    // if we subtract all known elements so far (top, middle, bottom, topLeft, bottomLeft) from all
    // sets of 6, only one will have one element left (number 6) with bottom right
    val bottomRight = (6..8)
        .map { i -> p[i].toSet() - topMiddleBottom - topLeft - bottomLeft }
        .first { it.size == 1 }

    val topRight = segsFor1 - bottomRight

    return listOf(top, topLeft, topRight, middle, bottomLeft, bottomRight, bottom).map { it.first() }.joinToString("")
}

fun sortChars(s: String) = s.toCharArray().toList().sorted().joinToString("")