package year2016.day10

import adventofcode.Problem
import util.parser.findTokens

fun main() {
    AOC2016D10(SAMPLE).run()
    AOC2016D10(INPUT).run()
}

const val NUMBER = "(-?\\d*)"
const val RECEIVER_TYPE = "(\\w*)"
val startPattern = """value $NUMBER goes to bot $NUMBER""".toRegex()
val botPattern = """bot $NUMBER gives low to $RECEIVER_TYPE $NUMBER and high to $RECEIVER_TYPE $NUMBER""".toRegex()

open class Receiver(open val id: String, open val values: MutableList<Int>)

data class Output(override val id: String, override val values: MutableList<Int>) : Receiver(id, values)

data class Bot(override val id: String, override val values: MutableList<Int>, var low: Receiver? = null, var high: Receiver? = null) : Receiver(id, values) {
    fun passValues() {
        if (values.size == 2) {
            val min = values.min()
            val max = values.max()

            low?.values?.add(min).also { values.remove(min) }
            high?.values?.add(max).also { values.remove(max) }
            // println("Bot $id passes $min to ${low?.id} and $max to ${high?.id}")
        } else {
            error("Bot $id: cannot pass values: $values")
        }
    }

    override fun toString(): String {
        // only print id of low and high, otherwise it gets too long when low and high are resolved recursively
        return "Bot(id=$id, values=$values, low=${low?.id}, high=${high?.id})"
    }
}

class AOC2016D10(input: String) : Problem(input) {
    override fun solve1() : String {
        val (botMap, _)  = parse()
        while (true) {
            val entry = botMap.entries.firstOrNull { it.value.values.size == 2 }
            if (entry == null) break

            entry.value.passValues()
            botMap.entries.firstOrNull { it.value.values.containsAll(listOf(17, 61)) }?.value?.also {
                println(it)
                return it.id
            }
        }
        return "none found"
    }

    override fun solve2() : Int {
        val (botMap, outputMap)  = parse()
        while (true) {
            val entry = botMap.entries.firstOrNull { it.value.values.size == 2 }
            if (entry == null) break

            entry.value.passValues()
        }
        return outputMap.entries
            .filter { it.key.toInt() in (0..2) }
            .map { it.value.values.first() }
            .reduce { a, b -> a * b }
    }

    fun parse(): Pair<MutableMap<String, Bot>, MutableMap<String, Output>> {
        val outputMap = mutableMapOf<String, Output>()
        val botMap = mutableMapOf<String, Bot>()

        inputLines().forEach { line ->
            when {
                // "value v goes to bot b"
                startPattern.matches(line) -> {
                    val (v, botId) = startPattern.findTokens(line)
                    if (!botMap.contains(botId)) {
                        botMap[botId] = Bot(botId, mutableListOf(v.toInt()))
                    } else {
                        botMap[botId]?.values?.add(v.toInt())
                    }
                }
                // "bot b gives low to output <low> and high to bot <high>"
                botPattern.matches(line) -> {
                    val (botId, lowType, lowId, highType, highId) = botPattern.findTokens(line)

                    val low = ensureReceiver(lowType, lowId, botMap, outputMap)
                    val high = ensureReceiver(highType, highId, botMap, outputMap)

                    if (!botMap.contains(botId)) {
                        botMap[botId] = Bot(botId, mutableListOf(), low, high)
                    } else {
                        botMap[botId]?.low = low
                        botMap[botId]?.high = high
                    }
                }
                else -> error("Unknown pattern: $line")
            }
        }
        return botMap to outputMap
    }

    private fun ensureReceiver(
        receiverType: String,
        receiverId: String,
        botMap: MutableMap<String, Bot>,
        outputMap: MutableMap<String, Output>
    ): Receiver {
        val lowReceiver: Receiver
        when (receiverType) {
            "bot" -> {
                if (!botMap.contains(receiverId)) {
                    botMap[receiverId] = Bot(receiverId, mutableListOf())
                }
                lowReceiver = botMap[receiverId]!!
            }
            "output" -> {
                if (!outputMap.contains(receiverId)) {
                    outputMap[receiverId] = Output(receiverId, mutableListOf())
                }
                lowReceiver = outputMap[receiverId]!!
            }
            else -> error("Unknown receiverType: $receiverType")
        }
        return lowReceiver
    }
}