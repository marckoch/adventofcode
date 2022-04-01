package year2015.day15

fun main() {
    processTestInput { spoons: List<Int>, ingredients: List<Ingredient> -> getScore(spoons, ingredients) }
    processRealInput { spoons: List<Int>, ingredients: List<Ingredient> -> getScore(spoons, ingredients) }
}

private fun getScore(spoons: List<Int>, ingredients: List<Ingredient>): Int {
    val capa = getScoreForProperty(spoons, ingredients) { i: Ingredient -> i.capacity }
    val dura = getScoreForProperty(spoons, ingredients) { i: Ingredient -> i.durability }
    val flav = getScoreForProperty(spoons, ingredients) { i: Ingredient -> i.flavor }
    val text = getScoreForProperty(spoons, ingredients) { i: Ingredient -> i.texture }

    return capa * dura * flav * text
}