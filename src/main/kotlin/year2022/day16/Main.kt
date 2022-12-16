package year2022.day16


// not finished, too tough for me
fun main() {
    part1(SAMPLE)
}

val LINE_PATTERN = "Valve (.*) has flow rate=(.*); tunnels? leads? to valves? (.*)".toRegex()

fun part1(input: String) {
    val g = buildGraph(input)
    println(g)

    g.start("AA", 5)
}

fun buildGraph(input: String): Graph {
    val nodes = input.lines().map {line ->
        val (cave, flow, _) = LINE_PATTERN.find(line)!!.destructured
        println("$cave  $flow")
        Node(cave, flow.toInt())
    }
    val g = Graph(nodes)
    input.lines().map {line ->
        val (cave, _, neighbors) = LINE_PATTERN.find(line)!!.destructured
        println("$cave -> $neighbors")
        neighbors.split(",").map { it.trim() }.forEach {
                s -> g.addEdge(cave, s)
        }
    }
    return g
}

class Graph(val n: List<Node>) {

    private val nodes = n.associateBy { node -> node.name }

    var maxFlow = 0

    fun start(name: String, time: Int) {
        val openValves = mutableListOf<Node>()
        go(name, time,  openValves)
    }

    fun go(name: String, time: Int, openValves: List<Node>) {
        val currentCave = nodes[name]!!

        println()

    }

    fun addEdge(from: String, to: String) {
        nodes[to]?.let { nodes[from]?.addNeighbor(it) }
        nodes[from]?.let { nodes[to]?.addNeighbor(it) }
    }

    override fun toString(): String {
        return "$nodes"
    }
}
class Node(val name: String, val flow: Int) {

    val neighbors = mutableSetOf<Node>()

    fun flow(time: Int): Int {
        return flow * time
    }

    fun addNeighbor(n: Node) {
        neighbors.add(n)
    }
    override fun toString(): String {
        val neighborNames = neighbors.map { it.name }
        return "Node($name, $flow to $neighborNames)"
    }
}