package year2015.day11

import adventofcode.Problem

fun main() {
    AOC2015D11("").findNextPassword("abcdefgh").also(::println)
    AOC2015D11("hepxcrrq").run1()
    AOC2015D11("hepxxyzz").run2()
}

val TWO_PAIRS = """([a-z])\1[a-z]*([a-z])\2""".toRegex()

val illegalChars = listOf('i', 'o', 'l')

class AOC2015D11(input: String) : Problem(input) {
    override fun solve1() = findNextPassword(input)

    override fun solve2() = findNextPassword(input)

    fun findNextPassword(s: String): String {
        var next = s
        var valid = false
        while (!valid) {
            next = next.inc()
            valid = next.hasThreeIncreasingChars() && next.hasValidLetters() && next.hasTwoPairs()
        }
        return next
    }

    // increment the given string
    private fun String.inc(): String {
        if (this.isEmpty()) return "a"
        return if (this.last() == 'z') {
            // edge case: last char is z, that means last char becomes 'a' and rest is incremented! (wrap around)
            this.dropLast(1).inc() + "a"
        } else {
            // normal case: just inc last char
            this.dropLast(1) + this.last().nextValid()
        }
    }

    private fun Char.nextValid(): Char {
        val next = this.inc()
        if (next in illegalChars) {
            return next.nextValid()
        }
        return next
    }

    private fun String.hasThreeIncreasingChars(): Boolean {
        return this.windowed(3, 1)
            .map { it.toCharArray() }
            .any { (c1, c2, c3) -> c2 == c1 + 1  && c3 == c2 + 1 }
    }

    private fun String.hasValidLetters(): Boolean {
        return this.none { it in illegalChars }
    }

    private fun String.hasTwoPairs(): Boolean {
        return TWO_PAIRS.containsMatchIn(this)
    }
}
