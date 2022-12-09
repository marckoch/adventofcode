package year2021.day10

fun main() {
    part1(SAMPLE)
    part1(INPUT)

    part2(SAMPLE)
    part2(INPUT)
}

fun part1(input: String) {
    input.lines()
        .mapNotNull { findFirstIllegalChar(it) }
        .sumOf { score(it) }
        .let { println("part1: $it") }
}

fun part2(input: String) {
    val scores = input.lines()
        .filter { findFirstIllegalChar(it) == null }
        .map { complete(it) }
        .map { scorePart2(it) }
        .sorted()

    println("part2: ${scores[scores.size / 2]}")
}

fun complete(s: String): List<Char> {
    val stack = ArrayDeque<Char>()
    val completion = mutableListOf<Char>()

    for (c in s.toCharArray().reversed()) {
        when (c) {
            in listOf(')', ']', '}', '>') -> stack.addLast(c)
            '(' -> if (stack.isEmpty()) completion.add(')') else stack.removeLast()
            '[' -> if (stack.isEmpty()) completion.add(']') else stack.removeLast()
            '{' -> if (stack.isEmpty()) completion.add('}') else stack.removeLast()
            '<' -> if (stack.isEmpty()) completion.add('>') else stack.removeLast()
        }
    }
    return completion
}

fun findFirstIllegalChar(s: String): Char? {
    val stack = ArrayDeque<Char>()

    for (c in s.toCharArray()) {
        when (c) {
            in listOf('(', '[', '{', '<') -> stack.addLast(c)
            in listOf(')', ']', '}', '>') -> {
                val last = stack.last()

                // we have a match
                if ((c == ')' && last == '(') ||
                    (c == ']' && last == '[') ||
                    (c == '}' && last == '{') ||
                    (c == '>' && last == '<')) {
                    stack.removeLast()
                } else {
                    // we found an illegal char
                    return c
                }
            }
        }
    }
    // all cool, no illegal chars found
    return null
}

fun score(c: Char): Int {
    return when (c) {
        ')' -> 3
        ']' -> 57
        '}' -> 1197
        '>' -> 25137
        else -> 0
    }
}

fun scorePart2(chars: List<Char>): Long {
    return chars.fold(0L) {
        acc, c -> acc * 5 + value(c)
    }
}

fun value(c: Char): Int {
    return when (c) {
        ')' -> 1
        ']' -> 2
        '}' -> 3
        '>' -> 4
        else -> 0
    }
}