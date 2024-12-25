package year2024.day23

fun main() {
    AOC2024D23(SAMPLE).solve().also(::println) // 7
    AOC2024D23(INPUT).solve().also(::println) // 1149
}

class AOC2024D23(val input: String) {
    fun solve(): Int {
        val allConnections = input.lines().toSet()
        val connections: Map<String, Set<String>> = allConnections
            .fold(mutableMapOf<String, MutableSet<String>>()) { map, connection ->
                val (a, b) = connection.split('-')
                map[a] = (map[a] ?: mutableSetOf()).apply { add(b) }
                map[b] = (map[b] ?: mutableSetOf()).apply { add(a) }
                map
            }

        val allNodes = connections.keys.sorted()

        val cliques: Set<Set<String>> = allConnections.flatMap { conn ->
            // we have a connection from 'a' to 'b' and search
            // for a 'c' that has a connection to 'a' and 'b'
            val (a, b) = conn.split('-')
            allNodes.filter {
                c -> connections.getValue(c).containsAll(setOf(a, b))
            }.map { c -> setOf(a, b, c) }
        }.toSet()
        return cliques.count { cl -> cl.any { node -> node.startsWith('t') } }
    }
}
