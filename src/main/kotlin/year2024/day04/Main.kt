package year2024.day04

fun main() {
    part1(SAMPLE).let { println(it) } // 4
    part1(SAMPLE2).let { println(it) } // 18
    part1(INPUT).let { println(it) }  // 2545

    part2(SAMPLE2).let { println(it) } // 9
    part2(INPUT).let { println(it) } // 1886
}

fun part1(input: String): Int {
    val field = input.lines()
    val rows = field.size
    val cols = field[0].length

    val XMAS = "XMAS"

    var count = 0

    for (row in 0 until rows) {
        for (col in 0 until cols) {
            count += match(field, XMAS, row, col, 0, 1)   // check right
            count += match(field, XMAS, row, col, 1, 1)   // check right down
            count += match(field, XMAS, row, col, 1, 0)   // check down
            count += match(field, XMAS, row, col, 1, -1)  // check left down
            count += match(field, XMAS, row, col, 0, -1)  // check left
            count += match(field, XMAS, row, col, -1, -1) // check left up
            count += match(field, XMAS, row, col, -1, 0)  // check up
            count += match(field, XMAS, row, col, -1, 1)  // check up right
        }
    }

    return count
}

fun part2(input: String): Int {
    val field = input.lines()
    val rows = field.size
    val cols = field[0].length

    var count = 0

    for (row in 0 until rows) {
        for (col in 0 until cols) {
            if (field[row][col] == 'A') {
                count += findXmasAroundA(field, row, col)
            }
        }
    }

    return count
}

fun match(field: List<String>, XMAS: String, startRow: Int, startCol: Int, rowStep: Int, colStep: Int): Int {
    try {
        XMAS.forEachIndexed { index, c ->
            if (field[startRow + index * rowStep][startCol + index * colStep] != c) {
                return 0
            }
        }
        // println("match at $startRow, $startCol, steps: $rowStep, $colStep")
        return 1
    } catch (e: IndexOutOfBoundsException) {
        return 0
    }
}

// we start from the A in the middle and check the corners
fun findXmasAroundA(field: List<String>, rowA: Int, colA: Int): Int {
    try {
        if (isWord(Type.FALLING, "MAS", field, rowA, colA) &&
            isWord(Type.RISING, "SAM", field, rowA, colA)) {
            return 1
        }
        if (isWord(Type.FALLING, "MAS", field, rowA, colA) &&
            isWord(Type.RISING, "MAS", field, rowA, colA)) {
            return 1
        }
        if (isWord(Type.FALLING, "SAM", field, rowA, colA) &&
            isWord(Type.RISING, "SAM", field, rowA, colA)) {
            return 1
        }
        if (isWord(Type.FALLING, "SAM", field, rowA, colA) &&
            isWord(Type.RISING, "MAS", field, rowA, colA)) {
            return 1
        }
        return 0
    } catch (e: IndexOutOfBoundsException) {
        return 0
    }
}

private fun isWord(type: Type, word: String, field: List<String>, startRow: Int, startCol: Int) =
    when (type) {
        Type.FALLING -> isFalling(field, startRow, startCol, word.first(), word.last())
        Type.RISING -> isRising(field, startRow, startCol, word.first(), word.last())
    }

private fun isFalling(field: List<String>, startRow: Int, startCol: Int, topLeftChar: Char, bottomRightChar: Char) =
    field[startRow - 1][startCol - 1] == topLeftChar &&
    field[startRow + 1][startCol + 1] == bottomRightChar

private fun isRising(field: List<String>, startRow: Int, startCol: Int, bottomLeftChar: Char, topRightChar: Char) =
    field[startRow + 1][startCol - 1] == bottomLeftChar &&
    field[startRow - 1][startCol + 1] == topRightChar

enum class Type {
    FALLING, RISING
}