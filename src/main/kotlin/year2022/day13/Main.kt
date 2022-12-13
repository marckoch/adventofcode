package year2022.day13

import kotlin.math.max

fun main() {
    part1(SAMPLE)
    part1(INPUT)

    part2(SAMPLE)
    part2(INPUT)
}

fun part1(input: String) {
    readLineChunks(input)
        .map { strings -> Pair(parse(strings.first()), parse(strings.last())) }
        .map { compare(it.first, it.second) }
        .withIndex()
        .filter { it.value < 0 }
        .sumOf { it.index + 1 }
        .let { println("part1: $it") }
}

fun part2(input: String) {
    val div2 = listOf(listOf(2))
    val div6 = listOf(listOf(6))
    val lines = readLineChunks(input).flatten().map{ s -> parse(s) }.toMutableList()
    lines.add(div2)
    lines.add(div6)

    val sorted = lines.sortedWith { l, r -> compare(l, r) }
    val indexDiv2 = sorted.indexOf(div2) + 1
    val indexDiv6 = sorted.indexOf(div6) + 1
    println("part2: " + indexDiv2 * indexDiv6)
}

fun parse(s: String): List<Any> {
    val lists = ArrayDeque<MutableList<Any>>()
    var accDigit = ""

    for (c in s) {
        if (c == '[') {
            lists.addLast(mutableListOf())
        } else if (c in '0'..'9') {
            accDigit += c
        } else if (c == ',') {
            // digit is finished
            if (accDigit.isNotEmpty()) {
                lists.last().add(Integer.parseInt(accDigit))
                accDigit = ""
            }
        } else if (c == ']') {
            // digit is finished
            if (accDigit.isNotEmpty()) {
                lists.last().add(Integer.parseInt(accDigit))
                accDigit = ""
            }

            val l = lists.removeLast()
            if (lists.isEmpty()) {
                return l
            } else
                lists.last().add(l)
        }
    }
    throw IllegalStateException("string is weird!")
}

fun compare(left: List<Any>, right: List<Any>): Int {
    //println("compare $left vs $right")

    val lastIndex = max(left.lastIndex, right.lastIndex)
    for (i in 0..lastIndex) {
        if (left.isEmpty() || i !in left.indices) { // empty or already exhausted
            return -1
        }
        if (right.isEmpty() || i !in right.indices) {  // empty or already exhausted
            return 1
        }
        val c = compare(left[i], right[i])
        if (c != 0) return c
    }

    return 0
}

fun compare(left: Any, right: Any): Int {
    //println(" compare $l vs $r")
    if (left is Int && right is Int) {
        return left.compareTo(right)
    } else if (left is Int && right is List<*>) {
        return compare(listOf(left), right)
    } else if (left is List<*> && right is Int) {
        return compare(left, listOf(right))
    } else {
        return compare((left as List<Any>), (right as List<Any>))
    }
}

fun readLineChunks(input: String): List<List<String>> {
    return input.lines().fold(mutableListOf<MutableList<String>>()) { list, line ->
        if (line.isEmpty()) {
            list.add(mutableListOf())
        } else {
            if (list.isEmpty()) {
                list.add(mutableListOf())
            }
            list.last().add(line.trim())
        }
        list
    }
}
