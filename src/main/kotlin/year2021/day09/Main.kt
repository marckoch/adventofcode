package year2021.day09

fun main() {
    part1(SAMPLE)
    part1(INPUT)

    part2(SAMPLE)
    part2(INPUT)
}

fun part1(input: String) {
    val matrix = read(input)

    getAllMatrixPoints(matrix)
        .filter { isLowPoint(it.first, it.second, matrix) }
        .sumOf { matrix[it.first][it.second] + 1 }
        .let { println("part1: $it") }
}

fun part2(input: String) {
    val matrix = read(input)

    // here we store/mark the basins
    val basins = List(matrix.size) { List(matrix[0].size) { 0 }.toMutableList() }

    // all low points are start point for a depth first search to mark the whole basin
    // each basin has its own numbers (based on 'withIndex' index)
    getAllMatrixPoints(matrix)
        .filter { isLowPoint(it.first, it.second, matrix) }
        .withIndex()
        .forEach {
            dfs(it.value.first, it.value.second, matrix, basins, it.index + 1)
        }

    val basinSizes = getAllMatrixPoints(basins)
        .map { basins[it.first][it.second] }
        .groupingBy { it }
        .eachCount()

    basinSizes
        .filter { entry -> entry.key > 0 }
        .map { entry -> entry }
        .sortedBy { entry -> entry.value }
        .reversed()
        .take(3)
        .map { entry -> entry.value }
        .reduce { acc, i -> acc * i }
        .let { println("part2: $it") }
}

fun dfs(row: Int, col: Int, matrix: Matrix, basins: Basins, basinNo: Int) {
    if (basins[row][col] > 0) return
    if (matrix[row][col] == 9) return

    basins[row][col] = basinNo
    val n = getNeighbors(row, col, matrix)
    for ((r, c) in n) {
        dfs(r, c, matrix, basins, basinNo)
    }
}

fun isLowPoint(row: Int, col: Int, matrix: Matrix): Boolean {
    // cell is low point when all neighbors are higher
    return getNeighbors(row, col, matrix)
        .map { (r, c) -> matrix[r][c] }
        .all { it > matrix[row][col] }
}

fun getNeighbors(r: Int, c: Int, matrix: Matrix): List<Pair<Int, Int>> {
    val rMax = matrix.lastIndex
    val cMax = matrix[0].lastIndex

    val n = mutableListOf<Pair<Int, Int>>()

    if (r > 0) n.add(Pair(r - 1, c))  // top
    if (r < rMax) n.add(Pair(r + 1, c))  // bottom
    if (c > 0) n.add(Pair(r, c - 1))  // left
    if (c < cMax) n.add(Pair(r, c + 1))  // right

    return n
}

fun read(input: String): Matrix {
    return input.lines()
        .map { row ->
            row.toCharArray()
                .map { it.digitToInt() }
        }
}

fun getAllMatrixPoints(matrix: Matrix): List<Pair<Int, Int>> {
    return matrix.indices.flatMap { row ->
        matrix[0].indices.map { col ->
            Pair(row, col)
        }
    }
}

typealias Matrix = List<List<Int>>
typealias Basins = List<MutableList<Int>>