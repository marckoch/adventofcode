package year2015.day05

import adventofcode.Problem
import util.strings.numberOfVowels

fun main() {
    AOC2015D05(INPUT).run()
}

class AOC2015D05(input: String) : Problem(input) {
    override fun solve1(): Int {
        return inputLines().count { isNice(it) }
    }

    override fun solve2(): Int {
        return inputLines().count { isBetterNice(it) }
    }

}

fun isNice(s: String): Boolean {
    if (s.numberOfVowels() < 3)
        return false

    val indexOfFirstDuplicate = s.windowed(2, 1).indexOfFirst { it[0] == it[1] }
    if (indexOfFirstDuplicate < 0)
        return false

    if ("ab" in s || "cd" in s || "pq" in s || "xy" in s)
        return false

    return true
}

fun isBetterNice(s: String): Boolean {
    s.windowed(2, 1)
        .withIndex()
        .firstOrNull { (index, twoLetters) -> twoLetters in s.substring(index + 2) }
        ?: return false

    val indexOfRepeatingLetter = s.windowed(3,1).indexOfFirst { it[0] == it[2] }
    if (indexOfRepeatingLetter < 0)
        return false

    return true
}