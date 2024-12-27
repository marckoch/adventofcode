package year2015.day16

import adventofcode.Problem
import util.parser.findTokens

const val THING = """(\w*)"""
const val NUMBER = """(\d*)"""

val LINE_PATTERN = """Sue $NUMBER: $THING: $NUMBER, $THING: $NUMBER, $THING: $NUMBER""".toRegex()

fun main() {
    AOC2015D16(INPUT).run()
}

fun Int?.matchesOrNull(value: Int?): Boolean = this == null || this == value
fun Int?.greaterThanOrNull(value: Int?): Boolean = this == null || (value != null && this > value)
fun Int?.lessThanOrNull(value: Int?): Boolean = this == null || (value != null && this < value)

data class Sue(
    val id: Int,
    val children: Int? = null,
    val cats: Int? = null,
    val samoyeds: Int? = null,
    val pomeranians: Int? = null,
    val akitas: Int? = null,
    val vizslas: Int? = null,
    val goldfish: Int? = null,
    val trees: Int? = null,
    val cars: Int? = null,
    val perfumes: Int? = null
) {
    companion object {
        fun from(s: String): Sue {
            return Sue(-1,
                s[0].digitToInt(),
                s[1].digitToInt(),
                s[2].digitToInt(),
                s[3].digitToInt(),
                s[4].digitToInt(),
                s[5].digitToInt(),
                s[6].digitToInt(),
                s[7].digitToInt(),
                s[8].digitToInt(),
                s[9].digitToInt())
        }
    }

    fun copy(propertyName: String, value: Int): Sue {
        return when (propertyName) {
            "children" -> copy(children = value)
            "cats" -> copy(cats = value)
            "samoyeds" -> copy(samoyeds = value)
            "pomeranians" -> copy(pomeranians = value)
            "akitas" -> copy(akitas = value)
            "vizslas" -> copy(vizslas = value)
            "goldfish" -> copy(goldfish = value)
            "trees" -> copy(trees = value)
            "cars" -> copy(cars = value)
            "perfumes" -> copy(perfumes = value)
            else -> error("unknown property: $propertyName")
        }
    }
}

class AOC2015D16(input: String) : Problem(input) {
    private val sues = parseInput()

    override fun solve1(): Int {
        val targetSue = Sue.from("3723005321")
        return sues.first { sue ->
            sue.children.matchesOrNull(targetSue.children) &&
            sue.cats.matchesOrNull(targetSue.cats) &&
            sue.samoyeds.matchesOrNull(targetSue.samoyeds) &&
            sue.pomeranians.matchesOrNull(targetSue.pomeranians) &&
            sue.akitas.matchesOrNull(targetSue.akitas) &&
            sue.vizslas.matchesOrNull(targetSue.vizslas) &&
            sue.goldfish.matchesOrNull(targetSue.goldfish) &&
            sue.trees.matchesOrNull(targetSue.trees) &&
            sue.cars.matchesOrNull(targetSue.cars) &&
            sue.perfumes.matchesOrNull(targetSue.perfumes)
        }.id
    }

    override fun solve2(): Any {
        val targetSue = Sue.from("3723005321")
        return sues.first { sue ->
            sue.children.matchesOrNull(targetSue.children) &&
            sue.cats.greaterThanOrNull(targetSue.cats) &&
            sue.samoyeds.matchesOrNull(targetSue.samoyeds) &&
            sue.pomeranians.lessThanOrNull(targetSue.pomeranians) &&
            sue.akitas.matchesOrNull(targetSue.akitas) &&
            sue.vizslas.matchesOrNull(targetSue.vizslas) &&
            sue.goldfish.lessThanOrNull(targetSue.goldfish) &&
            sue.trees.greaterThanOrNull(targetSue.trees) &&
            sue.cars.matchesOrNull(targetSue.cars) &&
            sue.perfumes.matchesOrNull(targetSue.perfumes)
        }.id
    }

    private fun parseInput(): List<Sue> {
        return inputLines().map { line ->
            val (id, thing1, count1, thing2, count2, thing3, count3) = LINE_PATTERN.findTokens(line)

            Sue(id.toInt())
                .copy(thing1, count1.toInt())
                .copy(thing2, count2.toInt())
                .copy(thing3, count3.toInt())
        }
    }

    private operator fun <E> List<E>.component6(): E {
        return get(5)
    }

    private operator fun <E> List<E>.component7(): E {
        return get(6)
    }
}