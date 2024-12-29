package year2015.day19

import adventofcode.Problem

val LINE_PATTERN = """(\w*) => (\w*)""".toRegex()

fun main() {
//    AOC2015D19(SAMPLE).run()
    AOC2015D19(INPUT).run()
}

class AOC2015D19(input: String) : Problem(input) {
    private val replacements = parseInput()
    private val molecule = inputLines().last()

    override fun solve1(): Int {
        val newMolecules = mutableSetOf<String>()
        for ((from, to) in replacements) {
            var position = 0
            while ((molecule.indexOf(from, position).also { position = it }) >= 0) {
                newMolecules.add(molecule.replace(from, to, position))
                position += from.length
            }
        }
        return newMolecules.size
    }

    override fun solve2(): Int {
        var count = 0
        var m = molecule
        while (m != "e") {
            for ((from, to) in replacements) {
                if (m.contains(to)) {
                    m = m.replace(to, from, m.indexOf(to))
                    count++
                }
            }
        }
        return count
    }

    private fun parseInput(): List<Pair<String, String>> {
        val replPairs = inputLines().dropLast(2).map { // 2 because blank and last line with molecule
            val (a, b) = it.split(" => ")
            a to b
        }
        return replPairs
    }
}

fun String.replace(`in`: String, out: String, position: Int): String {
    return this.substring(0, position) + out + this.substring(position + `in`.length)
}
