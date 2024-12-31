package year2016.day08

import adventofcode.Problem
import util.lists.rotateRight

fun main() {
    AOC2016D08(INPUT).run()
}

class AOC2016D08(input: String) : Problem(input) {
    override fun solve1() = buildField().sumOf { booleans -> booleans.count { it } }
    override fun solve2() = print(buildField()).let { "ZFHFSFOGPO" }

    private fun buildField() : List<List<Boolean>> {
        val field = MutableList(6, { MutableList(50) { false } })

        for (i in inputLines()) {
            when {
                i.startsWith("rect ") -> {
                    turnOnTopLeft(i, field)
                }
                i.startsWith("rotate row") -> {
                    val (y, shift) = getRotationValues(i)
                    field[y] = field[y].rotateRight(shift).toMutableList()
                }
                i.startsWith("rotate col") -> {
                    val (x, shift) = getRotationValues(i)
                    field.rotateColumn(x, shift)
                }
            }
        }
        return field
    }

    fun print(field: List<List<Boolean>>) {
        for (row in field) {
            row.map { if (it) '#' else '.' }.joinToString("").also { println(it) }
        }
    }

    private fun turnOnTopLeft(i: String, field: MutableList<MutableList<Boolean>>) {
        val (c, r) = i.split(" ").last().split("x").map { it.toInt() }
        for (row in 0 until r) {
            for (column in 0 until c) {
                field[row][column] = true
            }
        }
    }

    private fun getRotationValues(s: String): Pair<Int, Int> {
        val values = s.substringAfter("=")
        val a = values.substringBefore(" ").toInt()
        val shift = values.substringAfterLast(" ").toInt()
        return a to shift
    }

    private fun <T> List<MutableList<T>>.rotateColumn(columnIndex: Int, shift: Int) {
        val numRows = size
        if (numRows == 0 || columnIndex < 0 || columnIndex >= this[0].size) return

        val column = map { it[columnIndex] }

        val rotatedColumn = column.rotateRight(shift)

        // Replace the original column with the rotated one
        for (rowIndex in indices) {
            this[rowIndex][columnIndex] = rotatedColumn[rowIndex]
        }
    }
}
