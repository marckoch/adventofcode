package year2015.day15

const val NUMBER = "(-?\\d*)"

const val TEST_INPUT = """Butterscotch: capacity -1, durability -2, flavor 6, texture 3, calories 8
Cinnamon: capacity 2, durability 3, flavor -2, texture -1, calories 3"""

const val INPUT = """Sprinkles: capacity 2, durability 0, flavor -2, texture 0, calories 3
Butterscotch: capacity 0, durability 5, flavor -3, texture 0, calories 3
Chocolate: capacity 0, durability 0, flavor 5, texture -1, calories 8
Candy: capacity 0, durability -1, flavor 0, texture 5, calories 8"""

val LINE_PATTERN = """(\w*): capacity $NUMBER, durability $NUMBER, flavor $NUMBER, texture $NUMBER, calories $NUMBER""".toRegex()

fun readIngredients(input: List<String>): List<Ingredient> {
    return input.map { line ->
        val (name, capa, dur, flavor, text, cal) = LINE_PATTERN.find(line)!!.destructured
        Ingredient(name, capa.toInt(), dur.toInt(), flavor.toInt(), text.toInt(), cal.toInt())
    }
}

data class Ingredient(val name: String, val capacity: Int, val durability: Int, val flavor: Int, val texture: Int, val calories: Int)

fun processTestInput(scoreFunction: (List<Int>, List<Ingredient>) -> Int) {
    val ingredients = readIngredients(TEST_INPUT.lines())

    var maxScore = 0

    for (spoons1 in 1..99) {
        val spoons2 = 100 - spoons1

        val spoons = listOf(spoons1, spoons2)
        val score = scoreFunction(spoons, ingredients)
        maxScore = maxOf(score, maxScore)
    }
    println(maxScore)
}

fun processRealInput(scoreFunction: (List<Int>, List<Ingredient>) -> Int) {
    val ingredients = readIngredients(INPUT.lines())

    var maxScore = 0

    for (spoons1 in 0..100) {
        for (spoons2 in 0..100) {
            for (spoons3 in 0..100) {
                val spoons4 = 100 - spoons1 - spoons2 - spoons3
                if (spoons4 < 0) continue

                val spoons = listOf(spoons1, spoons2, spoons3, spoons4)
                val score = scoreFunction(spoons, ingredients)
                maxScore = maxOf(score, maxScore)
            }
        }
    }
    print(maxScore)
}

fun getScoreForProperty(spoons: List<Int>, ingredients: List<Ingredient>, function: (Ingredient) -> Int): Int {
    val score = spoons.zip(ingredients) { spoon, ingr -> spoon * function(ingr) }.sum()
    return maxOf(score, 0)
}