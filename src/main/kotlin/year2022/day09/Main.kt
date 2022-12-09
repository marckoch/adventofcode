package year2022.day09

import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min
import kotlin.math.sign

fun main() {
    println("part1: " + solve(SAMPLE, 2))
    println("part1: " + solve(INPUT, 2))

    println("part2: " + solve(SAMPLE, 10))
    println("part2: " + solve(SAMPLE2, 10))
    println("part2: " + solve(INPUT, 10))
}

val LINE_PATTERN = """([RLUD]) (\d+)""".toRegex()

fun solve(input: String, ropeLength: Int): Int {
    val segments = mutableListOf<Pos>()
    segments.addAll(List(ropeLength) { Pos(0, 0) })

    val tailVisits = mutableSetOf<Pos>()
    tailVisits.add(Pos(0, 0))

    input.lines().forEach {
        val (s1, s2) = LINE_PATTERN.find(it)!!.destructured
        val direction = Dir.valueOf(s1)
        val numberOfSteps = s2.toInt()

        repeat(numberOfSteps) {
//            println("$direction ${it+1}/$numberOfSteps")
            segments[0] = moveHead(direction, segments[0])

            // for the rest of the line each segment acts as the head for its follower
            for (i in segments.indices.windowed(2)) {
                val headIndex = i[0]
                val tailIndex = i[1]
                segments[tailIndex] = moveNextSegment(segments[headIndex], segments[tailIndex])
            }

            tailVisits.add(segments.last())
            //print(segments)
        }
    }
    return tailVisits.size
}

fun moveHead(dir: Dir, x: Pos): Pos {
    return when (dir) {
        Dir.U -> moveUp(x)
        Dir.D -> moveDown(x)
        Dir.L -> moveLeft(x)
        Dir.R -> moveRight(x)
    }
}

fun moveNextSegment(head: Pos, tail: Pos): Pos {
    val dX = head.first - tail.first
    val dY = head.second - tail.second

    if (abs(dX) > 1 || abs(dY) > 1) {
        return Pos(tail.first + dX.sign, tail.second + dY.sign)
    }

    // don't move
    return tail
}

fun moveUp(pos: Pos): Pos = Pos(pos.first, pos.second + 1)
fun moveDown(pos: Pos): Pos = Pos(pos.first, pos.second - 1)
fun moveLeft(pos: Pos): Pos = Pos(pos.first - 1, pos.second)
fun moveRight(pos: Pos): Pos = Pos(pos.first + 1, pos.second)

typealias Pos = Pair<Int, Int>

enum class Dir {
    R, L, U, D
}

private fun print(segments: List<Pos>) {
    println()
    val fromX = min(0, segments.map { it.first }.min())
    val toX = max(0, segments.map { it.first }.max())
    val fromY = min(0, segments.map { it.second }.min())
    val toY = max(0, segments.map { it.second }.max())

    (toY downTo fromY).forEach { y ->
        val r = (fromX..toX).map { x ->
            // check if we have a segment on this field
            val s = segments.withIndex().firstOrNull { (_, pair) -> pair.first == x && pair.second == y }
            if (s != null) {
                if (s.index == 0) "H" else s.index.toString()
            } else {
                if (x == 0 && y == 0) "s" else "."
            }
        }
        println(r.joinToString(""))
    }
}