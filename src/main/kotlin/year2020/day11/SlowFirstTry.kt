package year2020.day11.slow

import year2020.day11.INPUT
import year2020.day11.SAMPLE

// this solution is very slow, I tried working with a List<Seat>
// unfortunately this complicates (and slows down) simple tasks (like finding neighbors, etc.)
// I'll keep it as reference
fun main() {
    part1(SAMPLE)
    part1(INPUT)
}

fun part1(input: String) {
    var seats = parse(input)
    println(seats)

    val maxRow = input.lines().lastIndex
    val maxCol = input.lines().first().lastIndex

    print(seats, maxRow, maxCol)

    while (true) {
        val newSeats = tick(seats, maxRow, maxCol)
        if (newSeats == seats) {
            println("finished!")
            print(newSeats, maxRow, maxCol)
            newSeats.count { seat -> seat.occupied }.let { count -> println("part1: $count") }
            return
        }
        //print(newSeats, maxRow, maxCol)
        seats = newSeats
    }
}

fun tick(seats: List<Seat>, maxRow: Int, maxCol: Int): List<Seat> {
    return seats.map { seat ->
        if (seat.occupied) {
            if (neighbors(seat, seats, maxRow, maxCol).count { it.occupied } >= 4) {
                seat.copy(occupied = false)
            } else seat
        } else {
            if (neighbors(seat, seats, maxRow, maxCol).none { it.occupied }) {
                seat.copy(occupied = true)
            } else seat
        }
    }
}

fun print(seats: List<Seat>, maxRow: Int, maxCol: Int) {
    for (row in 0..maxRow) {
        for (col in 0..maxCol) {
            val seat = seats.find { it.row == row && it.col == col }
            if (seat != null) {
                if (seat.occupied) print('#') else print('L')
            } else
                print('.')
        }
        println()
    }
    println()
}

fun neighbors(seat: Seat, seats: List<Seat>, maxRow: Int, maxCol: Int): List<Seat> {
    val positionsOfNeighbors = (-1..1).flatMap { dRow ->
        (-1..1).mapNotNull { dCol ->
            if (dRow == 0 && dCol == 0) // exclude seat itself
                null
            else  {
                val row = seat.row + dRow
                val col = seat.col + dCol
                if (row in (0..maxRow) && col in (0..maxCol)) {
                    Pair(row, col)
                } else
                    null
            }
        }
    }
    return seats.filter { Pair(it.row, it.col) in positionsOfNeighbors }
}

fun parse(input: String): List<Seat> {
    return input.lines().withIndex().flatMap { (row, line) ->
        line.withIndex().mapNotNull { (col, c) ->
            if (c == 'L')
                Seat(row, col)
            else
                null
        }
    }
}

data class Seat(val row: Int, val col: Int, val occupied: Boolean = false)