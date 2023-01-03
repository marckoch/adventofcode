package year2020.day20

import kotlin.math.sqrt

fun main() {
    part1(SAMPLE)
    part1(INPUT)

    part2(SAMPLE)
    part2(INPUT)
}

val TILE_NAME = """Tile (\d+):""".toRegex()

const val seaMonster = """                  # 
#    ##    ##    ###
 #  #  #  #  #  #   """

const val SEA_MONSTER_MARKER = 'O'

fun part1(input: String) {
    val tiles = readLineChunks(input).map { lines -> buildTile(lines) }

    val matrix = findTileArrangement(tiles.toSet())
    val n = matrix.lastIndex
    println("part1: " + (matrix[0][0].id.toLong() * matrix[0][n].id * matrix[n][0].id * matrix[n][n].id))
}

fun part2(input: String) {
    val tiles = readLineChunks(input).map { lines -> buildTile(lines) }.toSet()

    val matrix = findTileArrangement(tiles)
//    print(matrix)
    // printLines(matrix)

    alignTiles(matrix)
    //println("tiles aligned:")
    //printLines(matrix)

    removeEdges(matrix)
    //println("edges removed:")
    //printLines(matrix)

    val imageTile = mergeTiles(matrix)
    //println("tiles merged:")
    //imageTile.lines.forEach { println(it) }

    val seaMonsterPoints = getSeaMonsterPoints(seaMonster.lines())

    val tileWithMonsters = searchForTileWithMonsters(imageTile, seaMonsterPoints)

    val markedMonsters = markSeaMonsters(tileWithMonsters, seaMonsterPoints)
    //println("seamonsters marked:")
    //markedMonsters.forEach { println(it.joinToString("")) }

    markedMonsters.sumOf { line -> line.count { c -> c == '#' } }.let { println("part2: $it") }
}

// find arrangement of tiles in n * n matrix
fun findTileArrangement(allTiles: Set<Tile>): Array<Array<Tile>> {
    val n = sqrt(allTiles.size.toDouble()).toInt()

    val matrix = Array(n) { Array(n) { Tile(0, listOf("#")) } }
    val freeTiles = allTiles.toMutableSet()

    for (row in matrix.indices) {
        for (col in matrix[0].indices) {
            // how many neighbors should a tile in this row/col have?
            // 2 for corner pieces, 3 for edge pieces and 4 for pieces inside matrix
            var numberOfNeighbors = 4
            if (row == 0 || row == matrix.lastIndex) numberOfNeighbors--
            if (col == 0 || col == matrix[0].lastIndex) numberOfNeighbors--

            // in the free tiles find a matching next tile

            val nextPiece = if (row == 0 && col == 0) {
                // we just pick the corner piece with the lowest id and start with that one
                freeTiles.filter { it.hasNumberOfNeighbors(allTiles, numberOfNeighbors) }.minBy { it.id }
            } else if (row == 0 && col > 0) {
                // next tile in top row will have tile to the left (row/col-1) as its neighbor
                freeTiles.filter { it.hasNumberOfNeighbors(allTiles, numberOfNeighbors) }
                    // here we can still have more options, we just pick the first one
                    .first { it.hasNeighborsWithIds(allTiles, setOf(matrix[row][col - 1].id)) }
            } else if (row > 0 && col == 0) {
                // next tile in left edge will have tile above (row-1/col) as its neighbor
                freeTiles.filter { it.hasNumberOfNeighbors(allTiles, numberOfNeighbors) }
                    // now we are pretty much fixed in our choices, so 'single' it is, there can only ever be one candidate
                    .single { it.hasNeighborsWithIds(allTiles, setOf(matrix[row - 1][col].id)) }
            } else if (row > 0 && col > 0) {
                // next tile within matrix will have tile above (row-1/col) as its neighbor AND tile to the left (row/col-1) as its neighbor
                freeTiles.filter { it.hasNumberOfNeighbors(allTiles, numberOfNeighbors) }
                    // now we are pretty much fixed in our choices, so 'single' it is, there can only ever be one candidate
                    .single {
                        it.hasNeighborsWithIds(allTiles, setOf(matrix[row - 1][col].id, matrix[row][col - 1].id))
                    }
            } else
                throw IllegalStateException("cannot find more pieces!")

            matrix[row][col] = nextPiece
            freeTiles -= nextPiece
        }
    }
    return matrix
}

// in the matrix we align (rotate, flip) all tiles until their edges are correct to its neighbors
fun alignTiles(matrix: Array<Array<Tile>>) {
    for (row in matrix.indices) {
        for (col in matrix.first().indices) {
            matrix[row][col] = alignTileToNeighbors(matrix, row, col)
        }
    }
}

// in the matrix we align (rotate, flip) tile at row|col until its edges are correct to its neighbors
fun alignTileToNeighbors(matrix: Array<Array<Tile>>, row: Int, col: Int): Tile {
    val tile = matrix[row][col]

    val sharedEdgeWithTopNeighbor = if (row > 0) {
        val topNeighbor = matrix[row - 1][col]
        tile.edges intersect topNeighbor.edges
    } else
        emptySet()

    val sharedEdgeWithBottomNeighbor = if (row < matrix.lastIndex) {
        val bottomNeighbor = matrix[row + 1][col]
        tile.edges intersect bottomNeighbor.edges
    } else
        emptySet()

    val sharedEdgeWithLeftNeighbor = if (col > 0) {
        val leftNeighbor = matrix[row][col - 1]
        tile.edges intersect leftNeighbor.edges
    } else
        emptySet()

    val sharedEdgeWithRightNeighbor = if (col < matrix.lastIndex) {
        val rightNeighbor = matrix[row][col + 1]
        tile.edges intersect rightNeighbor.edges
    } else
        emptySet()

    return tile.switchUntilEdgesAreMet(
        sharedEdgeWithTopNeighbor,
        sharedEdgeWithBottomNeighbor,
        sharedEdgeWithLeftNeighbor,
        sharedEdgeWithRightNeighbor
    )
}

fun removeEdges(matrix: Array<Array<Tile>>): Array<Array<Tile>> {
    for (row in matrix.indices) {
        for (col in matrix.first().indices) {
            matrix[row][col] = matrix[row][col].removeEdges()
        }
    }
    return matrix
}

// merge all Tiles in matrix to one big Tile
fun mergeTiles(matrix: Array<Array<Tile>>): Tile {
    val tileCount = matrix.size
    val tileSize = matrix[0][0].lines.size

    return Tile(0, (0 until (tileCount * tileSize)).map { row ->
        matrix[row / tileSize].joinToString("") { it.lines[row % tileSize] }
    })
}

fun print(matrix: Array<Array<Tile>>) {
    matrix.forEach { tiles -> tiles.map { it.id }.toList().let { println(it) } }
}

fun printLines(matrix: Array<Array<Tile>>) {
    val tileCount = matrix.size
    val tileSize = matrix[0][0].lines.size

    for (row in 0 until (tileCount * tileSize)) {
        if (row % tileSize == 0) println()
        for (col in 0 until (tileCount * tileSize)) {
            if (col % tileSize == 0) print(" ")
            print(matrix[row / tileSize][col / tileSize].lines[row % tileSize][col % tileSize])
        }
        println()
    }
}

fun buildTile(lines: List<String>): Tile {
    val (id) = TILE_NAME.find(lines.first())!!.destructured

    return Tile(id.toInt(), lines.drop(1))
}

fun readLineChunks(input: String): List<List<String>> {
    return input.lines().fold(mutableListOf<MutableList<String>>()) { list, line ->
        if (line.isEmpty()) {
            list.add(mutableListOf())
        } else {
            if (list.isEmpty()) {
                list.add(mutableListOf())
            }
            list.last().add(line.trim())
        }
        list
    }
}

// get a Set of all Points of sea monster
fun getSeaMonsterPoints(monster: List<String>): Set<Pair<Int, Int>> {
    return monster.indices.flatMap { row ->
        monster[row].indices.mapNotNull { col ->
            if (monster[row][col] == '#') row to col
            else null
        }
    }.toSet()
}

// we search for the monsters in the given tile, if none are found
// we change the tile (rotate, flip), until we find monsters
fun searchForTileWithMonsters(imageTile: Tile, seaMonsterPoints: Set<Pair<Int, Int>>): Tile {
    // first {it} is identity operation
    val operations = listOf(
        { it }, Tile::rotateLeft, Tile::rotateLeft, Tile::rotateLeft,
        Tile::flipLeftRight, Tile::rotateLeft, Tile::rotateLeft, Tile::rotateLeft
    )

    var newTile = imageTile
    for (op in operations) {
        newTile = op(newTile)
        if (findSeaMonster(newTile, seaMonsterPoints))
            return newTile
    }
    throw IllegalStateException("no tile with monsters found!")
}

// we move the set of sea monster points over the tile until all of those points cover a '#'
fun findSeaMonster(tile: Tile, seaMonsterPoints: Set<Pair<Int, Int>>): Boolean {
    for (row in 0 until tile.lines.size - seaMonsterPoints.maxOf { it.first }) {
        for (col in 0 until tile.lines.first().length - seaMonsterPoints.maxOf { it.second }) {
            val movedSeaMonsterPoints = seaMonsterPoints.map { it.first + row to it.second + col }.toSet()
            if (checkForSeaMonster(tile.lines, movedSeaMonsterPoints)) {
                return true
            }
        }
    }
    return false
}

// we mark the sea monsters by exchanging a '#' with a 'O'
fun markSeaMonsters(tile: Tile, seaMonsterPoints: Set<Pair<Int, Int>>): List<CharArray> {
    val matrix = tile.lines.map { it.toCharArray() }
    for (row in 0 until matrix.size - seaMonsterPoints.maxOf { it.first }) {
        for (col in 0 until matrix.first().size - seaMonsterPoints.maxOf { it.second }) {
            val movedSeaMonsterPoints = seaMonsterPoints.map { it.first + row to it.second + col }.toSet()
            if (checkForSeaMonster(tile.lines, movedSeaMonsterPoints)) {
                movedSeaMonsterPoints.forEach {
                    matrix[it.first][it.second] = SEA_MONSTER_MARKER
                }
            }
        }
    }
    return matrix
}

// true if ALL points of a sea monster cover a '#'
fun checkForSeaMonster(matrix: List<String>, seaMonsterPoints: Set<Pair<Int, Int>>): Boolean {
    return seaMonsterPoints.all { pair -> matrix[pair.first][pair.second] == '#' }
}

class Tile(val id: Int, val lines: List<String>) {
    val edges: Set<String>

    init {
        val top = lines.first()
        val bottom = lines.last()
        val left = lines.map { it[0] }.joinToString("")
        val right = lines.map { it[lines[0].lastIndex] }.joinToString("")

        val e = listOf(top, bottom, left, right)

        edges = (e + e.map { it.reversed() }).toSet()
    }

    private fun getNeighbors(tiles: Set<Tile>): Set<Tile> {
        return (tiles - this).filter { (edges intersect (it).edges).isNotEmpty() }.toSet()
    }

    fun hasNumberOfNeighbors(allTiles: Set<Tile>, numberOfNeighbors: Int): Boolean {
        return getNeighbors(allTiles).size == numberOfNeighbors
    }

    fun hasNeighborsWithIds(allTiles: Set<Tile>, ids: Set<Int>): Boolean {
        return getNeighbors(allTiles).map { t -> t.id }
            .containsAll(ids)
    }

    // turn, flip and rotate until the given edges are met
    fun switchUntilEdgesAreMet(top: Set<String>, bottom: Set<String>, left: Set<String>, right: Set<String>): Tile {
        // first {it} is identity operation
        val operations = listOf(
            { it }, Tile::rotateLeft, Tile::rotateLeft, Tile::rotateLeft,
            Tile::flipLeftRight, Tile::rotateLeft, Tile::rotateLeft, Tile::rotateLeft
        )

        var newTile = this
        for (op in operations) {
            newTile = op(newTile)
            if (newTile.matchesEdges(top, bottom, left, right))
                return newTile
        }

        throw IllegalStateException("can not find a correct alignment for tile!")
    }

    private fun matchesEdges(top: Set<String>, bottom: Set<String>, left: Set<String>, right: Set<String>): Boolean {
        val topMatches = top.isEmpty() || hasTopEdge(top)
        val bottomMatches = bottom.isEmpty() || hasBottomEdge(bottom)
        val leftMatches = left.isEmpty() || hasLeftEdge(left)
        val rightMatches = right.isEmpty() || hasRightEdge(right)
        return topMatches && bottomMatches && leftMatches && rightMatches
    }

    private fun hasTopEdge(top: Set<String>): Boolean {
        return lines.first() in top
    }

    private fun hasBottomEdge(bottom: Set<String>): Boolean {
        return lines.last() in bottom
    }

    private fun hasLeftEdge(left: Set<String>): Boolean {
        return lines.map { it[0] }.joinToString("") in left
    }

    private fun hasRightEdge(right: Set<String>): Boolean {
        return lines.map { it[lines.lastIndex] }.joinToString("") in right
    }

    fun flipLeftRight(): Tile {
        return Tile(id, lines.map { it.reversed() })
    }

    fun rotateLeft(): Tile {
        return Tile(id, lines.indices.reversed().map { index -> lines.map { it[index] }.joinToString("") })
    }

    fun removeEdges(): Tile {
        return Tile(id, lines.drop(1).dropLast(1).map { it.substring(1, it.lastIndex) })
    }

    override fun toString(): String {
        return "($id, edges=$edges)"
    }
}