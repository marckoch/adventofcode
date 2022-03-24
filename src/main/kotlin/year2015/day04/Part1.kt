package year2015.day04

fun main() {
    generateSequence(0) { it + 1 }
        .firstOrNull { i -> md5(INPUT + i.toString()).startsWith("00000") }
        .let { print(it) }
}