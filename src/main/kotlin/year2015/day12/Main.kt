package year2015.day12

import adventofcode.Problem
import kotlinx.serialization.json.*

fun main() {
    AOC2015D12(INPUT).run()
}

class AOC2015D12(input: String) : Problem(input) {
    override fun solve1() = Json.parseToJsonElement(input).sumUpElements()
    override fun solve2() = Json.parseToJsonElement(input).sumUpElements(ignoreProperty = "red")

    private fun JsonElement.sumUpElements(ignoreProperty: String = ""): Int {
        return when (this) {
            is JsonArray -> this.jsonArray.sumUpElements(ignoreProperty)
            is JsonObject -> this.jsonObject.sumUpElements(ignoreProperty)
            is JsonPrimitive -> this.jsonPrimitive.sum()
            else -> error("Unexpected element $this")
        }
    }

    private fun JsonPrimitive.sum() = if (this.isString) 0 else this.content.toInt()

    private fun JsonArray.sumUpElements(ignoreProperty: String = "") = this.sumOf { it.sumUpElements(ignoreProperty) }

    private fun JsonObject.sumUpElements(ignoreProperty: String = ""): Int {
        if (ignoreProperty.isNotEmpty() && this.values.any { it is JsonPrimitive && it.content == ignoreProperty })
            return 0
        return this.values.sumOf { it.sumUpElements(ignoreProperty) }
    }
}
