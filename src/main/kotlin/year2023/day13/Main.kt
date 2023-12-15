package year2023.day13

import kotlin.math.min

fun main() {
    println(part1(SAMPLE)) // 405
    println(part1(INPUT)) // 35232

    println(part2(SAMPLE)) // 400
    println(part2(INPUT)) // 37982
}

fun part1(input: String): Int {
    val patterns = readLineGroups(input.drop(1))

    val sum = patterns.sumOf {
        val row = checkRows(it)
        val col = checkCols(it)
        val result = 100 * row + col
        result
    }
    return sum
}

fun part2(input: String): Int {
    val patterns = readLineGroups(input.drop(1))

    val sum = patterns.sumOf {
        val row = checkRows2(it)
        val col = checkCols2(it)
        val result = 100 * row + col
        result
    }
    return sum
}

fun checkRows(pattern: List<String>): Int {
    return (1..pattern.lastIndex).filter { reflectionLine ->
        // the reflection line splits the pattern in two parts
        val rowsAbove = reflectionLine
        val rowsBelow = pattern.size - reflectionLine
        val rowsToCheck = min(rowsAbove, rowsBelow)

        // starting from the reflection line, we go up and down row by row and check if those rows are equal
        (0 until rowsToCheck).all {
            val upperRow = reflectionLine - it - 1
            val lowerRow = reflectionLine + it
            pattern[upperRow] == pattern[lowerRow]
        }
    }.sum()
}

fun checkCols(pattern: List<String>): Int {
    return (1..pattern[0].lastIndex).filter { reflectionLine ->
        // the reflection line splits the pattern in two parts
        val colsLeft = reflectionLine
        val colsRight = pattern[0].length - reflectionLine
        val colsToCheck = min(colsLeft, colsRight)

        // starting from the reflection line, we go left and right col by col and check if those cols are equal
        (0 until colsToCheck).all {
            val leftCol = reflectionLine - it - 1
            val rightCol = reflectionLine + it
            pattern.all {
                it[leftCol]== it[rightCol]
            }
        }
    }.sum()
}

fun checkRows2(pattern: List<String>): Int {
    return (1..pattern.lastIndex).filter { reflectionLine ->
        // the reflection line splits the pattern in two parts
        val rowsAbove = reflectionLine
        val rowsBelow = pattern.size - reflectionLine
        val rowsToCheck = min(rowsAbove, rowsBelow)

        // starting from the reflection line, we go up and down row by row and check if in all those rows we have exactly one smudge
        (0 until rowsToCheck).sumOf {
            val upperRow = reflectionLine - it - 1
            val lowerRow = reflectionLine + it
            rowDiff(pattern[upperRow], pattern[lowerRow])
        } == 1 // we have exactly one diff in all rows -> exactly one smudge
    }.sum()
}

fun rowDiff(row1: String, row2: String): Int {
    return row1.zip(row2).count { it.first != it.second }
}

fun checkCols2(pattern: List<String>): Int {
    return (1..pattern[0].lastIndex).filter { reflectionLine ->
        // the reflection line splits the pattern in two parts
        val colsLeft = reflectionLine
        val colsRight = pattern[0].length - reflectionLine
        val colsToCheck = min(colsLeft, colsRight)

        // starting from the reflection line, we go left and right col by col and check if those cols are equal
        (0 until colsToCheck).sumOf {
            val leftCol = reflectionLine - it - 1
            val rightCol = reflectionLine + it
            colDiff(pattern, leftCol, rightCol)
        } == 1 // we have exactly one diff in all cols -> exactly one smudge
    }.sum()
}

fun colDiff(pattern: List<String>, col1: Int, col2: Int): Int {
    return pattern.count { it[col1] != it[col2] }
}

fun readLineGroups(input: String): List<MutableList<String>> {
    return input.lines()
        .fold(mutableListOf()) { acc, line ->
            if (line.isEmpty()) {
                acc.add(mutableListOf())
            } else {
                if (acc.isEmpty()) {
                    acc.add(mutableListOf())
                }
                acc.last().add(line)
            }
            acc
        }
}