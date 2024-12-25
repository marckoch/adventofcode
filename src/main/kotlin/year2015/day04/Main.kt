package year2015.day04

import adventofcode.Problem
import java.math.BigInteger
import java.security.MessageDigest

fun main() {
    AOC2015D04(INPUT).run()
}

class AOC2015D04(rawInput: String) : Problem(rawInput) {
    private val fiveZeros = "0".repeat(5)
    private val sixZeros = "0".repeat(6)

    override fun solve1(): String {
        return firstWithPrefix(fiveZeros).toString()
    }

    override fun solve2(): String {
        return firstWithPrefix(sixZeros).toString()
    }

    private fun firstWithPrefix(prefix: String): Int {
        return generateSequence(0) { it + 1 }
            .first { i -> md5(rawInput + i.toString()).startsWith(prefix) }
    }
}

fun md5(input: String): String {
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(input.toByteArray()))
        .toString(16)
        .padStart(32, '0')
}

