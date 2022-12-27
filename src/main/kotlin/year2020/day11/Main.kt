package year2020.day11

fun main() {
    solve(SAMPLE, ::emptyOccupiedSeatPart1, ::occupyEmptySeatPart1).let { println("part1: $it") }
    solve(INPUT, ::emptyOccupiedSeatPart1, ::occupyEmptySeatPart1).let { println("part1: $it") }

    solve(SAMPLE, ::emptyOccupiedSeatPart2, ::occupyEmptySeatPart2).let { println("part2: $it") }
    solve(INPUT, ::emptyOccupiedSeatPart2, ::occupyEmptySeatPart2).let { println("part2: $it") }
}

fun emptyOccupiedSeatPart1(seats: Array<CharArray>, pos: Position): Boolean =
    adjacentNeighbors(seats, pos).count { it == '#' } >= 4

fun occupyEmptySeatPart1(seats: Array<CharArray>, pos: Position): Boolean =
    adjacentNeighbors(seats, pos).none { it == '#' }

fun emptyOccupiedSeatPart2(seats: Array<CharArray>, pos: Position): Boolean =
    neighborsInSight(seats, pos).count { it == '#' } >= 5

fun occupyEmptySeatPart2(seats: Array<CharArray>, pos: Position): Boolean =
    neighborsInSight(seats, pos).none { it == '#' }

fun solve(
    input: String,
    emptyOccupiedSeat: (Array<CharArray>, Position) -> Boolean,
    occupyEmptySeat: (Array<CharArray>, Position) -> Boolean
): Int {
    var seats = parse(input)
    // print(seats)

    while (true) {
        val newSeats = tick(seats, emptyOccupiedSeat, occupyEmptySeat)

        if (newSeats.contentDeepEquals(seats)) {
            // print(newSeats)
            return newSeats.sumOf { chars -> chars.count { c -> c == '#' } }
        }

        // print(newSeats)
        seats = newSeats
    }
}

fun tick(
    seats: Array<CharArray>,
    emptyOccupiedSeat: (Array<CharArray>, Position) -> Boolean,
    occupyEmptySeat: (Array<CharArray>, Position) -> Boolean
): Array<CharArray> {
    val rows = seats.size
    val cols = seats.first().size
    val newSeats = Array(rows) { CharArray(cols) }

    seats.withIndex().forEach { (row, line) ->
        line.withIndex().forEach { (col, c) ->
            newSeats[row][col] =
                if (c == '#') {
                    if (emptyOccupiedSeat(seats, Position(row, col))) 'L' else '#'
                } else if (c == 'L') {
                    if (occupyEmptySeat(seats, Position(row, col))) '#' else 'L'
                } else
                    c
        }
    }
    return newSeats
}

fun print(seats: Array<CharArray>) {
    seats.forEach { println(it.joinToString("")) }
    println()
}

fun adjacentNeighbors(seats: Array<CharArray>, pos: Position): List<Char> {
    val rows = seats.indices
    val cols = seats.first().indices

    return (-1..1).flatMap { dRow ->
        (-1..1).mapNotNull { dCol ->
            if (dRow == 0 && dCol == 0) // exclude seat itself
                null
            else {
                val nRow = pos.row + dRow
                val nCol = pos.col + dCol
                if (nRow in rows && nCol in cols) {
                    seats[nRow][nCol]
                } else
                    null
            }
        }
    }
}

fun neighborsInSight(seats: Array<CharArray>, pos: Position): List<Char> {
    return listOf(
        Position(-1, 0), // north
        Position(-1, 1), // north east
        Position(0, 1),  // east
        Position(1, 1),  // south east
        Position(1, 0),  // south
        Position(1, -1), // south west
        Position(0, -1),  // west
        Position(-1, -1), // north west
    ).mapNotNull { delta ->
        getLineOfSight(seats, pos, delta)
            .map { seats[it.row][it.col] }.firstOrNull { it != '.' }
    }
}

fun getLineOfSight(seats: Array<CharArray>, pos: Position, delta: Position): List<Position> {
    return generateSequence(pos) { it + delta }
        .drop(1) // drop field itself
        .takeWhile { it.row in seats.indices && it.col in seats.first().indices }
        .toList()
}

fun parse(input: String): Array<CharArray> {
    val rows = input.lines().size
    val cols = input.lines().first().length
    val seats = Array(rows) { CharArray(cols) }

    input.lines().withIndex().forEach { (row, line) ->
        seats[row] = line.toCharArray()
    }
    return seats
}

data class Position(val row: Int, val col: Int)

infix operator fun Position.plus(other: Position) = Position(this.row + other.row, this.col + other.col)