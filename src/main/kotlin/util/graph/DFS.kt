package util.graph

const val SAMPLE = """Sabqponm
abcryxxl
accszExk
acctuvwj
abdefghi"""

// taken/unused from 2022.12
fun main() {
    solve(SAMPLE)
}

fun solve(input: String) {
    val map = input.lines()

    val start = find(map, 'S')
    val end = find(map, 'E')

    val dfsPaths = dfs(map, start, end)
    dfsPaths.forEach { println(it) }
    println("min")
    println(dfsPaths.minBy { it.size }.size - 1)

    val bfsPaths = bfs(map, start, end)
    bfsPaths.forEach { println(it) }
    println("min")
    println(bfsPaths.minBy { it.size }.size - 1)
}

fun find(map: List<String>, c: Char): Point {
    val targetRow = map.withIndex().first { row ->
        row.value.contains(c)
    }
    val col = targetRow.value.indexOfFirst { it == c }
    return Pair(targetRow.index, col)
}

// basic dfs, good enough for sample, but will not complete for real input
// (makes sense: we don't want/need to find ALL paths and then filter for shortest.
// it is better to find the minimal path to target and abort immediately)
fun dfs(map: List<String>, from: Point, to: Point): List<List<Point>> {
    val paths = mutableListOf<List<Point>>()
    val visited = mutableListOf<Point>()
    dfs(map, from, to, ArrayDeque(), paths, visited)
    return paths
}

// https://www.baeldung.com/cs/simple-paths-between-two-vertices
fun dfs(
    map: List<String>, from: Point, to: Point,
    currentPath: ArrayDeque<Point>,
    paths: MutableList<List<Point>>,
    visited: MutableList<Point>
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

    for (next in findNeighbors(map, from)) {
        dfs(map, next, to, currentPath, paths, visited)
    }

    visited.remove(from)
    currentPath.removeLast()
}

// basic bfs, good enough for sample, but will not complete for real input
// (makes sense: we don't want/need to find ALL paths and then filter for shortest.
// it is better to find the minimal path to target and abort immediately)
fun bfs(map: List<String>, from: Point, to: Point): List<List<Point>> {
    val paths = mutableListOf<List<Point>>()
    val visited = mutableListOf<Point>()
    bfs(map, from, to, ArrayDeque(), paths, visited)
    return paths
}

fun bfs(
    map: List<String>, from: Point, to: Point,
    currentPath: ArrayDeque<Point>,
    paths: MutableList<List<Point>>,
    visited: MutableList<Point>
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

    val q = ArrayDeque<Point>()
    q.addAll(findNeighbors(map, from))

    while (q.isNotEmpty()) {
        val next = q.removeFirst()
        bfs(map, next, to, currentPath, paths, visited)
    }

    visited.remove(from)
    currentPath.removeLast()
}

fun findNeighbors(map: List<String>, p: Point): List<Point> {
    val north = Pair(p.first - 1, p.second)
    val south = Pair(p.first + 1, p.second)
    val west = Pair(p.first, p.second - 1)
    val east = Pair(p.first, p.second + 1)

    val currentElevation = elevation(map, p)

    return listOf(north, south, west, east)
        .filter { isInside(map, it) }
        .filter { elevation(map, it) in 'a' .. currentElevation + 1}
}

fun isInside(map: List<String>, p: Point): Boolean {
    val validRows = map.indices
    val validCols = map[0].indices
    return p.first in validRows && p.second in validCols
}

fun elevation(map: List<String>, p: Point): Char {
    return when (val c = map[p.first][p.second]) {
        'S' -> 'a'
        'E' -> 'z'
        else -> c
    }
}

typealias Point = Pair<Int, Int> // (row, col), e.g. (y, x)

