package year2022.day23

fun main() {
    part1(SAMPLE_SMALL)
    part1(SAMPLE)
    part1(INPUT)
}

// cycle through list
private fun dircycle() = iterator { while (true) yieldAll(Direction.values().iterator()) }

fun part1(input: String) {
    var elves = input.lines().withIndex().flatMap { (rowIndex, line) ->
        line.withIndex().mapNotNull { (colIndex, c) ->
            if (c == '#') Elf(rowIndex, colIndex) else null
        }
    }
    //elves.let { println(it) }

    //print(elves)

    repeat(10) {
        val moves = dircycle().asSequence().drop(it).take(4).toList()
        elves = move(elves, moves)
        //print(elves)
    }
    countEmptyTiles(elves).let { println("part1: $it") }
}

fun move(elves: List<Elf>, directions: List<Direction>): List<Elf> {
    //println(directions)
    // propose moves
    val proposedMoves = elves.map { elf ->
        if (neighbors(elf, elves).isNotEmpty()) {
            val dir = directions.firstOrNull { d -> neighbors(elf, d, elves).isEmpty() }
            if (dir != null) {
                Pair(elf, elf.move(dir))
            } else {
                Pair(elf, elf)// elf is blocked -> does not move
            }
        } else
            Pair(elf, elf)// elf has no neighbors -> does not move
    }
    //println("proposedMoves=$proposedMoves")

    // move
    val newPositions = proposedMoves.map { (oldPos, newPos) ->
        if (proposedMoves.count { it.second == newPos } == 1) {
            newPos
        } else
            oldPos
    }
    //println("newPositions=$newPositions")
    return newPositions
}

fun neighbors(elf: Elf, elves: List<Elf>): List<Elf> {
    return Direction.values().flatMap { d -> neighbors(elf, d, elves) }.distinct()
}

fun neighbors(elf: Elf, dir: Direction, elves: List<Elf>): List<Elf> {
    return when (dir) {
        Direction.NORTH -> elves.filter { it.row == elf.row - 1 && it.col in (elf.col - 1..elf.col + 1) }
        Direction.SOUTH -> elves.filter { it.row == elf.row + 1 && it.col in (elf.col - 1..elf.col + 1) }
        Direction.WEST -> elves.filter { it.col == elf.col - 1 && it.row in (elf.row - 1..elf.row + 1) }
        Direction.EAST -> elves.filter { it.col == elf.col + 1 && it.row in (elf.row - 1..elf.row + 1) }
    }
}

fun countEmptyTiles(elves: List<Elf>): Int {
    val minRow = elves.minOf { it.row }
    val maxRow = elves.maxOf { it.row }
    val minCol = elves.minOf { it.col }
    val maxCol = elves.maxOf { it.col }
    var count = 0
    for (row in minRow ..maxRow) {
        for (col in minCol ..maxCol) {
            if (elves.none { it.row == row && it.col == col })
                count += 1
        }
    }
    return count
}
fun print(elves: List<Elf>) {
    val minRow = elves.minOf { it.row }
    val maxRow = elves.maxOf { it.row }
    val minCol = elves.minOf { it.col }
    val maxCol = elves.maxOf { it.col }
    for (row in minRow - 1..maxRow + 1) {
        for (col in minCol - 1..maxCol + 1) {
            if (elves.any { it.row == row && it.col == col })
                print("#")
            else
                print(".")
        }
        println()
    }
}

data class Elf(val row: Int, val col: Int) {
    fun move(d: Direction): Elf {
        return when (d) {
            Direction.NORTH -> this.copy(row = row - 1)
            Direction.SOUTH -> this.copy(row = row + 1)
            Direction.WEST -> this.copy(col = col - 1)
            Direction.EAST -> this.copy(col = col + 1)
        }
    }

    override fun toString(): String {
        return "$row|$col"
    }
}

// order is order we cycle through!
enum class Direction { NORTH, SOUTH, WEST, EAST }
