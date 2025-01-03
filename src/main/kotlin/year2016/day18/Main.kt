package year2016.day18

import adventofcode.Problem

val SAMPLE = """
.^^.^.^^^^
10
""".trimIndent()

val INPUT = """
^.^^^.^..^....^^....^^^^.^^.^...^^.^.^^.^^.^^..^.^...^.^..^.^^.^..^.....^^^.^.^^^..^^...^^^...^...^.
40
""".trimIndent()

fun main() {
    AOC2016D18(SAMPLE).run()
    AOC2016D18(INPUT).run()
}

const val TRAP = '^'
const val SAFE = '.'

class AOC2016D18(input: String) : Problem(input) {
    private val firstRow = inputLines().first().toCharArray()
    private val inputRows = inputLines()[1].toInt()

    override fun solve1() = solve(rows = inputRows)
    override fun solve2() = solve(rows = 400_000)

    fun solve(rows: Int) : Int {

        val field = mutableListOf(firstRow)
        repeat(rows - 1) {
            field.add(next(field.last()))
        }
        return field.sumOf { row -> row.count { c -> !c.isTrap() } }
    }

    fun next(row: CharArray) : CharArray {
        return CharArray(row.size) { i -> determineField(row, i) }
    }

    private fun determineField(row: CharArray, i: Int) : Char {
        val left = if (i in 1..row.lastIndex) row[i - 1] else SAFE
        val center = row[i]
        val right = if (i in 0 until row.lastIndex) row[i + 1] else SAFE

        return when {
            left.isTrap() && center.isTrap() && !right.isTrap()-> TRAP
            !left.isTrap() && center.isTrap() && right.isTrap()-> TRAP
            left.isTrap() && !center.isTrap() && !right.isTrap()-> TRAP
            !left.isTrap() && !center.isTrap() && right.isTrap()-> TRAP
            else -> SAFE
        }
    }

    fun Char.isTrap() : Boolean = this == TRAP
}
