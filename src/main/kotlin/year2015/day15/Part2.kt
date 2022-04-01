package year2015.day15

fun main() {
    processTestInput { spoons: List<Int>, ingredients: List<Ingredient> -> getScore(spoons, ingredients) }
    processRealInput { spoons: List<Int>, ingredients: List<Ingredient> -> getScore(spoons, ingredients) }
}

// same as Part 1 but we only return scores when calories == 500
private fun getScore(spoons: List<Int>, ingredients: List<Ingredient>): Int {
    val capa = getScoreForProperty(spoons, ingredients) { i: Ingredient -> i.capacity }
    val dura = getScoreForProperty(spoons, ingredients) { i: Ingredient -> i.durability }
    val flav = getScoreForProperty(spoons, ingredients) { i: Ingredient -> i.flavor }
    val text = getScoreForProperty(spoons, ingredients) { i: Ingredient -> i.texture }

    val calo = getScoreForProperty(spoons, ingredients) {i: Ingredient -> i.calories }

    return if (calo == 500)
        capa * dura * flav * text
    else 0
}