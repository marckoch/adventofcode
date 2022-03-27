package year2015.day12

fun main() {
    DIGIT.toRegex().findAll(INPUT)
        .filter { it.value.isNotEmpty() }
        .sumOf { it.value.toInt() }
        .let { println(it) }
}