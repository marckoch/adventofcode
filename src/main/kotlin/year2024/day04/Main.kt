package year2024.day04

fun main() {
    AOC2024D04(SAMPLE).solvePart1().let { println(it) } // 4
    AOC2024D04(SAMPLE2).solvePart1().let { println(it) } // 18
    AOC2024D04(INPUT).solvePart1().let { println(it) }  // 2545

    AOC2024D04(SAMPLE2).solvePart2().let { println(it) } // 9
    AOC2024D04(INPUT).solvePart2().let { println(it) }  // 1886
}

class AOC2024D04(input: String) {
    private val field = input.lines()
    private val rows = field.size
    private val cols = field[0].length

    fun solvePart1(): Int {
        val XMAS = "XMAS"

        return allPoints().sumOf { point ->
            var count = 0
            count += match(XMAS, point, 0, 1)   // check right
            count += match(XMAS, point, 1, 1)   // check right down
            count += match(XMAS, point, 1, 0)   // check down
            count += match(XMAS, point, 1, -1)  // check left down
            count += match(XMAS, point, 0, -1)  // check left
            count += match(XMAS, point, -1, -1) // check left up
            count += match(XMAS, point, -1, 0)  // check up
            count += match(XMAS, point, -1, 1)  // check up right
            count
        }
    }

    fun solvePart2(): Int {
        return allPoints().sumOf { point ->
            if (getCharAt(point) == 'A') {
                findXmasAroundA(point.first, point.second)
            } else {
                0
            }
        }
    }

    private fun match(XMAS: String, point: Point, rowStep: Int, colStep: Int): Int {
        try {
            XMAS.forEachIndexed { index, c ->
                if (field[point.first + index * rowStep][point.second + index * colStep] != c) {
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
    private fun findXmasAroundA(rowA: Int, colA: Int): Int {
        try {
            if (isWord(Type.FALLING, "MAS", rowA, colA) &&
                isWord(Type.RISING, "SAM", rowA, colA)) {
                return 1
            }
            if (isWord(Type.FALLING, "MAS", rowA, colA) &&
                isWord(Type.RISING, "MAS", rowA, colA)) {
                return 1
            }
            if (isWord(Type.FALLING, "SAM", rowA, colA) &&
                isWord(Type.RISING, "SAM", rowA, colA)) {
                return 1
            }
            if (isWord(Type.FALLING, "SAM", rowA, colA) &&
                isWord(Type.RISING, "MAS", rowA, colA)) {
                return 1
            }
            return 0
        } catch (e: IndexOutOfBoundsException) {
            return 0
        }
    }

    private fun isWord(type: Type, word: String, startRow: Int, startCol: Int) =
        when (type) {
            Type.FALLING -> isFalling(startRow, startCol, word.first(), word.last())
            Type.RISING -> isRising(startRow, startCol, word.first(), word.last())
        }

    private fun isFalling(startRow: Int, startCol: Int, topLeftChar: Char, bottomRightChar: Char) =
        field[startRow - 1][startCol - 1] == topLeftChar &&
                field[startRow + 1][startCol + 1] == bottomRightChar

    private fun isRising(startRow: Int, startCol: Int, bottomLeftChar: Char, topRightChar: Char) =
        field[startRow + 1][startCol - 1] == bottomLeftChar &&
                field[startRow - 1][startCol + 1] == topRightChar

    private fun allPoints(): List<Point> {
        val points = mutableListOf<Point>()
        for (row in 0 until rows) {
            for (col in 0 until cols) {
                points.add(Pair(row, col))
            }
        }
        return points
    }

    enum class Type {
        FALLING, RISING
    }

    private fun getCharAt(point: Point): Char {
        return field[point.first][point.second]
    }
}

typealias Point = Pair<Int, Int>