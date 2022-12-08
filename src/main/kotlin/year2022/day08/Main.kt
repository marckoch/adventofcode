package year2022.day08

fun main() {
    part1(SAMPLE)
    part1(INPUT)

    part2(SAMPLE)
    part2(INPUT)
}

fun part1(input: String) {
    val matrix = read(input)

    val count = matrix.indices.flatMap { row ->
        matrix[0].indices.map { col ->
            isVisible(row, col, matrix)
        }
    }.count { it }

    println("part1: $count")
}

fun part2(input: String) {
    val matrix = read(input)

    val maxScore = matrix.indices.flatMap { row ->
        matrix[0].indices.map { col ->
            calcScore(row, col, matrix)
        }
    }.max()

    println("part2: $maxScore")
}

fun calcScore(r: Int, c: Int, matrix: Matrix): Int {
    val rMax = matrix.lastIndex
    val cMax = matrix[0].lastIndex

    // edge trees always have score 0 because one side has no trees
    if (r == 0 || r == rMax) return 0
    if (c == 0 || c == cMax) return 0

    val currentTree = matrix[r][c]

    // count visible trees to the left
    // row is const, list has to start at currentTree and go outside (so downTo)
    val treesToLeft = getTreesInRow (r, c - 1 downTo 0, matrix).takeWhile { it < currentTree }
    var countLeft = treesToLeft.size
    // we have to check if we reached the edge or were blocked before
    if (treesToLeft.size < c) countLeft++

    // count visible trees to the right
    // row is const, list has to start at currentTree and go outside (so ..)
    val treesToRight = getTreesInRow(r, c + 1..cMax, matrix).takeWhile { it < currentTree }
    var countRight = treesToRight.size
    // we have to check if we reached the edge or were blocked before
    if (treesToRight.size < cMax - c) countRight++

    // count visible trees to the top
    // col is const, list has to start at currentTree and go outside (so downTo)
    val treesToTop = getTreesInCol(r - 1 downTo 0, c, matrix).takeWhile { it < currentTree }
    var countTop = treesToTop.size
    // we have to check if we reached the edge or were blocked before
    if (treesToTop.size < r) countTop++

    // count visible trees to the bottom
    // row is const, list has to start at currentTree and go outside (so ..)
    val treesToBottom = getTreesInCol(r + 1..rMax, c, matrix).takeWhile { it < currentTree }
    var countBottom = treesToBottom.size
    // we have to check if we reached the edge or were blocked before
    if (treesToBottom.size < rMax - r) countBottom++

    return countLeft * countBottom * countRight * countTop
}

fun isVisible(r: Int, c: Int, matrix: Matrix): Boolean {
    val rMax = matrix.lastIndex
    val cMax = matrix[0].lastIndex

    // edge trees are always visible
    if (r == 0 || r == rMax) return true
    if (c == 0 || c == cMax) return true

    val currentTree = matrix[r][c]

    // visible from left means: all trees to the left ([r][0 <= i < c]) are smaller, row r is constant
    val visFromLeft = getTreesInRow(r, 0 until c,  matrix).all { it < currentTree }
    if (visFromLeft) return true

    // visible from right means: all trees to the right ([r][c+1 <= x <= cMax]) are smaller, row r is constant
    val visFromRight = getTreesInRow(r, c + 1..cMax, matrix).all { it < currentTree }
    if (visFromRight) return true

    // visible from top means: all trees above ([0 <= x < r][c]) are smaller, col c is constant
    val visFromTop = getTreesInCol(0 until r, c, matrix).all { it < currentTree }
    if (visFromTop) return true

    // visible from bottom means: all trees below ([r+1 <= x <= rMax][c]) are smaller, col c is constant
    val visFromBottom = getTreesInCol(r + 1..rMax, c, matrix).all { it < currentTree }
    if (visFromBottom) return true

    return false
}

fun getTreesInRow(row: Int, cols: IntRange, matrix: Matrix): List<Int> = cols.map { matrix[row][it] }
fun getTreesInRow(row: Int, cols: IntProgression, matrix: Matrix): List<Int> = cols.map { matrix[row][it] }

fun getTreesInCol(rows: IntRange, col: Int, matrix: Matrix): List<Int> = rows.map { matrix[it][col] }
fun getTreesInCol(rows: IntProgression, col: Int, matrix: Matrix): List<Int> = rows.map { matrix[it][col] }


fun read(input: String): Matrix {
    return input.lines()
        .map { row ->
            row.toCharArray()
                .map { it.digitToInt() }
        }
}

typealias Matrix = List<List<Int>>