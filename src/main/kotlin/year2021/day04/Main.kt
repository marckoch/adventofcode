package year2021.day04

val LINE_PATTERN = Regex("""(\d+) +(\d+) +(\d+) +(\d+) +(\d+)""")

fun main() {
    part1(SAMPLE)
    part1(INPUT)

    part2(SAMPLE)
    part2(INPUT)
}

fun part1(input: String) {
    val numbers = extractNumbers(input.lines().first())

    val boards = buildBoards(input)

    numbers.forEach { number ->
        boards.forEach { board ->
            board.removeNumber(number)

            if (board.hasBingo()) {
                printResult(board, number)
                return
            }
        }
    }
}

fun part2(input: String) {
    val numbers = extractNumbers(input.lines().first())

    var boards = buildBoards(input)

    numbers.forEach { number ->
        // we filter until we have one board left
        if (boards.size > 1) {
            boards = boards.filter { board ->
                board.removeNumber(number)
                !board.hasBingo()
            }
        } else {
            val board = boards.first()
            board.removeNumber(number)

            if (board.hasBingo()) {
                printResult(board, number)
                return
            }
        }
    }
}

fun extractNumbers(s: String) = s.split(",").map { it.toInt() }

fun printResult(board: Board, number: Int) {
    val sum = board.sumOfNumbers()
    val result = board.sumOfNumbers() * number
    println("$sum * $number = $result")
}

fun buildBoards(input: String): List<Board> {
    return input.lines()
        .drop(1)    // line with numbers that are drawn
        .windowed(6, 6)
        .map { it.drop(1) }  // drop the blank line
        .map { extractBoard(it) }
}

fun printBoards(boards: List<Board>) {
    boards.forEach { println(it) }
}

// if a number is drawn we remove it from the board by setting the element to -1,
// we can not use 0 because that is a valid number that can be drawn
class Board(private var b: List<List<Int>>) {
    fun removeNumber(n: Int) {
        b = b.map { ints ->
            ints.map { i ->
                if (i == n) -1 else i
            }
        }
    }

    fun hasBingo(): Boolean {
        val hasEmptyRow = b.any { lineIsClear(it) }
        val hasEmptyCol = transpose(b).any { lineIsClear(it) }
        return hasEmptyRow || hasEmptyCol
    }

    private fun lineIsClear(line: List<Int>): Boolean {
        return line.all { it == -1 }
    }

    fun sumOfNumbers(): Int {
        return b.fold(0) { acc, ints ->
            acc + ints.sumOf { i -> if (i > 0) i else 0 }
        }
    }

    override fun toString(): String {
        return b.toString()
    }
}

fun extractBoard(lines: List<String>): Board {
    return Board(lines.map { s -> parse(s) })
}

fun parse(line: String): List<Int> {
    return LINE_PATTERN
        .find(line)!!
        .groupValues
        .drop(1)  // first element is original string
        .map { it.toInt() }
}

// https://gist.github.com/clementgarbay/49288c006252955c2a3c6139a61ca92a
fun <E> transpose(xs: List<List<E>>): List<List<E>> {
    // Helpers
    fun <E> List<E>.head(): E = this.first()
    fun <E> List<E>.tail(): List<E> = this.takeLast(this.size - 1)
    fun <E> E.append(xs: List<E>): List<E> = listOf(this).plus(xs)

    xs.filter { it.isNotEmpty() }.let { ys ->
        return when (ys.isNotEmpty()) {
            true -> ys.map { it.head() }.append(transpose(ys.map { it.tail() }))
            else -> emptyList()
        }
    }
}