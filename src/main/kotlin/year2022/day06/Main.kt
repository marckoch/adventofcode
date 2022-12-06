package year2022.day06

fun main() {
    findStarter(SAMPLE, 4)
    findStarter(INPUT, 4)

    findStarter(SAMPLE, 14)
    findStarter(INPUT, 14)
}
fun findStarter(input: String, length: Int) {
    input
        .windowed(length)
        .withIndex()
        .first { it.value.toSet().size == length }
        .let { println(it.index + length) }
}