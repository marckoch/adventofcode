package adventofcode

abstract class Problem(val input: String) {
    abstract fun solve1(): Any
    abstract fun solve2(): Any

    fun inputLines(): List<String> = input.lines()

    fun run() {
        solve1().also { println(it) }
        solve2().also { println(it) }
    }

    fun run1() {
        solve1().also { println(it) }
    }

    fun run2() {
        solve2().also { println(it) }
    }
}