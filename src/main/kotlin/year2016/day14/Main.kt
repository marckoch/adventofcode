package year2016.day14

import adventofcode.Problem
import util.hex.toLowerHex
import java.security.MessageDigest

fun main() {
    AOC2016D14(SAMPLE).run()
    AOC2016D14(INPUT).run()
}

val regexCharTriple = """(.)\1{2}""".toRegex() // This regex looks for a character repeated 3 times in a row

class AOC2016D14(input: String) : Problem(input) {
    private val md5Digester = MessageDigest.getInstance("MD5")
    private val salt = input

    override fun solve1() = getKey(salt, key = 64, hashPasses = 1)
    override fun solve2() = getKey(salt, key = 64, hashPasses = 2017)

    private fun getKey(salt: String, hashPasses: Int, key: Int): Int {
        return generateHashes(salt, hashPasses)
            .windowed(1001, 1) // Sliding window over hashes
            .withIndex()
            .filter { (_, hashes) -> isKey(hashes) }
            .take(key)
            .last()
            .index
    }

    private fun generateHashes(salt: String, hashPasses: Int = 1): Sequence<String> {
        return generateSequence(0, Int::inc)
            .map { "$salt$it" }
//            .map { md5(it, hashPasses) }
            .map {
                var x = it
                repeat(hashPasses) {
                    x = md5(x)
                }
                x
            }
    }

    private fun isKey(hashes: List<String>): Boolean {
        val firstHash = hashes.first()
        val tripleChar = firstHash.findTripleChar() ?: return false
        val fiveChars = tripleChar.first().toString().repeat(5) // Generate five consecutive characters
        return hashes.drop(1).any { it.contains(fiveChars) }
    }

    private fun String.findTripleChar(): String? {
        return regexCharTriple.find(this)?.value // Returns the matched triple or null if not found
    }

    private fun md5(input: String): String {
        return md5Digester.digest(input.toByteArray(Charsets.UTF_8)).toLowerHex()
    }

    private fun md5(input: String, hashPasses: Int): String {
        md5Digester.update(input.toByteArray(Charsets.UTF_8))
        repeat (hashPasses - 1) {
            md5Digester.update(md5Digester.digest().toLowerHex().toByteArray())
        }
        return md5Digester.digest().toLowerHex()
    }
}
