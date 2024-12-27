package year2015.day15

import adventofcode.Problem
import util.parser.findTokens

const val NUMBER = "(-?\\d*)"

val LINE_PATTERN = """(\w*): capacity $NUMBER, durability $NUMBER, flavor $NUMBER, texture $NUMBER, calories $NUMBER""".toRegex()

fun main() {
    AOC2015D15(SAMPLE).run()
    AOC2015D15(INPUT).run()
}

data class Ingredient(val name: String, val capacity: Int, val durability: Int, val flavor: Int, val texture: Int, val calories: Int)

class AOC2015D15(input: String) : Problem(input) {
    private val ingredients = parseIngredients()

    override fun solve1() = buildSpoonCombinations(ingredients.size).maxOfOrNull(::getScore1) ?: 0

    override fun solve2() = buildSpoonCombinations(ingredients.size).maxOfOrNull(::getScore2) ?: 0

    private fun parseIngredients(): List<Ingredient> {
        return inputLines().map { line ->
            val (name, _) = LINE_PATTERN.findTokens(line)
            val (capa, dur, flavor, text, cal) = LINE_PATTERN.findTokens(line).drop(1).map { it.toInt() }
            Ingredient(name, capa, dur, flavor, text, cal)
        }
    }

    private operator fun <E> List<E>.component6(): E {
        return get(5)
    }

    private fun getScoreForProperty(spoons: List<Int>, function: (Ingredient) -> Int): Int {
        val score = spoons.zip(ingredients) { spoon, ingr -> spoon * function(ingr) }.sum()
        return maxOf(score, 0)
    }

    fun getScore1(spoons: List<Int>): Int {
        val capa = getScoreForProperty(spoons, Ingredient::capacity)
        val dura = getScoreForProperty(spoons, Ingredient::durability)
        val flav = getScoreForProperty(spoons, Ingredient::flavor)
        val text = getScoreForProperty(spoons, Ingredient::texture)

        return capa * dura * flav * text
    }

    // same as Part 1 but we only return scores when calories == 500
    fun getScore2(spoons: List<Int>): Int {
        val capa = getScoreForProperty(spoons, Ingredient::capacity)
        val dura = getScoreForProperty(spoons, Ingredient::durability)
        val flav = getScoreForProperty(spoons, Ingredient::flavor)
        val text = getScoreForProperty(spoons, Ingredient::texture)

        val calo = getScoreForProperty(spoons, Ingredient::calories)

        return if (calo == 500)
            capa * dura * flav * text
        else 0
    }

    private fun buildSpoonCombinations(dim: Int): List<List<Int>> {
        return when (dim) {
            2 -> buildSpoonCombinations2()
            4 -> buildSpoonCombinations4()
            else -> error("Unknown dim $dim")
        }
    }

    private fun buildSpoonCombinations2(): List<List<Int>> {
        val combinations = mutableListOf<List<Int>>()
        for (n1 in 0..100) {
            val n2 = 100 - n1

            combinations.add(listOf(n1, n2))
        }
        return combinations
    }

    private fun buildSpoonCombinations4(): List<List<Int>> {
        val combinations = mutableListOf<List<Int>>()
        for (spoons1 in 0..100) {
            for (spoons2 in 0..100 - spoons1) {
                for (spoons3 in 0..100 - spoons1 - spoons2) {
                    val spoons4 = 100 - spoons1 - spoons2 - spoons3
                    if (spoons4 < 0) continue

                    listOf(spoons1, spoons2, spoons3, spoons4).takeIf { it.sum() == 100 }?.also {
                        combinations.add(it)
                    }
                }
            }
        }
        return combinations
    }
}

