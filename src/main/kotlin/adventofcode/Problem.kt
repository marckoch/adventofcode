package adventofcode

abstract class Problem(val rawInput: String) {
    abstract fun solve1(): String
    abstract fun solve2(): String

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