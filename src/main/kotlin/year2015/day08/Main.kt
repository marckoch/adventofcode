package year2015.day08

import adventofcode.Problem

fun main() {
    AOC2015D08(SAMPLE).run1()
    AOC2015D08(INPUT).run1()

    AOC2015D08(SAMPLE).run2()
    AOC2015D08(INPUT).run2()
}

const val ONE_HEX_CHAR = "[0-9a-fA-F]"
val HEX = """(\\x$ONE_HEX_CHAR$ONE_HEX_CHAR)""".toRegex()  // \x plus 2 hex chars
val DOUBLE_BACKSLASH = """(\\\\)""".toRegex()  // \\
val ESCAPED_DOUBLE_QUOTE = """(\\")""".toRegex()  // \"
val SINGLE_DOUBLE_QUOTE = """(")""".toRegex() // single "
val SINGLE_BACKSLASH = """(\\)""".toRegex() // single \

class AOC2015D08(input: String) : Problem(input) {
    override fun solve1(): Int {
        return inputLines()
            .map { line -> count(line) }
            .sumOf { pair -> pair.first - pair.second }
    }

    override fun solve2(): Any {
        return inputLines()
            .map { line -> blowUp(line) }
            .sumOf { pair -> pair.first - pair.second }
    }

    private fun count(s: String): Pair<Int, Int> {
        val newString = s
            .replace(HEX, "_") // hex
            .replace(DOUBLE_BACKSLASH, "_")
            .replace(ESCAPED_DOUBLE_QUOTE, "_")
            .replace(SINGLE_DOUBLE_QUOTE, "")
        return Pair(s.length, newString.length)
    }

    private fun blowUp(s: String): Pair<Int, Int> {
        val newString = s
            .replace(SINGLE_BACKSLASH, "__")
            .replace(SINGLE_DOUBLE_QUOTE, "__")
        return Pair(newString.length + 2, s.length) // + 2 for " in front and back
    }
}
