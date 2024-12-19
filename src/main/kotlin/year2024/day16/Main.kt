package year2024.day16

import util.graph.Direction
import util.graph.Labyrinth
import util.point.Point
import java.util.*

fun main() {
    AOC2024D16(SAMPLE1).solve().also(::println)
    AOC2024D16(SAMPLE2).solve().also(::println)

    AOC2024D16(INPUT).solve().also(::println)
}

operator fun <T> List<MutableList<T>>.set(p: Point, value: T) {
    this[p.first][p.second] = value
}

operator fun <T> List<MutableList<T>>.get(p: Point): T = this[p.first][p.second]

data class Node(val point: Point, val dir: Direction) {
    fun moveForward(): Node {
        return when (dir) {
            Direction.NORTH -> Node(Point(point.first - 1, point.second), dir)
            Direction.WEST -> Node(Point(point.first, point.second - 1), dir)
            Direction.SOUTH -> Node(Point(point.first + 1, point.second), dir)
            Direction.EAST -> Node(Point(point.first, point.second + 1), dir)
        }
    }
}

class AOC2024D16(val input: String) {
    fun solve(): Int {
        val l = Labyrinth.fromStrings(input.lines())//.also { it.print() }

        val visited = mutableSetOf<Node>()

        val unvisited = PriorityQueue<Pair<Node, Int>> { (_, cost1), (_, cost2) -> cost1.compareTo(cost2) }
        unvisited.add(Node(l.start, Direction.EAST) to 0)

        while (unvisited.isNotEmpty()) {
            val (node, cost) = unvisited.poll().also { (node, _) -> visited.add(node) }
//            println("$node $cost")
            if (node.point == l.end) {
                return cost
            }

            val neighborNodesWithCost = listOf(
                node.copy(dir = node.dir.turnLeft()) to cost + 1000,
                node.copy(dir = node.dir.turnRight()) to cost + 1000,
                node.moveForward() to cost +1
            ).filter { (node, _) -> node !in visited }
                .filter { (node, _) -> l.isNotObstacle(node.point) }

            unvisited.addAll(neighborNodesWithCost)
        }
        error("should never happen")
    }
}
