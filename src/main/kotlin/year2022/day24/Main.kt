package year2022.day24

import java.util.PriorityQueue

fun main() {
    part1(SAMPLE)
    part1(SAMPLE2)
    part1(INPUT)
}

fun part1(input: String) {
    val blizzards = parseBlizzards(input)
    val maxRow = input.lines().lastIndex
    val maxCol = input.lines().first().lastIndex

    val start = Point(0, 1)
    val finish = Point(maxRow, maxCol - 1)
    val board = Board(maxRow, maxCol, start, finish)

    println("initial state:")
    board.print(blizzards, start)

    val time = solve(board, blizzards, start, finish, 0)
    println("part1: $time")
    val time2 = solve(board, blizzards, finish, start, time)
    val time3 = solve(board, blizzards, start, finish, time2)
    println("part2: $time3")
}

fun solve(board: Board, blizzards: List<Blizzard>, start: Point, finish: Point, startTime: Int): Int {
    // this is basically an iterative BFS
    val seen = mutableSetOf<State>()
    // because the edges are moving (time dependent) we also have to store the time in state
    val q = PriorityQueue(compareBy(State::time))
    q.add(State(startTime, start))
    while (q.isNotEmpty()) {
        val (time, currentPos) = q.poll()
        if (currentPos == finish) {
            println("Minute $time:")
            board.print(moveBlizzards(board, blizzards, time), currentPos)
            return time
        }

        val movedBlizzards = moveBlizzards(board, blizzards, time + 1)
        findFreeFields(board, movedBlizzards, currentPos)
            .map { pos -> State(time + 1, pos) }.forEach { state ->
                seen.add(state) && q.add(state) // add to queue only if not already seen
            }
    }
    throw IllegalStateException("computer says no!")
}

fun findFreeFields(board: Board, blizzards: List<Blizzard>, p: Point): List<Point> {
    val blizzardPoints = blizzards.map { b -> b.point }
    return listOf(
        Point(0, 0), // staying in place is a valid option!
        Point(1, 0), Point(-1, 0),
        Point(0, -1), Point(0, 1)
    ).map { (dRow, dCol) ->
        Point(p.row + dRow, p.col + dCol)
    }
        .filterNot { blizzardPoints.contains(it) }
        .filter { it == board.start || it == board.finish ||
                    (it.row in board.innerRows && it.col in board.innerCols)
        }
}

fun parseBlizzards(input: String): List<Blizzard> {
    return input.lines().withIndex().flatMap { (row, line) ->
        line.withIndex().mapNotNull { (col, c) ->
            when (c) {
                '>', 'v', '<', '^' -> Blizzard(Point(row, col), c)
                else -> null
            }
        }
    }
}

// take given blizzards and move each one 'steps' steps ahead, while obeying the boundaries
fun moveBlizzards(board: Board, blizzards: List<Blizzard>, steps: Int): List<Blizzard> {
    return blizzards.map { b ->
        // like a coordinate transformation: (y-y0) = (x-x0) + Z(t)
        val newRow = (b.point.row - board.rowOffset + (steps * b.dirAsDelta().row)).mod(board.height) + board.rowOffset
        val newCol = (b.point.col - board.colOffset + (steps * b.dirAsDelta().col)).mod(board.width) + board.colOffset

        Blizzard(Point(newRow, newCol), b.dir)
    }
}

data class Blizzard(val point: Point, val dir: Char) {
    fun dirAsDelta(): Point {
        return when (dir) {
            '>' -> Point(0, 1)
            'v' -> Point(1, 0)
            '<' -> Point(0, -1)
            '^' -> Point(-1, 0)
            else -> throw IllegalStateException("computer says no to this dir: $dir")
        }
    }
}

data class Board(val maxRow: Int, val maxCol: Int, val start: Point, val finish: Point) {
    val rowOffset = 1
    val colOffset = 1
    val width = maxCol - 1
    val height = maxRow - 1
    val innerRows = (1 until maxRow)
    val innerCols = (1 until maxCol)

    fun print(blizzards: List<Blizzard>, exp: Point) {
        for (r in 0..maxRow) {
            for (c in 0..maxCol) {
                if (r == exp.row && c == exp.col) {
                    print('E')
                } else if (r == start.row && c == start.col) {
                    print('.')
                } else if (r == finish.row && c == finish.col) {
                    print('.')
                } else if (r == 0 || r == maxRow || c == 0 || c == maxCol) {
                    print('#')
                } else {
                    val blizzardsInField = blizzards.filter { it.point.row == r && it.point.col == c }
                    if (blizzardsInField.size > 1) {
                        print(blizzardsInField.size)
                    } else if (blizzardsInField.size == 1) {
                        print(blizzardsInField.first().dir)
                    } else {
                        print('.')
                    }
                }
            }
            println()
        }
    }
}

data class Point(val row: Int, val col: Int)
data class State(val time: Int, val position: Point)