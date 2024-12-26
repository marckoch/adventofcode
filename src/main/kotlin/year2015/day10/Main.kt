package year2015.day10

import adventofcode.Problem

fun main() {
    AOC2015D10("1").lookAndSay(5).also(::println)
    AOC2015D10("1321131112").run1()
    AOC2015D10("1321131112").run2()
}

val LINE_PATTERN = """(.*) to (.*) = (\d*)""".toRegex()

class AOC2015D10(input: String) : Problem(input) {
    override fun solve1() = lookAndSay(40)

    override fun solve2() = lookAndSay(50)

    fun lookAndSay(times: Int): Int {
        return generateSequence(input) { lookAndSay(it) }.elementAt(times).length
    }

    private fun lookAndSay(s: String): String {
        val result = StringBuilder()

        var last: Char = 0.toChar()
        var count = 0
        for (c in s.toCharArray()) {
            if (last == 0.toChar()) { // at the start init last with first character and set count to 1
                last = c
                count++
            } else if (c != last && last != 0.toChar()) { // new char, so finish up last batch
                result.append(count)
                result.append(last)
                last = c
                count = 1
            } else if (c == last) { // char does not change, so just inc count
                count++
            }
        }
        result.append(count)
        result.append(last)
    //    println("$s -> $result")
        return result.toString()
    }
}
