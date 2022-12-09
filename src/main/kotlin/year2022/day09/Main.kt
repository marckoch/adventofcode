package year2022.day09

import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

fun main() {
//    part1(SAMPLE)
//    part1(INPUT)

    part2(SAMPLE)
//    part2(INPUT) // 5447 too high
}

enum class Dir {
    R, L, U, D
}

val LINE_PATTERN = """([RLUD]) (\d+)""".toRegex()
fun part1(input: String) {
    var headPos = Pair(0, 0)
    var tailPos = Pair(0, 0)
    val tailVisits = mutableSetOf<Pos>()
    tailVisits.add(tailPos)

    input.lines().forEach {
        val (s1, s2) = LINE_PATTERN.find(it)!!.destructured
        val direction = Dir.valueOf(s1)
        val numberOfSteps = s2.toInt()

        repeat(numberOfSteps) {
            val oldHeadPos = headPos
            headPos = move(direction, oldHeadPos)
            if (nextShouldFollow(headPos, tailPos)) {
                tailPos = oldHeadPos
                tailVisits.add(tailPos)
            }
            //print(headPos, tailPos)
        }
    }
    println("part1: ${tailVisits.size}")
}

fun part2(input: String) {
    val segments = mutableListOf<Pos>()
    segments.addAll(List(10) { Pos(0, 0) })
    val tailVisits = mutableSetOf<Pos>()
    tailVisits.add(Pair(0, 0))

    input.lines().forEach {
        val (s1, s2) = LINE_PATTERN.find(it)!!.destructured
        val direction = Dir.valueOf(s1)
        val numberOfSteps = s2.toInt()

        println(it)

        repeat(numberOfSteps) {
            var oldHeadPos = segments[0]
            segments[0] = move(direction, oldHeadPos)

            for (i in (0..segments.lastIndex).windowed(2)) {
                val h = i[0]
                val t = i[1]
                if (nextShouldFollow(segments[h], segments[t])) {
                    val oldTailPos = segments[t]
                    segments[t] = oldHeadPos
                    oldHeadPos = oldTailPos
                }
            }
            tailVisits.add(segments.last())
//            println(segments)
            print(segments)
        }
    }
    println("part1: ${tailVisits.size}")
}

// next segment should follow if current one is more than 1 field away,
// in corner case tail should NOT follow
// e.g.
// ..H
// .T.
fun nextShouldFollow(head: Pos, tail: Pos): Boolean {
    if (abs(head.first - tail.first) > 1) return true
    if (abs(head.second - tail.second) > 1) return true
    return false
}

fun move(dir: Dir, x: Pos): Pos {
    return when (dir) {
        Dir.U -> moveUp(x)
        Dir.D -> moveDown(x)
        Dir.L -> moveLeft(x)
        Dir.R -> moveRight(x)
    }
}

fun moveUp(pos: Pos): Pos = Pair(pos.first, pos.second + 1)
fun moveDown(pos: Pos): Pos = Pair(pos.first, pos.second - 1)
fun moveLeft(pos: Pos): Pos = Pair(pos.first - 1, pos.second)
fun moveRight(pos: Pos): Pair<Int, Int> = Pair(pos.first + 1, pos.second)

typealias Pos = Pair<Int, Int>

private fun print(head: Pos, tail: Pos) {
    println()
    val fromX = listOf(0, head.first, tail.first).min()
    val toX = listOf(0, head.first, tail.first).max()
    val fromY = listOf(0, head.second, tail.second).min()
    val toY = listOf(0, head.second, tail.second).max()

    (toY downTo fromY).forEach { y ->
        val r = (fromX..toX).map { x ->
            if (x == head.first && y == head.second) {
                "H"
            } else if (x == tail.first && y == tail.second) {
                "T"
            } else if (x == 0 && y == 0) {
                "s"
            } else "."
        }
        println(r.joinToString(""))
    }
}

private fun print(segments: List<Pos>) {
    println()
    val fromX = min(0, segments.map { it.first }.min())
    val toX = max(0, segments.map { it.first }.max())
    val fromY = min(0, segments.map { it.second }.min())
    val toY = max(0, segments.map { it.second }.max())

    (toY downTo fromY).forEach { y ->
        val r = (fromX..toX).map { x ->
            val s = segments.withIndex().firstOrNull { (index, pair) -> pair.first == x && pair.second == y }
            if (s != null) {
                if (s.index == 0) "H" else s.index.toString()
            }
            else {
                if (x == 0 && y == 0) "s" else "."
            }
        }
        println(r.joinToString(""))
    }
}