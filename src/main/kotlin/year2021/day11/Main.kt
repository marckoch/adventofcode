package year2021.day11

fun main() {
    part1(SAMPLE)
    part1(INPUT)

    part2(SAMPLE)
    part2(INPUT)
}

private fun part1(input: String) {
    var matrix = input.lines().map { l -> l.toCharArray().map { c -> c.digitToInt() }.toMutableList() }
    println("before:")
    printMatrix(matrix)

    var flashCount = 0

    repeat(100) {
        matrix = incAll(matrix)
//    println("after inc")
//    printMatrix(m)

        while (hasFlashableCells(matrix)) {
            flashCount += processBursts(matrix)
//        println("after bursts")
//        printMatrix(m)
        }

        if ((it + 1) % 10 == 0) {
            println("after step ${it + 1}")
            printMatrix(matrix)
        }
    }

    println("part1: $flashCount")
}

private fun part2(input: String) {
    var matrix = input.lines().map { l -> l.toCharArray().map { c -> c.digitToInt() }.toMutableList() }

    var step = 0
    while (true) {
        step += 1

        matrix = incAll(matrix)

        while (hasFlashableCells(matrix)) {
            processBursts(matrix)
        }

        if (matrix.all { row -> row.all { i ->  i == 0 } }) {
            println("part2: $step")
            return
        }
    }
}

private fun hasFlashableCells(matrix: Matrix): Boolean = matrix.any { row -> row.any { it > 9 } }

private fun processBursts(matrix: Matrix): Int {
    var flashCount = 0
    for (row in matrix.indices) {
        for (col in matrix[0].indices) {
            if (matrix[row][col] > 9) {
                // inc all neighbors
                for (dr in -1..1) {
                    for (dc in -1..1) {
                        val r = row + dr
                        val c = col + dc

                        // must be inside matrix
                        if (r in matrix.indices && c in matrix[0].indices) {

                            if (dr == 0 && dc == 0) {
                                // burst field itself
                                matrix[r][c] = 0
                                flashCount += 1
                            } else {
                                // valid neighbor, don't update when it is 0! it only flashes once per step!
                                if (matrix[r][c] > 0)
                                    matrix[r][c] = matrix[r][c] + 1
                            }

                        }
                    }
                }
            }
        }
    }
    return flashCount
}

private fun printMatrix(matrix: Matrix) {
    for (row in matrix) {
        println(row.joinToString(""))
    }
}

private fun incAll(matrix: Matrix): Matrix = matrix.map { row -> row.map { it + 1 }.toMutableList() }

typealias Matrix = List<MutableList<Int>>