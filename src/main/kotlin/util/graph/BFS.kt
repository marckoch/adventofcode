package util.graph

data class Edge(val from: Int, val to: Int)

// https://www.techiedelight.com/breadth-first-search/
class Graph(edges: List<Edge>, nodeCount: Int) {
    val adjList: List<List<Int>>

    init {
        val tempList = MutableList(nodeCount) { mutableListOf<Int>() }
        edges.forEach { (from, to) ->
            tempList[from].add(to)
            tempList[to].add(from)
        }
        adjList = tempList
    }
}

fun bfs(graph: Graph, start: Int, visited: BooleanArray) {
    val q = ArrayDeque<Int>()

    visited[start] = true

    q.add(start)

    while (q.isNotEmpty()) {
        val x = q.removeFirst()
        print("$x ")

        for (next in graph.adjList[x]) {
            if (!visited[next]) {
                visited[next] = true
                q.addLast(next)
            }
        }
    }
}

fun main() {
    val edges = listOf(
        Edge(1, 2), Edge(1, 3), Edge(1, 4), Edge(2, 5),
        Edge(2, 6), Edge(5, 9), Edge(5, 10), Edge(4, 7),
        Edge(4, 8), Edge(7, 11), Edge(7, 12)
    )

    val nodeCount = 15

    val graph = Graph(edges, nodeCount)

    val visited = BooleanArray(nodeCount)

    for (i in visited.indices) {
        if (!visited[i]) {
            println()
            println("starting with $i")
            bfs(graph, i, visited)
        }
    }
}

private inline fun <T> breadthFirstSearch(start: T, isEnd: (T) -> Boolean, moves: (T) -> Iterable<T>): T {
    val queue = ArrayDeque(listOf(start))
    val seen = mutableSetOf<T>()
    while (queue.isNotEmpty()) {
        val node = queue.removeFirst()
        for (neighbour in moves(node)) {
            if (isEnd(neighbour)) return neighbour
            if (seen.add(neighbour)) queue += neighbour // add to queue only if not already seen
        }
    }
    error("No route found")
}