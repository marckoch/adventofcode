package year2020.day24

fun main() {
    part1(SAMPLE)
    part1(INPUT)

    part2(SAMPLE)
    part2(INPUT)
}

fun part1(input: String) {
    val steps = input.lines().map { parse(it) }
    val tiles = steps.map { findFinalTile(it) }
    // tiles.forEach{ println(it) }

    tiles.groupingBy { it }
        .eachCount()
        .filterValues { it % 2 == 1 }
        .count()
        .let { println("part1: $it") }
}

fun part2(input: String) {
    val steps = input.lines().map { parse(it) }
    val tiles = steps.map { findFinalTile(it) }
    // tiles.forEach{ println(it) }

    val initialBlackTiles = tiles.groupingBy { it }
        .eachCount()
        .filterValues { it % 2 == 1 }
        .keys
    // initialBlackTiles.let { println(it) }

    var blackTiles = initialBlackTiles

    repeat (100) {
        blackTiles = changeTiles(blackTiles)
        val day = it + 1
        if (day <= 10 || day % 10 == 0) {
            println("Day $day: ${blackTiles.size}")
        }
    }
}

fun changeTiles(blackTiles: Set<Tile>): Set<Tile> {
    val allNeighbors = blackTiles.flatMap { it.findNeighbors() }.toSet()
    val allWhiteNeighborTiles = (allNeighbors - blackTiles)

    // black tiles that remain black because they have 1 or 2 black neighbor tiles
    val constantBlackTiles = blackTiles.mapNotNull { tile ->
        val noOfBlackNeighborTiles = tile.findNeighbors().count { it in blackTiles }
        if (noOfBlackNeighborTiles in (1..2)) tile else null
    }.toSet()

    // white tiles that have 2 black neighbor tiles become black
    val newBlackTiles = allWhiteNeighborTiles.mapNotNull { tile ->
        val noOfBlackNeighborTiles = tile.findNeighbors().count { it in blackTiles }
        if (noOfBlackNeighborTiles == 2) tile else null
    }.toSet()

    return constantBlackTiles + newBlackTiles
}

fun findFinalTile(steps: List<String>): Tile {
    return steps.fold(Tile(0, 0)) { acc, s -> acc.step(s) }
}

fun parse(line: String): List<String> {
    return """(e|se|sw|w|nw|ne)""".toRegex().findAll(line).toList().map { it.groupValues.first() }
}

// x is w/e, y is nw/se
// ne is +1 in both, sw is -1 in both
data class Tile(val x: Int, val y: Int) {
    fun step(step: String): Tile {
        return when (step) {
            "e" -> this.copy(x = x + 1)
            "w" -> this.copy(x = x - 1)
            "nw" -> this.copy(y = y + 1)
            "se" -> this.copy(y = y - 1)
            "ne" -> this.copy(x = x + 1, y = y + 1)
            "sw" -> this.copy(x = x - 1, y = y - 1)
            else -> throw IllegalArgumentException("unknown step: $step")
        }
    }

    fun findNeighbors(): Set<Tile> {
        return setOf(
            this.copy(x = x + 1), // to the east
            this.copy(x = x - 1), // to the west
            this.copy(y = y + 1), // to the nw
            this.copy(y = y - 1), // to the se
            this.copy(x = x + 1, y = y + 1), // to the ne
            this.copy(x = x - 1, y = y - 1)  // to the sw
        )
    }
}