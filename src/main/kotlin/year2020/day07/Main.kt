package year2020.day07

fun main() {
    part1(SAMPLE)
    part1(INPUT)

    part2(SAMPLE)
    part2(SAMPLE2)
    part2(INPUT)
}

val LINE_PATTERN = """(.*) bags contain (.*).""".toRegex()
val BAGS_PATTERN = """(\d*) (.*) bags?""".toRegex()

fun part1(input: String) {
    val edges = parseEdges(input)
    // edges.forEach { println(it) }

    val end = "shiny gold"
    edges.map { it.from }
        .filter { it != end }
        .distinct()
        .count { valid(it, end, edges) }
        .let { println("part1: $it") }
}

fun part2(input: String) {
    val edges = parseEdges(input)
    // edges.forEach { println(it) }

    val from = "shiny gold"
    countBags(from, edges).let { println("part2: $it") }
}

fun countBags(name: String, edges: List<Edge>): Int {
    val next = edges.filter { it.from == name }
    if (next.isEmpty()) return 1
    return next.sumOf { it.weight + it.weight * countBags(it.to, edges) }
}

// valid true means there is a path that ends in 'to'
fun valid(from: String, to: String, edges: List<Edge>): Boolean {
    val paths = mutableListOf<List<String>>()
    val visited = mutableListOf<String>()
    dfs(from, to, edges, ArrayDeque(), paths, visited)
    // println("paths $from -> $to: $paths")
    return paths.isNotEmpty()
}

fun dfs(
    from: String, to: String, edges: List<Edge>,
    currentPath: ArrayDeque<String>,
    paths: MutableList<List<String>>,
    visited: MutableList<String>
) {
    if (visited.contains(from)) {
        return
    }

    visited.add(from)
    currentPath.addLast(from)

    if (from == to) {
        paths.add(currentPath.toMutableList()) // make copy by calling toMutableList
        visited.remove(from)
        currentPath.removeLast()
        return
    }

    for (next in edges.filter { it.from == from }) {
        dfs(next.to, to, edges, currentPath, paths, visited)
    }

    visited.remove(from)
    currentPath.removeLast()
}

data class Edge(val from: String, val to: String, val weight: Int)

fun parseEdges(input: String): List<Edge> {
    return input.lines().flatMap { l ->
        LINE_PATTERN.find(l)!!.destructured.let { (name, bags) ->
            //println("$name -> $bags")
            if (bags == "no other bags") {
                listOf(Edge(name, "", 0))
            } else {
                bags.split(", ").map { b ->
                    val (amount, color) = BAGS_PATTERN.find(b)!!.destructured
                    Edge(name, color, amount.toInt())
                }
            }
        }
    }
}
