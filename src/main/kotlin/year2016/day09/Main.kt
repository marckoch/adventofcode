package year2016.day09

import adventofcode.Problem

fun main() {
    AOC2016D09(INPUT).run()
}

class AOC2016D09(input: String) : Problem(input) {
    override fun solve1() = inputLines().first().let { calcLength(it, isPart2 = false) }
    override fun solve2() = inputLines().first().let { calcLength(it, isPart2 = true) }

    private fun calcLength(startString: String, isPart2: Boolean): Long {
        if (startString.isEmpty()) return 0L
        if (!startString.contains('(')) return startString.length.toLong()

        var s = startString

        var result = 0L
        while (s.contains('(')) {
            // find marker start
            val markerStart = s.indexOf('(')

            // handle everything left of marker,
            // it can not be expanded and can be added to length
            s = s.substring(markerStart)
            result += markerStart

            val markerEnd = s.indexOf(')')
            val marker = s.substring(0, markerEnd + 1)
            val (length, count) = marker.deconstruct()

            // drop marker from string beginning
            s = s.substring(markerEnd + 1)
            if (isPart2) {
                result += calcLength(s.substring(0, length), true) * count
            } else
                result += length * count

            // handle rest
            // no adding to result here, because the rest might need expansion,
            // will be checked/handled in next loop
            s = s.substring(length)
        }
        result += s.length.toLong()
        return result
    }

    // (<length>x<count>) -> length to count
    private fun String.deconstruct(): Pair<Int, Int> {
        return this
            .replace("(", "")
            .replace(")", "")
            .split("x")
            .map(String::toInt)
            .let { (length, count) -> length to count }
    }
}