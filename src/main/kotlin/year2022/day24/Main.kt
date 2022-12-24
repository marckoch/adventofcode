package year2022.day24

import java.util.PriorityQueue

fun main() {
    part1(SAMPLE)
    part1(SAMPLE2)
//    part1(INPUT)
}

fun part1(input: String) {
    val blizzards = parseBlizzards(input)
    val maxRow = input.lines().lastIndex
    val maxCol = input.lines().first().lastIndex

    val start = Point(0, 1)
    val finish = Point(maxRow, maxCol - 1)
    val board = Board(maxRow, maxCol, start, finish)

    println("initial state:")
    print(board, blizzards, start)

    val q = PriorityQueue<Pair<Point, List<Blizzard>>> { p1, p2 -> p2.first.row - p1.first.row + p2.first.col - p1.first.col }

    var b = blizzards
    var currentPos = start
    var round = 0
    while (true) {
        b = moveBlizzards(board, b)

        val free = findFreeFields(board, b, currentPos)
        if (free.contains(finish)) {
            println("Minute ${round++ + 1}:")
            currentPos = finish
            print(board, b, currentPos)
            break
        }
        free.map { Pair(it, b) }.forEach { q.add(it) }
        if (free.isNotEmpty()) {
            val (p, blizz) = q.poll()
            currentPos = p
            b = blizz
        }

        println("Minute ${round++ + 1}:")
        //print(board, b, currentPos)
    }
}

fun findFreeFields(board: Board, blizzards: List<Blizzard>, p: Point): List<Point> {
    val blizPoints = blizzards.map { b -> b.point }
//    println(blizPoints)
    return listOf(Point(1,0), Point(-1,0), Point(0,-1), Point(0,1)).map { dp ->
            Point(p.row + dp.row, p.col + dp.col)
    }
//        .also { println(it) }
    .filter { it.row in (1 until board.maxRow) && it.col in (1 until board.maxCol) || it == board.finish}
//        .also { println(it) }
    .filter { point -> !blizPoints.contains(point) }
//        .also { println(it) }
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

fun moveBlizzards(board: Board, blizzards: List<Blizzard>): List<Blizzard> {
    return blizzards.map { b ->
        val newRow = (b.point.row + b.dirAsDelta().row)
            .let { if (it == board.maxRow) 1 else if (it == 0) board.maxRow - 1 else it }
        val newCol = (b.point.col + b.dirAsDelta().col)
            .let { if (it == board.maxCol) 1 else if (it == 0) board.maxCol - 1 else it }

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

data class Board(val maxRow: Int, val maxCol: Int, val start: Point, val finish: Point)

fun print(board: Board, blizzards: List<Blizzard>, exp: Point) {
    for (r in 0..board.maxRow) {
        for (c in 0..board.maxCol) {
            if (r == exp.row && c == exp.col) {
                print('E')
            } else if (r == board.start.row && c == board.start.col) {
                print('.')
            } else if (r == board.finish.row && c == board.finish.col) {
                print('.')
            } else if (r == 0 || r == board.maxRow || c == 0 || c == board.maxCol) {
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

data class Point(val row: Int, val col: Int)
