package year2024.day10

import year2024.day08.Point

fun main() {
    AOC2024D10(SAMPLE1).solvePart1().let { println(it) } // 1
    AOC2024D10(SAMPLE2).solvePart1().let { println(it) } // 2
    AOC2024D10(SAMPLE3).solvePart1().let { println(it) } // 4
    AOC2024D10(SAMPLE4).solvePart1().let { println(it) } // 3
    AOC2024D10(SAMPLE5).solvePart1().let { println(it) } // 36
    AOC2024D10(INPUT).solvePart1().let { println(it) } // 796

    AOC2024D10(SAMPLE_2_1).solvePart2().let { println(it) } // 3
    AOC2024D10(SAMPLE_2_2).solvePart2().let { println(it) } // 13
    AOC2024D10(SAMPLE_2_3).solvePart2().let { println(it) } // 227
    AOC2024D10(SAMPLE5).solvePart2().let { println(it) } // 81
    AOC2024D10(INPUT).solvePart2().let { println(it) } // 1942
}

class AOC2024D10(val input: String) {
    val field = parse(input)

    fun solvePart1(): Int {
        return findUniquePaths().map { it.first() to it.last() }.distinct().size
    }

    fun solvePart2(): Int {
        return findUniquePaths().size
    }

    private fun findUniquePaths(): Set<Path> {
        val uniquePaths = mutableSetOf<Path>()
        for (row in field.indices) {
            for (column in field[row].indices) {
                if (field[row][column] == 0) {
                    val current = Point(row, column)
                    val currentPath = mutableListOf(current)
                    findPath(current, currentPath , uniquePaths)
                }
            }
        }
        return uniquePaths.toSet()
    }

    private fun findPath(start: Point, currentPath: MutableList<Point>, successfulPaths: MutableSet<Path>) {
        val height = field[start.first][start.second]
        if (height == 9) {
            successfulPaths.add(currentPath)
            return
        }
        neighborsOf(start)
            .filter { field[it.first][it.second] == height + 1 }
            .forEach { neighbor ->
                findPath(neighbor,
                    currentPath.toMutableList().also { it.add(neighbor) },
                    successfulPaths)
            }
        return
    }

    private fun neighborsOf(p: Point): Set<Point> {
        val n = mutableSetOf<Point>()
        if (p.first - 1 in field.indices) { n.add(Point(p.first - 1, p.second)) } // north
        if (p.first + 1 in field.indices) { n.add(Point(p.first + 1, p.second)) } // south
        if (p.second - 1 in field.first().indices) { n.add(Point(p.first, p.second - 1)) } // west
        if (p.second + 1 in field.first().indices) { n.add(Point(p.first, p.second + 1)) } // east
        return n
    }

    private fun parse(input: String): List<List<Int>> {
        val field  = mutableListOf<List<Int>>()
        input.lines().forEach { l ->
            field.add(l.map { c -> if (c=='.') -1 else c.digitToInt() }.toList())
        }
        return field.toList()
    }
}

typealias Point = Pair<Int, Int>
typealias Path = List<Point>