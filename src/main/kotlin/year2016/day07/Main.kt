package year2016.day07

import adventofcode.Problem

fun main() {
    AOC2016D07(SAMPLE).run()
    AOC2016D07(INPUT).run()
}

class AOC2016D07(input: String) : Problem(input) {
    private val pattern = """(\[\w*])|(\w*)""".toRegex()
    private val abaPattern = """(.)((?!\1).)\1""".toRegex() // three chars, first and last are same, second is different

    override fun solve1() = inputLines().count { supportsTLS(it) }
    override fun solve2() = inputLines().count { supportsSSL(it) }

    private fun supportsTLS(s: String): Boolean {
        val (hypernet, supernet) = getGroups(s)
        return hypernet.none { it.containsABBA() } && supernet.any { it.containsABBA() }
    }

    private fun supportsSSL(s: String): Boolean {
        val (hypernet, supernet) = getGroups(s)
        return hasMatchInOtherGroup(hypernet, supernet) || hasMatchInOtherGroup(supernet, hypernet)
    }

    private fun String.containsABBA(): Boolean {
        if (this.length < 4) return false
        return this.windowed(4).any { it[0] == it[3] && it[1] == it[2] && it[0] != it[1] }
    }

    private fun hasMatchInOtherGroup(group1: List<String>, group2: List<String>): Boolean {
        return group1.any { s1 ->
            abaPattern.findAll(s1).any {
                    x -> group2.any { it.contains(x.value.invert()) }
            }
        }
    }

    // "aba" -> "bab"
    private fun String.invert(): String {
        return "${this[1]}${this[0]}${this[1]}"
    }

    private fun getGroups(s: String) : Pair<List<String>, List<String>> {
        val tokens = pattern.findAll(s).map { it.value }.toList()
        return tokens.partition { it.startsWith('[') }
    }
}
