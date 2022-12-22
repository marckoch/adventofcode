package year2022.day22

fun main() {
    part1(SAMPLE)
    part1(INPUT)
}

fun part1(input: String) {
    val (map, instr) = readLineChunks(input).let { (map, path) -> Pair(padMap(map), parseSteps(path.first())) }
    //map.forEach { println(it) }

    val finalPos = instr.fold(startPosition(map)) {
        acc, s -> executeOneInstruction(map, acc, s)
    }
    println("final position: $finalPos")

    val result1 = 1000 * (finalPos.row + 1) + 4 * (finalPos.col + 1) + headingToInt(finalPos.heading)
    println("part1: $result1")
}

// turn L or R or go forward x steps
// returns new position
fun executeOneInstruction(map: List<String>, pos: Position, instr: String): Position {
    return if (instr == "L" || instr == "R") {
        pos.turn(instr.first())
        println("turning $instr to $pos")
        pos
    } else {
        println("moving $instr steps")
        val newPos = moveForward(map, pos, instr.toInt())
        println("stopped moving at $pos")
        newPos
    }
}

// we pad each line to the right with blanks, that will wrapping around easier
fun padMap(map: List<String>): List<String> {
    val maxLength = map.map { it.length }.distinct().max()
    return map.map { it.padEnd(maxLength, ' ') }
}

fun headingToInt(heading: Heading): Int {
    return when (heading) {
        Heading.EAST -> 0
        Heading.SOUTH -> 1
        Heading.WEST -> 2
        Heading.NORTH -> 3
    }
}

fun startPosition(map: List<String>): Position {
    val startCol = map.first().indexOfFirst { it == '.' }
    return Position(0, startCol, Heading.EAST)
}

fun parseSteps(path: String): List<String> {
    return """\d+|\D+""".toRegex().findAll(path).map { it.groupValues.first() }.toList()
}

fun moveForward(map: List<String>, pos: Position, steps: Int): Position {
    var currentPos = pos
    repeat (steps) {
        val fieldAhead = nextFieldAhead(map, currentPos)
        val f = map[fieldAhead.row][fieldAhead.col]
        if (f == '.') {
            currentPos = fieldAhead
        } else if (f == '#') {
            println("hitting wall! aborting move after $it steps")
            return currentPos
        }
    }
    return currentPos
}

fun nextFieldAhead(map: List<String>, pos: Position): Position {
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