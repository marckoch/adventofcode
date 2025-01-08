package year2017.day04

import adventofcode.Problem

fun main() {
    AOC2017D04(INPUT).run()
}

class AOC2017D04(input: String) : Problem(input) {
    override fun solve1() : Int {
        return inputLines().count { line -> line.split(Regex("\\s+"))
            .sorted()
            .windowed(2, 1)
            .none { (word1, word2) -> word1 == word2 }
        }
    }

    override fun solve2() : Int {
        return inputLines().count { line -> line.split(Regex("\\s+"))
            .map { it.sortChars() }
            .sorted()
            .windowed(2, 1)
            .none { (word1, word2) -> word1 == word2 }
        }
    }

    private fun String.sortChars() : String = this.toCharArray().sorted().toString()
}
