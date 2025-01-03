package year2016.day16

import adventofcode.Problem
import util.ints.isEven

val SAMPLE = """
20
10000
""".trimIndent()

val INPUT = """
272
01111001100111011
""".trimIndent()

fun main() {
    AOC2016D16(SAMPLE).run()
    AOC2016D16(INPUT).run()
}

// avoid (intermediate) lists and 'map' to increase performance and reduce memory usage
// a lot of low level (barely readable) array operations are done here
class AOC2016D16(input: String) : Problem(input) {
    private val length = inputLines()[0].toInt()
    private val startingString = inputLines()[1]

    override fun solve1() = solve(startingString, length = length)
    override fun solve2() = solve(startingString, length = 35651584)

    fun solve(s: String, length: Int) : String {
        var x = s.toCharArray()
        while (x.size < length) {
            x = dragon(x)
        }

        x = x.copyOfRange(0, length)

        return checksum(x).joinToString("")
    }
}

fun dragon(s : String): String {
    return dragon(s.toCharArray()).joinToString("")
}

fun dragon(a : CharArray): CharArray {
    val b = CharArray(a.size) { a[a.size - 1 - it].invert() }
    return (a + '0' + b)
}

fun checksum(s: CharArray): CharArray {
    val res = CharArray(s.size / 2)
    for (i in s.indices step 2) {
        res[i / 2] = if (s[i] == s[i + 1]) '1' else '0'
    }
    return if (res.size.isEven())
        checksum(res)
    else
        res
}

fun Char.invert(): Char {
    return when (this) {
        '1' -> '0'
        '0' -> '1'
        else -> throw IllegalArgumentException("Cannot invert illegal char $this")
    }
}