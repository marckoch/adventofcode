package year2021.day12

fun main() {
    part1(SAMPLE1)
    part1(SAMPLE2)
    part1(SAMPLE3)
    part1(INPUT)
}

fun part1(input: String) {
    val connections = input.lines().map {
        val (from, to) = "(\\w*)-(\\w*)".toRegex().find(it)!!.destructured
        Pair(from, to)
    }

    val graph = buildGraph(connections)

    val paths = dfs(graph, "start", "end")
//    paths.forEach { println(it) }
    println(paths.size)
}

fun buildGraph(connections: List<Pair<String, String>>): Map<String, List<String>> {
    return connections.fold(HashMap<String, MutableList<String>>()) { acc, pair ->
        val from = pair.first
        val to = pair.second

        if (acc.containsKey(from)) {
            acc[from]!!.add(to)
        } else {
            acc[from] = mutableListOf(to)
        }

        if (acc.containsKey(to)) {
            acc[to]!!.add(from)
        } else {
            acc[to] = mutableListOf(from)
        }

        acc
    }
}

fun dfs(g: Map<String, List<String>>, from: String, to: String): List<List<String>> {
    val paths = mutableListOf<List<String>>()
    val visited = mutableListOf<String>()
    dfs(g, from, to, ArrayDeque(), paths, visited)
    return paths
}

// https://www.baeldung.com/cs/simple-paths-between-two-vertices
fun dfs(g: Map<String, List<String>>, from: String, to: String, currentPath: ArrayDeque<String>, paths: MutableList<List<String>>, visited: MutableList<String>) {
    if (visitForbidden(visited, from)) {
        return
    }
    val fromIsSmallCave = from.all { it in ('a'..'z') }
    if (fromIsSmallCave)
        visited.add(from)
    currentPath.addLast(from)
    if (from == to) {
        paths.add(currentPath.toMutableList()) // make copy by calling toMutableList
        if (fromIsSmallCave)
            visited.remove(from)
        currentPath.removeLast()
        return
    }

    for (next in g[from]!!.sorted()) {
        dfs(g, next, to, currentPath, paths, visited)
    }

    if (fromIsSmallCave)
        visited.remove(from)
    currentPath.removeLast()
}

fun visitForbidden(visited: List<String>, from: String): Boolean {
    return visited.contains(from)
}
