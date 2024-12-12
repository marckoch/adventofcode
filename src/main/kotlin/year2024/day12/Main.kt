package year2024.day12

fun main() {
    AOC2024D12(SAMPLE1).solve().let { println(it) } // 140
    AOC2024D12(SAMPLE2).solve().let { println(it) } // 772
    AOC2024D12(SAMPLE3).solve().let { println(it) } // 1930
    AOC2024D12(INPUT).solve().let { println(it) } // 1465968

    AOC2024D12(SAMPLE1).solve2().let { println(it) } // 80
    AOC2024D12(SAMPLE2).solve2().let { println(it) } // 436
    AOC2024D12(SAMPLE3).solve2().let { println(it) } // 1206
    AOC2024D12(INPUT).solve2().let { println(it) } // 1206
}

typealias Point = Pair<Int, Int>

data class Plant(val type: Char, var visited: Boolean = false)

operator fun List<List<Plant>>.get(point: Point): Plant {
    return this[point.first][point.second]
}

class AOC2024D12(val input: String) {
    val map: List<List<Plant>> = input.lines().map { line -> line.map { c -> Plant(c) } }.toList()

    fun solve(): Long {
        return allPoints().sumOf { floodFill(mutableListOf(it)) }
    }

    fun solve2(): Long {
        return allPoints().sumOf { floodFill2(mutableListOf(it)) }
    }

    // do flood fill and analyse neighbors,
    // if neighbor is different type, we have a fence
    private fun floodFill(toVisit: MutableList<Pair<Int, Int>>): Long {
        var area = 0L
        var perimeter = 0L
        while (toVisit.isNotEmpty()) {
            val current = toVisit.removeAt(0)
            if (!map[current].visited) {
                map[current].visited = true

                area++

                // from current field find 4 neighbors
                setOf(
                    (current.first - 1 to current.second),
                    (current.first + 1 to current.second),
                    (current.first to current.second - 1),
                    (current.first to current.second + 1)
                ).forEach {
                    if (inMap(it)) {
                        if (map[it].type == map[current].type) {
                            if (!map[it].visited)
                                toVisit.add(it)
                        } else {
                            // neighbor is of other type, add one perimeter
                            perimeter++
                        }
                    } else {
                        // neighbor is not in map, add one perimeter
                        perimeter++
                    }
                }
            }
        }
        return area * perimeter
    }

    // do flood fill and analyse neighbors,
    // instead of counting sides we count corners
    private fun floodFill2(toVisit: MutableList<Pair<Int, Int>>): Long {
        var area = 0L
        var corners = 0L
        while (toVisit.isNotEmpty()) {
            val current = toVisit.removeAt(0)
            if (!map[current].visited) {
                map[current].visited = true

                area++

                val north = current.first - 1 to current.second
                val northeast = current.first - 1 to current.second + 1
                val east = current.first to current.second + 1
                val southeast = current.first + 1 to current.second + 1
                val south = current.first + 1 to current.second
                val southwest = current.first + 1 to current.second - 1
                val west = current.first to current.second - 1
                val northwest = current.first - 1 to current.second - 1

                if (inMap(north) && !map[north].visited && map[current].type == map[north].type)
                    toVisit.add(north)
                if (inMap(east) && !map[east].visited && map[current].type == map[east].type)
                    toVisit.add(east)
                if (inMap(south) && !map[south].visited && map[current].type == map[south].type)
                    toVisit.add(south)
                if (inMap(west) && !map[west].visited && map[current].type == map[west].type)
                    toVisit.add(west)

                corners += countCorners(current, north, east, northeast)
                corners += countCorners(current, east, south, southeast)
                corners += countCorners(current, south, west, southwest)
                corners += countCorners(current, west, north, northwest)
            }
        }
        return area * corners
    }

    private fun countCorners(current: Point, straightNeighbor1: Point, straightNeighbor2: Point, diagonalNeighbor: Point): Int {
        var corners = 0
        // both line neighbors are not in map -> current must be corner plot
        // e.g. above '_' and right '_' from A here:
        // _
        // -+
        // A|_
        if (!inMap(straightNeighbor1) && !inMap(straightNeighbor2)) corners++

        // one lineNeighbor is not in map -> this must be an edge plot
        // AND other lineNeighbor is in map but is different type -> we have a corner
        // e.g. above '_' and right 'B' from A here:
        // _
        // -+-
        // A|B
        if (!inMap(straightNeighbor1) && inMap(straightNeighbor2) &&
            map[straightNeighbor2].type != map[current].type) corners++
        if (inMap(straightNeighbor1) && !inMap(straightNeighbor2) &&
            map[straightNeighbor1].type != map[current].type) corners++

        // C.
        // AB
        if (inMap(straightNeighbor1) && inMap(straightNeighbor2) &&
            map[straightNeighbor1].type != map[current].type &&
            map[straightNeighbor2].type != map[current].type) corners++

        // AC
        // AA
        if (inMap(straightNeighbor1) && inMap(straightNeighbor2) && inMap(diagonalNeighbor) &&
            map[straightNeighbor1].type == map[current].type &&
            map[straightNeighbor2].type == map[current].type &&
            map[diagonalNeighbor].type != map[current].type) corners++

        return corners
    }

    private fun allPoints(): List<Point> {
        val points = mutableListOf<Point>()
        for (row in map.indices) {
            for (col in map.first().indices) {
                points.add(Pair(row, col))
            }
        }
        return points
    }

    private fun inMap(point: Point): Boolean {
        return point.first in map.indices && point.second in map.first().indices
    }
}
