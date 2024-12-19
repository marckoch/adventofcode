package year2024.day19

fun main() {
    AOC2024D19(SAMPLE).solve1().also(::println) // 6
    AOC2024D19(INPUT).solve1().also(::println) // 226

    AOC2024D19(SAMPLE).solve2().also(::println) // 16
    AOC2024D19(INPUT).solve2().also(::println) // 601201576113503
}

class AOC2024D19(val input: String) {
    private val available = input.lines().first().split(", ")
    private val wantedPatterns = input.lines().drop(2)
    private val cache = mutableMapOf("" to true)
    private val countCache = mutableMapOf("" to 1L)

    fun solve1(): Int {
        return wantedPatterns.count { isPossible(it) }
    }

    fun solve2(): Long {
        return wantedPatterns.sumOf { countPossible(it) }
    }

    private fun isPossible(wanted: String): Boolean {
        return cache.getOrPut(wanted) {
            available.any { a -> wanted.startsWith(a) && isPossible(wanted.substring(a.length)) }
        }
    }

    private fun countPossible(wanted: String): Long {
        return countCache.getOrPut(wanted) {
            available.sumOf { if (wanted.startsWith(it)) countPossible(wanted.substring(it.length)) else 0L }
        }
    }
}
