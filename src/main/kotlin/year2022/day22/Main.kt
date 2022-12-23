package year2022.day22

fun main() {
    Part1().solve(SAMPLE)
    Part1().solve(INPUT)

    Part2Sample(4).solve(SAMPLE)
    Part2Input(50).solve(INPUT)
}

abstract class Solver {
    fun solve(input: String) {
        val (map, instr) = readLineChunks(input).let { (map, path) -> Pair(padMap(map), parseSteps(path.first())) }
        //map.forEach { println(it) }

        val path = instr.fold(mutableListOf(startPosition(map))) { acc, s ->
            executeOneInstruction(map, acc, s)
            acc
        }
        val finalPos = path.last()
        println("final position: $finalPos")

        val result1 = 1000 * (finalPos.row + 1) + 4 * (finalPos.col + 1) + finalPos.heading.toInt()
        println("solution: $result1 (we took ${path.size} steps)")

        //println(path)
        //print(map, path)
    }

    // turn L or R or go forward x steps
    // returns new position
    private fun executeOneInstruction(map: List<String>, path: MutableList<Position>, instr: String) {
        if (instr == "L" || instr == "R") {
            path.last().turn(instr.first())
            println("turning $instr to ${path.last()}")
        } else {
            println("moving $instr steps")
            moveForward(map, path, instr.toInt())
            println("stopped moving at ${path.last()}")
        }
    }

    // we pad each line to the right with blanks, that will make wrapping around easier
    private fun padMap(map: List<String>): List<String> {
        val maxLength = map.map { it.length }.distinct().max()
        return map.map { it.padEnd(maxLength, ' ') }
    }

    private fun startPosition(map: List<String>): Position {
        val startCol = map.first().indexOfFirst { it == '.' }
        return Position(0, startCol, Heading.EAST)
    }

    private fun parseSteps(path: String): List<String> {
        return """\d+|\D+""".toRegex().findAll(path).map { it.groupValues.first() }.toList()
    }

    private fun moveForward(map: List<String>, path: MutableList<Position>, steps: Int) {
        repeat(steps) {
            val fieldAhead = nextFieldAhead(map, path.last())
            val f = map[fieldAhead.row][fieldAhead.col]
            if (f == '.') {
                path.add(fieldAhead)
            } else if (f == '#') {
                println("hitting wall! aborting move after $it steps")
                return
            }
        }
    }

    abstract fun nextFieldAhead(map: List<String>, pos: Position): Position
}

class Part1 : Solver() {
    // part1: move on plain
    override fun nextFieldAhead(map: List<String>, pos: Position): Position {
        if (pos.heading == Heading.EAST) {
            if (pos.col == map[pos.row].lastIndex || // hitting right end of my row
                map[pos.row][pos.col + 1] == ' '     // next field to right would be blank
            ) {
                val newCol = map[pos.row].indexOfFirst { it != ' ' }
                val newPos = pos.copy(col = newCol)
                println("hitting end of map, looking from $pos to $newPos")
                return newPos
            }
        } else if (pos.heading == Heading.SOUTH) {
            if (pos.row == map.lastIndex ||       // hitting lower end of my map
                map[pos.row + 1][pos.col] == ' '  // next field in row below would be blank
            ) {
                val newRow = map.map { s -> s[pos.col] }.indexOfFirst { it != ' ' }
                val newPos = pos.copy(row = newRow)
                println("hitting end of map, looking from $pos to $newPos")
                return newPos
            }
        } else if (pos.heading == Heading.WEST) {
            if (pos.col == 0 ||                      // hitting left end of my row
                map[pos.row][pos.col - 1] == ' '     // next field to left would be blank
            ) {
                val newCol = map[pos.row].indexOfLast { it != ' ' }
                val newPos = pos.copy(col = newCol)
                println("hitting end of map, looking from $pos to $newPos")
                return newPos
            }
        } else if (pos.heading == Heading.NORTH) {
            if (pos.row == 0 ||                   // hitting upper end of my map
                map[pos.row - 1][pos.col] == ' '  // next field in row above would be blank
            ) {
                val newRow = map.map { s -> s[pos.col] }.indexOfLast { it != ' ' }
                val newPos = pos.copy(row = newRow)
                println("hitting end of map, looking from $pos to $newPos")
                return newPos
            }
        }

        // field ahead is normal field on map, no wrapping around
        return pos.stepForward()
    }
}

class Part2Sample(cubeLength: Int) : Solver() {

    // fields are numbered like in example:
    //        1111
    //        1111
    //        1111
    //        1111
    //222233334444
    //222233334444
    //222233334444
    //222233334444
    //        55556666
    //        55556666
    //        55556666
    //        55556666

    val FIELD1_TOP_ROW = 0
    val FIELD1_BOTTOM_ROW = FIELD1_TOP_ROW + cubeLength - 1
    val FIELD1_LEFT_COL = 2 * cubeLength
    val FIELD1_RIGHT_COL = FIELD1_LEFT_COL + cubeLength - 1

    val FIELD2_TOP_ROW = cubeLength
    val FIELD2_BOTTOM_ROW = FIELD2_TOP_ROW + cubeLength - 1
    val FIELD2_LEFT_COL = 0
    val FIELD2_RIGHT_COL = FIELD2_LEFT_COL + cubeLength - 1

    val FIELD3_TOP_ROW = cubeLength
    val FIELD3_BOTTOM_ROW = FIELD3_TOP_ROW + cubeLength - 1
    val FIELD3_LEFT_COL = cubeLength
    val FIELD3_RIGHT_COL = FIELD3_LEFT_COL + cubeLength - 1

    val FIELD4_TOP_ROW = cubeLength
    val FIELD4_BOTTOM_ROW = FIELD4_TOP_ROW + cubeLength - 1
    val FIELD4_LEFT_COL = 2 * cubeLength
    val FIELD4_RIGHT_COL = FIELD4_LEFT_COL + cubeLength - 1

    val FIELD5_TOP_ROW = 2 * cubeLength
    val FIELD5_BOTTOM_ROW = FIELD5_TOP_ROW + cubeLength - 1
    val FIELD5_LEFT_COL = 2 * cubeLength
    val FIELD5_RIGHT_COL = FIELD5_LEFT_COL + cubeLength - 1

    val FIELD6_TOP_ROW = 2 * cubeLength
    val FIELD6_BOTTOM_ROW = FIELD6_TOP_ROW + cubeLength - 1
    val FIELD6_LEFT_COL = 3 * cubeLength
    val FIELD6_RIGHT_COL = FIELD6_LEFT_COL + cubeLength - 1

    override fun nextFieldAhead(map: List<String>, pos: Position): Position {
        // edge between 1 and 2
        // top 1 north -> top 2 south
        if (pos.row == FIELD1_TOP_ROW &&
            pos.col in FIELD1_LEFT_COL .. FIELD1_RIGHT_COL &&
            pos.heading == Heading.NORTH) {
            val x = pos.col - FIELD1_LEFT_COL
            val newPos = Position(FIELD2_TOP_ROW, FIELD2_RIGHT_COL - x, Heading.SOUTH)
            println("hitting top end of field 1, looking from $pos to $newPos in top edge of field 2")
            return newPos
        }
        // top 2 north -> top 1 south
        if (pos.row == FIELD2_TOP_ROW &&
            pos.col in FIELD2_LEFT_COL .. FIELD2_RIGHT_COL &&
            pos.heading == Heading.NORTH) {
            val x = pos.col - FIELD2_LEFT_COL
            val newPos = Position(FIELD1_TOP_ROW, FIELD1_RIGHT_COL - x, Heading.SOUTH)
            println("hitting top end of field 2, looking from $pos to $newPos in top edge of field 1")
            return newPos
        }

        // edge between 1 and 3
        // top 3 north -> left 1 east
        if (pos.row == FIELD3_TOP_ROW &&
            pos.col in FIELD3_LEFT_COL ..FIELD3_RIGHT_COL &&
            pos.heading == Heading.NORTH) {
            val x = pos.col - FIELD3_LEFT_COL
            val newPos = Position(FIELD1_TOP_ROW + x, FIELD1_LEFT_COL, Heading.EAST)
            println("hitting top end of field 3, looking from $pos to $newPos in left edge of field 1")
            return newPos
        }
        // left 1 west -> top 3 south
        if (pos.row in FIELD1_TOP_ROW .. FIELD1_BOTTOM_ROW &&
            pos.col == FIELD1_LEFT_COL &&
            pos.heading == Heading.WEST) {
            val x = pos.row - FIELD1_TOP_ROW
            val newPos = Position(FIELD3_TOP_ROW, FIELD3_LEFT_COL + x, Heading.SOUTH)
            println("hitting left edge of field 1, looking from $pos to $newPos in top edge of field 3")
            return newPos
        }

        // edge between 1 and 6
        // right 1 east -> right 6 west
        if (pos.row in FIELD1_TOP_ROW .. FIELD1_BOTTOM_ROW &&
            pos.col == FIELD1_RIGHT_COL &&
            pos.heading == Heading.EAST) {
            val x = pos.row - FIELD1_TOP_ROW
            val newPos = Position(FIELD6_BOTTOM_ROW - x, FIELD6_RIGHT_COL, Heading.WEST)
            println("hitting right edge of field 1, looking from $pos to $newPos in right edge of field 6")
            return newPos
        }
        // right 6 east -> right 1 west
        if (pos.row in FIELD6_TOP_ROW .. FIELD6_BOTTOM_ROW &&
            pos.col == FIELD6_RIGHT_COL &&
            pos.heading == Heading.EAST) {
            val x = pos.row - FIELD6_TOP_ROW
            val newPos = Position(FIELD1_BOTTOM_ROW - x, FIELD1_RIGHT_COL, Heading.WEST)
            println("hitting right edge of field 6, looking from $pos to $newPos in right edge of field 1")
            return newPos
        }

        // edge between 4 and 6
        // right 4 east -> top 6 south
        if (pos.row in FIELD4_TOP_ROW..FIELD4_BOTTOM_ROW &&
            pos.col == FIELD4_RIGHT_COL &&
            pos.heading == Heading.EAST) {
            val x = pos.row - FIELD4_TOP_ROW
            val newPos = Position(FIELD6_TOP_ROW, FIELD6_RIGHT_COL - x, Heading.SOUTH)
            println("hitting right edge of field 4, looking from $pos to $newPos in top edge of field 6")
            return newPos
        }
        // top 6 north -> right 4 west
        if (pos.row == FIELD6_TOP_ROW &&
            pos.col in FIELD6_LEFT_COL..FIELD6_RIGHT_COL &&
            pos.heading == Heading.NORTH) {
            val x = pos.col - FIELD6_LEFT_COL
            val newPos = Position(FIELD4_BOTTOM_ROW - x, FIELD4_RIGHT_COL, Heading.WEST)
            println("hitting right edge of field 4, looking from $pos to $newPos in top edge of field 6")
            return newPos
        }

        // edge between 3 and 5
        // bottom 3 south -> left 5 east
        if (pos.row == FIELD3_BOTTOM_ROW &&
            pos.col in FIELD3_LEFT_COL..FIELD3_RIGHT_COL &&
            pos.heading == Heading.SOUTH) {
            val x = pos.col - FIELD3_LEFT_COL
            val newPos = Position(FIELD5_BOTTOM_ROW - x, FIELD5_LEFT_COL, Heading.EAST)
            println("hitting bottom edge of field 3, looking from $pos to $newPos in left edge of field 5")
            return newPos
        }
        // left 5 west -> bottom 3 north
        if (pos.row in FIELD5_TOP_ROW..FIELD5_BOTTOM_ROW &&
            pos.col == FIELD5_LEFT_COL &&
            pos.heading == Heading.WEST) {
            val x = pos.row - FIELD5_TOP_ROW
            val newPos = Position(FIELD3_BOTTOM_ROW, FIELD3_RIGHT_COL - x, Heading.NORTH)
            println("hitting left edge of field 5, looking from $pos to $newPos in bottom row of field 3")
            return newPos
        }

        // edge between 2 and 5
        // bottom 2 south -> bottom 5 north
        if (pos.row == FIELD2_BOTTOM_ROW &&
            pos.col in FIELD2_LEFT_COL..FIELD2_RIGHT_COL &&
            pos.heading == Heading.SOUTH) {
            val x = pos.col - FIELD2_LEFT_COL
            val newPos = Position(FIELD5_BOTTOM_ROW, FIELD5_RIGHT_COL - x, Heading.NORTH)
            println("hitting bottom edge of field 2, looking from $pos to $newPos in bottom edge of field 5")
            return newPos
        }
        // bottom 5 south -> bottom 2 north
        if (pos.row == FIELD5_BOTTOM_ROW &&
            pos.col in FIELD5_LEFT_COL..FIELD5_RIGHT_COL &&
            pos.heading == Heading.SOUTH) {
            val x = pos.col - FIELD5_LEFT_COL
            val newPos = Position(FIELD2_BOTTOM_ROW, FIELD2_RIGHT_COL - x, Heading.NORTH)
            println("hitting bottom edge of field 5, looking from $pos to $newPos in bottom edge of field 2")
            return newPos
        }

        // edge between 2 and 6
        // left 2 west -> bottom 6 north
        if (pos.row in FIELD2_TOP_ROW..FIELD2_BOTTOM_ROW &&
            pos.col == FIELD2_LEFT_COL &&
            pos.heading == Heading.WEST) {
            val x = pos.row - FIELD2_TOP_ROW
            val newPos = Position(FIELD6_BOTTOM_ROW, FIELD6_RIGHT_COL - x, Heading.NORTH)
            println("hitting left edge of field 2, looking from $pos to $newPos in bottom row of field 6")
            return newPos
        }
        // bottom 6 south -> left 2 east
        if (pos.row == FIELD6_BOTTOM_ROW &&
            pos.col in FIELD6_LEFT_COL..FIELD6_RIGHT_COL &&
            pos.heading == Heading.SOUTH) {
            val x = pos.col - FIELD6_LEFT_COL
            val newPos = Position(FIELD2_BOTTOM_ROW - x, FIELD2_LEFT_COL, Heading.EAST)
            println("hitting bottom edge of field 6, looking from $pos to $newPos in left edge of field 2")
            return newPos
        }

        // field ahead is normal field on map, no wrapping around
        return pos.stepForward()
    }
}

class Part2Input(cubeLength: Int) : Solver() {

    // fields are cut/numbered different from example
    //    11112222
    //    11112222
    //    11112222
    //    11112222
    //    3333
    //    3333
    //    3333
    //    3333
    //44445555
    //44445555
    //44445555
    //44445555
    //6666
    //6666
    //6666
    //6666

    val FIELD1_TOP_ROW = 0
    val FIELD1_BOTTOM_ROW = FIELD1_TOP_ROW + cubeLength - 1
    val FIELD1_LEFT_COL = cubeLength
    val FIELD1_RIGHT_COL = FIELD1_LEFT_COL + cubeLength - 1

    val FIELD2_TOP_ROW = 0
    val FIELD2_BOTTOM_ROW = FIELD2_TOP_ROW + cubeLength - 1
    val FIELD2_LEFT_COL = 2 * cubeLength
    val FIELD2_RIGHT_COL = FIELD2_LEFT_COL + cubeLength - 1

    val FIELD3_TOP_ROW = cubeLength
    val FIELD3_BOTTOM_ROW = FIELD3_TOP_ROW + cubeLength - 1
    val FIELD3_LEFT_COL = cubeLength
    val FIELD3_RIGHT_COL = FIELD3_LEFT_COL + cubeLength - 1

    val FIELD4_TOP_ROW = 2 * cubeLength
    val FIELD4_BOTTOM_ROW = FIELD4_TOP_ROW + cubeLength - 1
    val FIELD4_LEFT_COL = 0
    val FIELD4_RIGHT_COL = FIELD4_LEFT_COL + cubeLength - 1

    val FIELD5_TOP_ROW = 2 * cubeLength
    val FIELD5_BOTTOM_ROW = FIELD5_TOP_ROW + cubeLength - 1
    val FIELD5_LEFT_COL = cubeLength
    val FIELD5_RIGHT_COL = FIELD5_LEFT_COL + cubeLength - 1

    val FIELD6_TOP_ROW = 3 * cubeLength
    val FIELD6_BOTTOM_ROW = FIELD6_TOP_ROW + cubeLength - 1
    val FIELD6_LEFT_COL = 0
    val FIELD6_RIGHT_COL = FIELD6_LEFT_COL + cubeLength - 1

    override fun nextFieldAhead(map: List<String>, pos: Position): Position {
        // edge between 1 and 6
        // top 1 north -> left 6 east
        if (pos.row == FIELD1_TOP_ROW &&
            pos.col in FIELD1_LEFT_COL .. FIELD1_RIGHT_COL &&
            pos.heading == Heading.NORTH) {
            val x = pos.col - FIELD1_LEFT_COL
            val newPos = Position(FIELD6_TOP_ROW + x, FIELD6_LEFT_COL, Heading.EAST)
            println("hitting top end of field 1, looking from $pos to $newPos in left edge of field 6")
            return newPos
        }
        // left 6 west -> top 1 south
        if (pos.row in FIELD6_TOP_ROW..FIELD6_BOTTOM_ROW &&
            pos.col == FIELD6_LEFT_COL &&
            pos.heading == Heading.WEST) {
            val x = pos.row - FIELD6_TOP_ROW
            val newPos = Position(FIELD1_TOP_ROW, FIELD1_LEFT_COL + x, Heading.SOUTH)
            println("hitting left edge of field 6, looking from $pos to $newPos in top edge of field 1")
            return newPos
        }

        // edge between 1 and 4
        // left 1 west -> left 4 east
        if (pos.row in FIELD1_TOP_ROW .. FIELD1_BOTTOM_ROW &&
            pos.col == FIELD1_LEFT_COL &&
            pos.heading == Heading.WEST) {
            val x = pos.row - FIELD1_TOP_ROW
            val newPos = Position(FIELD4_BOTTOM_ROW - x, FIELD4_LEFT_COL, Heading.EAST)
            println("hitting left edge of field 1, looking from $pos to $newPos in left edge of field 4")
            return newPos
        }
        // left 4 west -> left 1 east
        if (pos.row in FIELD4_TOP_ROW .. FIELD4_BOTTOM_ROW &&
            pos.col == FIELD4_LEFT_COL &&
            pos.heading == Heading.WEST) {
            val x = pos.row - FIELD4_TOP_ROW
            val newPos = Position(FIELD1_BOTTOM_ROW - x, FIELD1_LEFT_COL, Heading.EAST)
            println("hitting left edge of field 4, looking from $pos to $newPos in left edge of field 1")
            return newPos
        }

        // edge between 3 and 4
        // left 3 west -> top 4 south
        if (pos.row in FIELD3_TOP_ROW .. FIELD3_BOTTOM_ROW &&
            pos.col == FIELD3_LEFT_COL &&
            pos.heading == Heading.WEST) {
            val x = pos.row - FIELD3_TOP_ROW
            val newPos = Position(FIELD4_TOP_ROW, FIELD4_LEFT_COL + x, Heading.SOUTH)
            println("hitting left edge of field 3, looking from $pos to $newPos in top edge of field 4")
            return newPos
        }
        // top 4 north -> left 3 east
        if (pos.row == FIELD4_TOP_ROW &&
            pos.col in FIELD4_LEFT_COL..FIELD4_RIGHT_COL &&
            pos.heading == Heading.NORTH) {
            val x = pos.col - FIELD4_LEFT_COL
            val newPos = Position(FIELD3_TOP_ROW + x, FIELD3_LEFT_COL, Heading.EAST)
            println("hitting top edge of field 4, looking from $pos to $newPos in left edge of field 3")
            return newPos
        }

        // edge between 2 and 6
        // top 2 north -> bottom 6 north
        if (pos.row == FIELD2_TOP_ROW &&
            pos.col in FIELD2_LEFT_COL .. FIELD2_RIGHT_COL &&
            pos.heading == Heading.NORTH) {
            val x = pos.col - FIELD2_LEFT_COL
            val newPos = Position(FIELD6_BOTTOM_ROW, FIELD6_LEFT_COL + x, Heading.NORTH)
            println("hitting top end of field 2, looking from $pos to $newPos in bottom edge of field 6")
            return newPos
        }
        // bottom 6 south -> top 2 south
        if (pos.row == FIELD6_BOTTOM_ROW &&
            pos.col in FIELD6_LEFT_COL .. FIELD6_RIGHT_COL &&
            pos.heading == Heading.SOUTH) {
            val x = pos.col - FIELD6_LEFT_COL
            val newPos = Position(FIELD2_TOP_ROW, FIELD2_LEFT_COL + x, Heading.SOUTH)
            println("hitting bottom end of field 6, looking from $pos to $newPos in top edge of field 2")
            return newPos
        }

        // edge between 2 and 5
        // right 2 east -> right 5 west
        if (pos.row in FIELD2_TOP_ROW..FIELD2_BOTTOM_ROW &&
            pos.col == FIELD2_RIGHT_COL &&
            pos.heading == Heading.EAST) {
            val x = pos.row - FIELD2_TOP_ROW
            val newPos = Position(FIELD5_BOTTOM_ROW - x, FIELD5_RIGHT_COL, Heading.WEST)
            println("hitting right edge of field 2, looking from $pos to $newPos in right edge of field 5")
            return newPos
        }
        // right 5 east -> right 2 west
        if (pos.row in FIELD5_TOP_ROW..FIELD5_BOTTOM_ROW &&
            pos.col == FIELD5_RIGHT_COL &&
            pos.heading == Heading.EAST) {
            val x = pos.row - FIELD5_TOP_ROW
            val newPos = Position(FIELD2_BOTTOM_ROW - x, FIELD2_RIGHT_COL, Heading.WEST)
            println("hitting right edge of field 5, looking from $pos to $newPos in right edge of field 2")
            return newPos
        }

        // edge between 2 and 3
        // bottom 2 south -> right 3 west
        if (pos.row == FIELD2_BOTTOM_ROW &&
            pos.col in FIELD2_LEFT_COL..FIELD2_RIGHT_COL &&
            pos.heading == Heading.SOUTH) {
            val x = pos.col - FIELD2_LEFT_COL
            val newPos = Position(FIELD3_TOP_ROW + x, FIELD3_RIGHT_COL, Heading.WEST)
            println("hitting bottom edge of field 2, looking from $pos to $newPos in right edge of field 3")
            return newPos
        }
        // right 3 east -> bottom 2 north
        if (pos.row in FIELD3_TOP_ROW..FIELD3_BOTTOM_ROW &&
            pos.col == FIELD3_RIGHT_COL &&
            pos.heading == Heading.EAST) {
            val x = pos.row - FIELD3_TOP_ROW
            val newPos = Position(FIELD2_BOTTOM_ROW, FIELD2_LEFT_COL + x, Heading.NORTH)
            println("hitting right edge of field 3, looking from $pos to $newPos in bottom edge of field 2")
            return newPos
        }

        // edge between 5 and 6
        // bottom 5 south -> right 6 west
        if (pos.row == FIELD5_BOTTOM_ROW &&
            pos.col in FIELD5_LEFT_COL..FIELD5_RIGHT_COL &&
            pos.heading == Heading.SOUTH) {
            val x = pos.col - FIELD5_LEFT_COL
            val newPos = Position(FIELD6_TOP_ROW + x, FIELD6_RIGHT_COL, Heading.WEST)
            println("hitting bottom edge of field 5, looking from $pos to $newPos in right edge of field 6")
            return newPos
        }
        // right 6 east -> bottom 5 north
        if (pos.row in FIELD6_TOP_ROW..FIELD6_BOTTOM_ROW &&
            pos.col == FIELD6_RIGHT_COL &&
            pos.heading == Heading.EAST) {
            val x = pos.row - FIELD6_TOP_ROW
            val newPos = Position(FIELD5_BOTTOM_ROW, FIELD5_LEFT_COL + x, Heading.NORTH)
            println("hitting right edge of field 6, looking from $pos to $newPos in bottom edge of field 5")
            return newPos
        }

        // field ahead is normal field on map, no wrapping around
        return pos.stepForward()
    }
}

data class Position(var row: Int, var col: Int, var heading: Heading) {
    fun stepForward(): Position {
        return when (heading) {
            Heading.NORTH -> this.copy(row = row - 1)
            Heading.SOUTH -> this.copy(row = row + 1)
            Heading.WEST -> this.copy(col = col - 1)
            Heading.EAST -> this.copy(col = col + 1)
        }
    }

    fun turn(turn: Char) {
        heading = when (Pair(heading, turn)) {
            Pair(Heading.NORTH, 'R') -> Heading.EAST
            Pair(Heading.EAST, 'R') -> Heading.SOUTH
            Pair(Heading.SOUTH, 'R') -> Heading.WEST
            Pair(Heading.WEST, 'R') -> Heading.NORTH
            Pair(Heading.NORTH, 'L') -> Heading.WEST
            Pair(Heading.WEST, 'L') -> Heading.SOUTH
            Pair(Heading.SOUTH, 'L') -> Heading.EAST
            Pair(Heading.EAST, 'L') -> Heading.NORTH
            else -> throw IllegalArgumentException("computers says no: illegal heading/turn $heading $turn")
        }
    }

    override fun toString(): String {
        return "$row|$col|$heading"
    }
}

enum class Heading {
    NORTH, EAST, SOUTH, WEST;

    // for calculating the final score
    fun toInt(): Int {
        return when (this) {
            EAST -> 0
            SOUTH -> 1
            WEST -> 2
            NORTH -> 3
        }
    }

    // for printing the path
    fun toChar(): Char {
        return when (this) {
            EAST -> '>'
            SOUTH -> 'V'
            WEST -> '<'
            NORTH -> '^'
        }
    }
}

fun print(map:List<String>, path: List<Position>) {
    for (row in map.indices) {
        for (col in map[row].indices) {
            val stepInPath = path.firstOrNull { it.row == row && it.col == col }
            if (stepInPath != null ) {
                print(stepInPath.heading.toChar())
            } else {
                print(map[row][col])
            }
        }
        println()
    }
}

fun readLineChunks(input: String): List<List<String>> {
    return input.lines().fold(mutableListOf<MutableList<String>>()) { list, line ->
        if (line.isEmpty()) {
            list.add(mutableListOf())
        } else {
            if (list.isEmpty()) {
                list.add(mutableListOf())
            }
            list.last().add(line)
        }
        list
    }
}