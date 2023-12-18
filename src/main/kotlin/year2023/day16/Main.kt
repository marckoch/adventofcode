package year2023.day16

import kotlin.math.max

fun main() {
    println(part1(SAMPLE)) // 46
    println(part1(INPUT)) // 7870

    println(part2(SAMPLE)) // 51
    println(part2(INPUT)) // 8143
}

fun part1(input: String): Int {
    val field = Field(input)
    //field.print()

    lightUpField(field, Beam(Position(0, 0), Direction.RIGHT))

    //field.print()
    return field.countBeams()
}

fun part2(input: String): Int {
    val f = Field(input)
    val lastRowIndex = f.lastRowIndex()
    val lastColIndex = f.lastColIndex()

    var max = 0
    // left edge going right
    (0..lastRowIndex).forEach { rowIndex ->
        val field = Field(input)
        lightUpField(field, Beam(Position(rowIndex, 0), Direction.RIGHT))
        val count = field.countBeams()
        max = max(max, count)
    }
    // right edge going left
    (0..lastRowIndex).forEach { rowIndex ->
        val field = Field(input)
        lightUpField(field, Beam(Position(rowIndex, lastRowIndex), Direction.LEFT))
        val count = field.countBeams()
        max = max(max, count)
    }
    // upper edge going down
    (0..lastColIndex).forEach { colIndex ->
        val field = Field(input)
        lightUpField(field, Beam(Position(0, colIndex), Direction.DOWN))
        val count = field.countBeams()
        max = max(max, count)
    }
    // lower edge going up
    (0..lastColIndex).forEach { colIndex ->
        val field = Field(input)
        lightUpField(field, Beam(Position(lastRowIndex, colIndex), Direction.UP))
        val count = field.countBeams()
        max = max(max, count)
    }
    return max
}

fun lightUpField(field: Field, startingBeam: Beam) {
    val currentBeams = mutableListOf(startingBeam)

    while (true) {
        if (currentBeams.isEmpty()) {
            break
        }

        val currentBeam = currentBeams.removeFirst()
        
        if (currentBeam.isNotIn(field)) {
            continue
        }

        val spot = field.getSpot(currentBeam.position)

        // we have already been here
        if (spot.beams.contains(currentBeam.direction)) {
            continue
        }

        spot.beams.add(currentBeam.direction)

        when (currentBeam.direction) {
            Direction.UP -> {
                when (spot.mirror) {
                    '/' -> currentBeams.add(currentBeam.right())
                    '\\' -> currentBeams.add(currentBeam.left())
                    '-' -> {
                        currentBeams.add(currentBeam.left())
                        currentBeams.add(currentBeam.right())
                    }
                    '.', '|' -> currentBeams.add(currentBeam.up())
                }
            }
            Direction.DOWN -> {
                when (spot.mirror) {
                    '/' -> currentBeams.add(currentBeam.left())
                    '\\' -> currentBeams.add(currentBeam.right())
                    '-' -> {
                        currentBeams.add(currentBeam.left())
                        currentBeams.add(currentBeam.right())
                    }
                    '.', '|' -> currentBeams.add(currentBeam.down())
                }
            }
            Direction.LEFT -> {
                when (spot.mirror) {
                    '/' -> currentBeams.add(currentBeam.down())
                    '\\' -> currentBeams.add(currentBeam.up())
                    '|' -> {
                        currentBeams.add(currentBeam.up())
                        currentBeams.add(currentBeam.down())
                    }
                    '.', '-' -> currentBeams.add(currentBeam.left())
                }
            }
            Direction.RIGHT -> {
                when (spot.mirror) {
                    '/' -> currentBeams.add(currentBeam.up())
                    '\\' -> currentBeams.add(currentBeam.down())
                    '|' -> {
                        currentBeams.add(currentBeam.up())
                        currentBeams.add(currentBeam.down())
                    }
                    '.', '-' -> currentBeams.add(currentBeam.right())
                }
            }
        }

    }
}

class Field(input: String) {
    private val spots: List<List<Spot>>

    init {
        spots = readInput(input)
    }

    private fun readInput(input: String): List<List<Spot>> {
        return input.lines().drop(1).map { line ->
            line.toCharArray().map { char ->
                Spot(char, mutableListOf())
            }.toMutableList()
        }
    }

    fun getSpot(position: Position): Spot {
        return spots[position.first][position.second]
    }

    fun lastRowIndex(): Int = spots.lastIndex

    fun lastColIndex(): Int = spots[0].lastIndex

    fun countBeams(): Int {
        return spots.sumOf { row ->
            row.count { spot ->
                spot.beams.isNotEmpty()
            }
        }
    }

    fun print() {
        println("--------------------")
        spots.forEach { row ->
            row.forEach { spot ->
                if (spot.mirror == '.') {
                    if (spot.beams.isEmpty()) {
                        print('.')
                    } else if (spot.beams.size == 1) {
                        when (spot.beams.first()) {
                            Direction.UP -> print('^')
                            Direction.DOWN -> print('v')
                            Direction.LEFT -> print('<')
                            Direction.RIGHT -> print('>')
                        }
                    } else {
                        print(spot.beams.size)
                    }
                } else {
                    print(spot.mirror)
                }
            }
            println()
        }
    }
}

data class Spot(val mirror: Char, val beams: MutableList<Direction>)

data class Beam(val position: Position, val direction: Direction) {
    fun up(): Beam {
        return Beam(Position(position.first - 1, position.second), Direction.UP)
    }
    fun down(): Beam {
        return Beam(Position(position.first + 1, position.second), Direction.DOWN)
    }
    fun left(): Beam {
        return Beam(Position(position.first, position.second - 1), Direction.LEFT)
    }
    fun right(): Beam {
        return Beam(Position(position.first, position.second + 1), Direction.RIGHT)
    }

    fun isNotIn(field: Field): Boolean  = isIn(field).not()

    private fun isIn(field: Field): Boolean {
        return position.first in 0..field.lastRowIndex() &&
                position.second in 0..field.lastColIndex()
    }
}

typealias Position = Pair<Int, Int>

enum class Direction {
    UP, DOWN, LEFT, RIGHT
}