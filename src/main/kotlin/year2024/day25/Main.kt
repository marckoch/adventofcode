package year2024.day25

import util.parser.readChunks

fun main() {
    AOC2024D25(SAMPLE).solve().also(::println) // 3
    AOC2024D25(INPUT).solve().also(::println) // 3077
}

// "The locks are schematics that have the top row filled (#) and the bottom row empty (.)..."
fun List<String>.isLock() = this.first().all { c -> c == '#' }

class AOC2024D25(val input: String) {
    fun solve(): Int {
        val chunks = readChunks(input)
        val locks = chunks.filter { it.isLock() }.map { c -> parse(c) }
        val keys = chunks.filter { !it.isLock() }.map { c -> parse(c) }

        return locks.sumOf { lock -> keys.count { key -> fits(key, lock) } }
    }

    private fun fits(key: IntArray, lock: IntArray): Boolean {
        return key.zip(lock).all { it.first + it.second <= 5 }
    }

    fun parse(lines: List<String>): IntArray {
        val heights = IntArray(lines.first().length) { -1 }
        lines.forEach { line ->
            line.withIndex().forEach { (i, value) -> if (value == '#') heights[i]++ }
        }
        return heights
    }
}
