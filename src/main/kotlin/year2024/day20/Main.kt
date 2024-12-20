package year2024.day20

import util.graph.Labyrinth
import util.point.Point
import util.point.manhattanDistanceTo

fun main() {
    AOC2024D20(SAMPLE).solve(2, 2, 2).also(::println) // 44
    AOC2024D20(INPUT).solve(100, 2, 2).also(::println) // 1358

    AOC2024D20(SAMPLE).solve(50, 2, 20).also(::println) // 285
    AOC2024D20(INPUT).solve(100, 2, 20).also(::println) // 1005856
}

class AOC2024D20(val input: String) {
    fun solve(minimalSaving: Int, minSeconds: Int, maxSeconds: Int): Int {
        val path = Labyrinth.fromStrings(input.lines()).dijkstraPath()
        var from = 0
        var to = 0
        val savings = mutableListOf<Int>()
        while (from < path.size) {
            while (to < path.size) {
                if (cheatPossible(path[from], path[to], minSeconds, maxSeconds)) {
                    savings.add(to - from - path[from].manhattanDistanceTo(path[to]))
                }
                to++
            }
            from++
            to = from
        }
        return savings.count { it >= minimalSaving }
    }

    private fun cheatPossible(p1: Point, p2: Point, min: Int, max: Int): Boolean {
        return p1.manhattanDistanceTo(p2) in min.. max
    }
}
