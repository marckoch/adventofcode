package year2016.day11

import adventofcode.Problem
import util.lists.cartesianProduct
import util.lists.combinations
import java.util.*

fun main() {
    AOC2016D11(SAMPLE).run()
//    AOC2016D11(INPUT).run()
}

val genPattern = "a (\\w*) generator".toRegex()
val microchipPattern = "a (\\w*)-compatible microchip".toRegex()

data class State(val elevator: Int, val floors: Map<Int, List<String>>, val move: Int, val prev: State?) {
    fun next(): List<State> {
        var nextFloors = listOf(elevator + 1, elevator - 1).filter { it in (1..4) }
        val thingsOnThisFloor = floors[elevator]!!

        val take = (1..2).flatMap { thingsOnThisFloor.combinations(it) }

        return nextFloors.cartesianProduct(take).map { (nextFloor, t) ->
            this.moveThingsToFloor(elevator, nextFloor, t)
        }.filter { it.isValid() }.sortedByDescending { it.sum() }.toList()
    }

    private fun moveThingsToFloor(fromFloor: Int, toFloor: Int, things: List<String>) : State {
        val newFromFloor = floors[fromFloor]?.filter { it !in things }
        val newToFloor = floors[toFloor]!! + things

        val newMap = floors.toMutableMap()

        newMap[fromFloor] = newFromFloor!!
        newMap[toFloor] = newToFloor
        return State(toFloor, newMap, move + 1, this)
    }

    fun isDone() = elevator == 4 &&
            floors[1]!!.isEmpty() &&
            floors[2]!!.isEmpty() &&
            floors[3]!!.isEmpty()

    fun sum() = floors.entries.sumOf { it.key * it.value.count() }

    fun isValid() = floors.entries.none { it.value.isDangerous() }

    fun List<String>.isDangerous(): Boolean {
        if (this.isEmpty()) return false
        val microchips = this.filter { it.isMicroChip() }
        if (microchips.isEmpty()) return false
        val generators = this.filter { it.isGenerator() }
        if (generators.isEmpty()) return false

        val unmatchedChip = microchips.any { m -> !generators.contains(m.counterpart()) }
        return unmatchedChip
    }

    fun String.isGenerator() = this.endsWith("G")
    fun String.isMicroChip() = this.endsWith("M")
    fun String.counterpart() = this.getElement() + if (this.isMicroChip()) "G" else "M"
    fun String.getElement() = this.dropLast(1)

    fun print() {
        val orderedThings = floors.entries.flatMap { it.value }.sorted()
        for (f in (4 downTo 1)) {
            val e = if (elevator == f) "E" else "."
            val t = orderedThings.joinToString(separator = " ") { if (floors[f]!!.contains(it)) it else " . " }
            println("F$f $e $t")
        }
        println("sum: ${sum()} move: $move")
        println()
    }
}

class AOC2016D11(input: String) : Problem(input) {
    override fun solve1() : Int {
        val initialState = parse()
//        initialState.print()
//        initialState.next().forEach { it.print() }
        val queue = PriorityQueue(compareByDescending(State::sum))
        val visited = mutableSetOf<State>()
        queue.add(initialState)
        while (queue.isNotEmpty()) {
//            println("${queue.size} ${visited.size}")
            val s = queue.poll()
            visited.add(s.copy(move = 0, prev = null))
            if (s.isDone()) {
//                s.print()
//                println(s)
                var path = mutableListOf<State>(s)
                var c = s
                while (true) {
                    c = c.prev
                    if (c != null) {
                        path.addFirst(c)
                    } else
                        break
                }
                path.forEach { it.print() }
                return s.move
            }
            for (next in s.next()) {
                if (!visited.contains(next.copy(move = 0, prev = null))) {
                    queue.add(next)
                }
            }
        }
        return 1
    }

    override fun solve2() : Int {
        return 1
    }

    fun parse() : State {
        val thingsOnFloor = mutableMapOf<Int, List<String>>()
        inputLines().withIndex().forEach { (i, line) ->
            thingsOnFloor[i + 1] =
                extract(line, genPattern, "G") +
                extract(line, microchipPattern, "M")
        }
        return State(1, thingsOnFloor, 0, null)
    }

    private fun extract(line: String, pattern: Regex, suffix: String): List<String> {
        return pattern.findAll(line).toList().map { short(it.groups[1]!!.value) + suffix }.toList()
    }

    private fun short(s: String): String {
        return when (s) {
            "hydrogen" -> "Hy"
            "lithium" -> "Li"
            "cobalt" -> "Co"
            "polonium" -> "Po"
            "thulium" -> "Th"
            "ruthenium" -> "Ru"
            "promethium" -> "Pr"
            else -> s
        }
    }
}