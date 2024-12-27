package year2015.day18

import adventofcode.Problem

fun main() {
    AOC2015D18(SAMPLE, 4).run()
    AOC2015D18(INPUT, 100).run()
}

class AOC2015D18(input: String, val steps: Int) : Problem(input) {
    override fun solve1(): Int {
        val life = Life(Field.fromInput(inputLines()))
        repeat (steps) {
            life.step()
        }
        return life.countLights()
    }

    override fun solve2(): Any {
        val life = Life(Field.fromInput(inputLines()))
        // corner lights are always on, even in initial field
        life.turnOnCornerLights()
        repeat(steps) {
            life.step()
            life.turnOnCornerLights()
        }
        return life.countLights()
    }
}

// represents the game field
class Field(val width: Int, val height: Int) {
    companion object {
        fun fromInput(input: List<String>): Field {
            val w = input.first().length
            val h = input.count()
            val initialField = Field(w,h)

            input.withIndex().map { indexedLine ->
                val row = indexedLine.index
                indexedLine.value.withIndex().map { indexedChar ->
                    val col = indexedChar.index
                    initialField[col, row] = indexedChar.value == '#'
                }
            }
            return initialField
        }
    }

    private val s = List(height) { BooleanArray(width) }

    operator fun set(x: Int, y: Int, b: Boolean) {
        s[y][x] = b
    }

    fun next(x: Int, y: Int): Boolean {
        var countLitNeighbors = 0
        for (i in -1..1) {
            for (j in -1..1) {
                // check 8 neighbors
                if (state(x + i, y + j) && !(j == 0 && i == 0)) countLitNeighbors++
            }
        }
        return when (state(x, y)) {
            true -> (countLitNeighbors == 2 || countLitNeighbors == 3)
            false -> countLitNeighbors == 3
        }
    }

    fun state(x: Int, y: Int): Boolean {
        // fields outside the field count as false/off
        if ((x !in 0 until width) || (y !in 0 until height)) return false
        return s[y][x]
    }

    fun countLights(): Int {
        return s.sumOf { booleans -> booleans.count { b: Boolean -> b } }
    }

    fun turnOnCornerLights() {
        s[0][0] = true
        s[0][width - 1] = true
        s[height - 1][0] = true
        s[height - 1][width - 1] = true
    }
}

// represent the Game of life, Conway
class Life(field: Field) {
    private var state: Field
    private var nextState: Field

    init {
        state = field
        nextState = Field(state.width, state.height)
    }

    fun step() {
        for (y in 0 until state.height) {
            for (x in 0 until state.width) {
                nextState[x, y] = state.next(x, y)
            }
        }
        val t = state
        state = nextState
        nextState = t
    }

    override fun toString(): String {
        val sb = StringBuilder()
        for (y in 0 until state.height) {
            for (x in 0 until state.width) {
                val c = if (state.state(x, y)) '#' else '.'
                sb.append(c)
            }
            sb.append('\n')
        }
        return sb.toString()
    }

    fun countLights(): Int {
        return state.countLights()
    }

    fun turnOnCornerLights() {
        state.turnOnCornerLights()
    }
}
