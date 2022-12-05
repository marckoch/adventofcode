package year2021.day02

fun main() {
    solve(Part1::proceed, SAMPLE)
    solve(Part1::proceed, INPUT)

    solve(Part2::proceed, SAMPLE)
    solve(Part2::proceed, INPUT)
}

object Part1 {
    fun proceed(pos: Position, move: Move): Position {
        val newHorizontal = pos.horizontal + move.horizontal
        val newDepth = pos.depth + move.depth

        return Position(newHorizontal, newDepth)
    }
}

object Part2 {
    fun proceed(pos: Position, move: Move): Position {
        val newHorizontal = pos.horizontal + move.horizontal
        val newDepth = pos.depth + pos.aim * move.horizontal
        val newAim = pos.aim + move.depth

        return Position(newHorizontal, newDepth, newAim)
    }
}

fun solve(proceed: (Position, Move) -> Position, input: String) {
    input.lines()
        .fold(Position()) { acc, s -> proceed(acc, parse(s)) }
        .let { println(it.horizontal * it.depth) }
}

// parse input line and return Pair of steps in (forward, up/down)
// up is negative because depth is decreasing
fun parse(line: String): Move {
    val (command, number) = line.split(" ")
    return when (command) {
        "forward" -> Move(number.toInt(), 0)
        "up" -> Move(0, -number.toInt())
        "down" -> Move(0, number.toInt())
        else -> throw IllegalArgumentException("problem with $line")
    }
}

class Move(val horizontal: Int = 0, val depth: Int = 0)

class Position(val horizontal: Int = 0, val depth: Int = 0, val aim: Int = 0)