package year2020.day21

fun main() {
    part1(SAMPLE)
    part1(INPUT)

    part2(SAMPLE)
    part2(INPUT)
}

val LINE_PATTERN = """(.*) \(contains (.*)\)""".toRegex()

fun part1(input: String) {
    val foods = parse(input)
    // foods.forEach { println(it) }

    // has to be a list, not set! because we have to count multiple occurrences of an ingredient! ('sbzzf' in example)
    val allIngredients = foods.flatMap { it.ingredients }
    // println(allIngredients)

    val potentialIngredientsWithAllergene = findPotentialIngredientsWithAllergene(foods)
    // println(potentialIngredientsWithAllergene)

    val x = allIngredients.filter { it !in potentialIngredientsWithAllergene.values.flatten() }
    println("part1: ${x.size}")
}

fun part2(input: String) {
    val foods = parse(input)
    // foods.forEach { println(it) }

    val potentialIngredientsWithAllergene = findPotentialIngredientsWithAllergene(foods)
    // println(potentialIngredientsWithAllergene)

    val certainAllergenes = reduce(potentialIngredientsWithAllergene.toMutableMap(), HashMap())
    // println(certainAllergenes)

    certainAllergenes.keys.sorted()
        .map { certainAllergenes[it] }
        .joinToString(",")
        .let { println("part2: $it") }
}

// we recursively check for a potential allergene that only occurs once in an ingredient and move it to the certain map
// and remove it from the potential map, this should produce a new certain one (that occurs only once after removing the certain one)
fun reduce(
    potentialAllergenes: MutableMap<Allergene, Set<Ingredient>>,
    certainAllergenes: MutableMap<Allergene, Ingredient>
): Map<Allergene, Ingredient> {
    // println("reduce: potentialAllergenes=$potentialAllergenes, certainAllergenes=$certainAllergenes")
    if (potentialAllergenes.isEmpty())
        return certainAllergenes

    val certain = potentialAllergenes.filterValues { it.size == 1 }
    if (certain.isEmpty())
        return certainAllergenes

    certain.forEach { (key, value) ->
        // println("certain: $entry")

        // move this to map with certain allergenes
        certainAllergenes[key] = value.single()

        // and remove it completely from map with potential allergenes
        potentialAllergenes.remove(key)
        potentialAllergenes.keys.forEach { k ->
            potentialAllergenes[k] = potentialAllergenes[k]!! - value.single()
        }
    }

    return reduce(potentialAllergenes, certainAllergenes)
}

fun findPotentialIngredientsWithAllergene(foods: List<Food>): Map<Allergene, Set<Ingredient>> {
    return foods.fold(HashMap()) { acc, food ->
        food.allergenes.forEach { allergene ->
            val reducedIngredientListForAllergene = if (!acc.containsKey(allergene)) {
                food.ingredients.toSet()
            } else {
                // with each new ingredients list we can narrow it down more, an allergene has to occur on both sets (the intersection)
                acc[allergene]!! intersect food.ingredients.toSet()
            }
            acc[allergene] = reducedIngredientListForAllergene
        }
        return@fold acc
    }
}

fun parse(input: String): List<Food> {
    return input.lines().map { line ->
        val (ins, als) = LINE_PATTERN.find(line)!!.destructured
        val ingredients = ins.split(" ")
        val allergenes = als.split(",").map { it.trim() }
        Food(ingredients, allergenes)
    }
}

data class Food(val ingredients: List<Ingredient>, val allergenes: List<Allergene>) {
    override fun toString(): String {
        return "Food(${ingredients.sorted()}, ${allergenes.sorted()}"
    }
}

typealias Ingredient = String

typealias Allergene = String