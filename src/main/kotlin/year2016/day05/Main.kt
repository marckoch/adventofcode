package year2016.day05

import adventofcode.Problem
import java.math.BigInteger
import java.security.MessageDigest

fun main() {
    AOC2016D05(SAMPLE).run()
    AOC2016D05(INPUT).run()
}

class AOC2016D05(input: String) : Problem(input) {
    private val md5Digester = MessageDigest.getInstance("MD5")
    private val zeroPrefix = "0".repeat(5)
    private val doorId = input

    override fun solve1(): String {
        return generateSequence(0, Int::inc)
            .map { "$doorId$it" }
            .map { md5(it) }
            .filter { i -> i.startsWith(zeroPrefix) }
            .take(8)
            .map { it[5] }
            .joinToString("")
    }

    override fun solve2(): String {
        return generateSequence(0 , Int::inc)
            .map { "$doorId$it" }
            .map { md5(it) }
            .filter { it.startsWith(zeroPrefix) }
            .filter { it[5].isDigit() && it[5].digitToInt() in 0..7}
            .distinctBy { it[5] }
            .take(8)
            .sortedBy { it[5] }
            .map { it[6] }
            .joinToString("")
    }

    private fun md5(input: String): String {
        return BigInteger(1, md5Digester.digest(input.toByteArray()))
            .toString(16)
            .padStart(32, '0')
    }
}
