package year2016.day22

import adventofcode.Problem
import util.lists.cartesianProduct
import java.util.PriorityQueue
import kotlin.math.abs

fun main() {
    AOC2016D22(SAMPLE).run()
    AOC2016D22(INPUT).run()
}

class AOC2016D22(input: String) : Problem(input) {
    private val nodes = inputLines().map { l -> parseNodes(l) }

    override fun solve1() : Int {
        return nodes.cartesianProduct(nodes)
            .filter { (nodeA, _) -> nodeA.isNotEmpty() }
            .filter { (nodeA, nodeB) -> nodeA != nodeB }
            .filter { (nodeA, nodeB) -> nodeA canFitIn nodeB }
            .count()
    }

    override fun solve2() : Int {
        val maxX = nodes.maxOf { it.x }
        nodes.first { it.x == maxX && it.y == 0 }.also { it.isGoalData = true }
        nodes.first { it.x == 0 && it.y == 0 }.also { it.isTargetNode = true }
        val initialState = State(nodes, 0)

        initialState.print()
        val unvisited = PriorityQueue(compareBy(State::weight))
        unvisited.add(initialState)

        val visited = emptyList<State>()
        while (unvisited.isNotEmpty()) {
            println(unvisited.size)
            val current = unvisited.remove()!!

            current.print()

            val goal = current.getGoalNode()
            val target = current.getTargetNode()
            if (goal.x == target.x && goal.y == target.y) {
                return current.length
            }

            current.potentialNext().forEach { n ->
                if (n !in visited) {
                    unvisited.add(n)
                }
            }
        }

        return 1
    }

    private fun parseNodes(line: String): Node {
        return Node.from(line)
    }
}

data class State(val nodes: List<Node>, val length: Int) {
    val maxX = nodes.maxOf { it.x }
    val maxY = nodes.maxOf { it.y }
    val maxSize = nodes.maxOf { it.size }

    fun getTargetNode(): Node = nodes.first { it.isTargetNode }
    fun getGoalNode(): Node = nodes.first { it.isGoalData }
    fun getFreeNode(): Node = nodes.first { it.isEmpty() }
    fun get(x: Int, y: Int): Node? = nodes.firstOrNull { it.x == x && it.y == y }
    fun distanceGoalToTarget() : Int {
        val g = getGoalNode()
        val t = getTargetNode()
        return manhattan(g, t)
    }
    fun distanceFreeToGoal() : Int {
        val g = getGoalNode()
        val f = getFreeNode()
        return manhattan(g, f)
    }
    fun distanceFreeToTarget() : Int {
        val t = getTargetNode()
        val f = getFreeNode()
        return manhattan(t, f)
    }
    fun weight(): Int = distanceFreeToTarget() + distanceFreeToGoal() + distanceGoalToTarget() + length

    fun manhattan(n1: Node, n2: Node) : Int = abs(n1.x - n2.x) + abs(n1.y - n2.y)

    fun potentialNext() : List<State> {
        val f = getFreeNode()
        return listOf(getUpper(f), getLower(f), getLeft(f), getRight(f))
            .filterNotNull()
            .filter { isInField(it) }
            .filter { it.canFitIn(f) }
            .map { this.moveData(it.x, it.y, f.x, f.y) }
    }

    fun moveData(xFrom: Int, yFrom: Int, xTo: Int, yTo: Int) : State {
        val newNodes = nodes.toMutableList()

        val oldFrom = newNodes.first { it.x == xFrom && it.y == yFrom }
        val indexFrom = newNodes.indexOfFirst { it.x == xFrom && it.y == yFrom }
        val data = oldFrom.used
        val from = oldFrom.copy(used = 0)
        newNodes.removeAt(indexFrom)
        newNodes.add(indexFrom, from)

        val oldTo = newNodes.first { it.x == xTo && it.y == yTo }
        val indexTo = newNodes.indexOfFirst { it.x == xTo && it.y == yTo }
        val to = oldTo.copy(used = data)
        newNodes.removeAt(indexTo)
        newNodes.add(indexTo, to)
        return this.copy(nodes = newNodes, length = this.length + 1)
    }

    fun isInField(n: Node) : Boolean = n.x in 0.. maxX && n.y in 0 .. maxY

    fun getUpper(n: Node): Node? = get(n.x, n.y - 1)
    fun getLower(n: Node): Node? = get(n.x, n.y + 1)
    fun getLeft(n: Node): Node? = get(n.x - 1, n.y)
    fun getRight(n: Node): Node? = get(n.x + 1, n.y)

    fun print() {
        val l = maxSize.toString().length
        fun Int.pad() = this.toString().padStart(l)
        for (y in 0..maxY) {
            val row = nodes.filter { it.y == y }
                .sortedBy { it.x }
                .map { n -> when  {
                    n.isGoalData -> "${n.used.pad()}*${n.size.pad()}"
                    n.isTargetNode -> "${n.used.pad()}_${n.size.pad()}"
                    else -> "${n.used.pad()}/${n.size.pad()}"
                } }
                .joinToString(" - ")
            println(row)

        }
    }
}

data class Node(val x: Int, val y: Int, val size: Int, val used: Int, val avail: Int, val use: Int,
    var isGoalData: Boolean = false, var isTargetNode: Boolean = false) {
    companion object {
        fun from(line: String): Node {
            val (pPath, pSize, pUsed, pAvail, pUse) = line.split(Regex("\\s+"))

            val iX = pPath.indexOf('x')
            val x = pPath.substring(iX + 1, pPath.indexOf('-', iX)).toInt()
            val iY = pPath.indexOf('y')
            val y = pPath.substring(iY + 1).toInt()

            return Node(x, y,
                pSize.dropLast(1).toInt(),
                pUsed.dropLast(1).toInt(),
                pAvail.dropLast(1).toInt(),
                pUse.dropLast(1).toInt())
        }
    }

    fun isEmpty() = used == 0
    fun isNotEmpty() = !isEmpty()
    infix fun canFitIn(other: Node) = this.used <= other.avail
}