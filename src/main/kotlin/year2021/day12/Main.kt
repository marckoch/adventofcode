package year2021.day12

fun main() {
    println("part1:")
    solve(SAMPLE1, ::isVisitForbiddenPart1)
    solve(SAMPLE2, ::isVisitForbiddenPart1)
    solve(SAMPLE3, ::isVisitForbiddenPart1)
    solve(INPUT, ::isVisitForbiddenPart1)

    println("part2:")
    solve(SAMPLE1, ::isVisitForbiddenPart2)
    solve(SAMPLE2, ::isVisitForbiddenPart2)
    solve(SAMPLE3, ::isVisitForbiddenPart2)
    solve(INPUT, ::isVisitForbiddenPart2)
}

fun solve(input: String, isVisitForbidden: (List<String>, String) -> Boolean) {
    val connections = input.lines().map {
        val (from, to) = "(\\w*)-(\\w*)".toRegex().find(it)!!.destructured
        Pair(from, to)
    }

    val graph = buildGraph(connections)

    val paths = dfs(graph, "start", "end", isVisitForbidden)
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

fun dfs(
    g: Map<String, List<String>>,
    from: String,
    to: String,
    isVisitForbidden: (List<String>, String) -> Boolean
): List<List<String>> {
    val paths = mutableListOf<List<String>>()
    val visited = mutableListOf<String>()
    dfs(g, from, to, ArrayDeque(), paths, visited, isVisitForbidden)
    return paths
}

// https://www.baeldung.com/cs/simple-paths-between-two-vertices
fun dfs(
    g: Map<String, List<String>>, from: String, to: String,
    currentPath: ArrayDeque<String>,
    paths: MutableList<List<String>>,
    smallCavesVisited: MutableList<String>,
    isVisitForbidden: (List<String>, String) -> Boolean
) {
    if (isVisitForbidden(smallCavesVisited, from)) {
        return
    }

    val fromIsSmallCave = from.all { it in ('a'..'z') }
    if (fromIsSmallCave)
        smallCavesVisited.add(from)
    currentPath.addLast(from)

    if (from == to) {
        paths.add(currentPath.toMutableList()) // make copy by calling toMutableList
        if (fromIsSmallCave)
            smallCavesVisited.remove(from)
        currentPath.removeLast()
        return
    }

    for (next in g[from]!!.sorted()) {
        dfs(g, next, to, currentPath, paths, smallCavesVisited, isVisitForbidden)
    }

    if (fromIsSmallCave)
        smallCavesVisited.remove(from)
    currentPath.removeLast()
}

// part1: only one visit allowed
fun isVisitForbiddenPart1(smallCavesVisited: List<String>, from: String): Boolean {
    return smallCavesVisited.contains(from)
}

fun isVisitForbiddenPart2(smallCavesVisited: List<String>, from: String): Boolean {
    // never visit start again
    if (from == "start" && smallCavesVisited.contains("start")) return true

    val otherHasCount2 = smallCavesVisited.filter { it != from }
        .groupingBy { it }
        .eachCount()
        .any { entry -> entry.value == 2 }

    if (otherHasCount2) {
        // if other has already been visited twice, I can visit once at most
        return smallCavesVisited.count { it == from } > 0
    } else {
        // if no other has been visited twice, I can visit twice
        return smallCavesVisited.count { it == from } > 1
    }
}
