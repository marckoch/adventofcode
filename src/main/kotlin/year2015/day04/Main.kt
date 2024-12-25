package year2015.day04

import adventofcode.Problem
import java.math.BigInteger
import java.security.MessageDigest

fun main() {
    AOC2015D04(INPUT).run()
}

class AOC2015D04(input: String) : Problem(input) {
    override fun solve1() = firstWithZeroPrefixOfLength(5)
    override fun solve2() = firstWithZeroPrefixOfLength(6)

    private fun firstWithZeroPrefixOfLength(numberOfZeros: Int): Int {
        val zeroPrefix = "0".repeat(numberOfZeros)
        return generateSequence(0) { it + 1 }
            .first { i -> md5(input + i.toString()).startsWith(zeroPrefix) }
    }

    private fun md5(input: String): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray()))
            .toString(16)
            .padStart(32, '0')
    }
}