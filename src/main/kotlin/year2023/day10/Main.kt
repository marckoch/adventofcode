package year2023.day10

fun main() {
    part1(SAMPLE1).let { println(it) }  // 4
    part1(SAMPLE2).let { println(it) }  // 8

    part1(INPUT).let { println(it) } // 7107
}

fun part1(input: String): Int {
    // input.lines().forEach { println(it) }
    val rows = input.lines().withIndex()
    val sPos = rows.filter { it.value.contains("S") }.map { Point(it.index,it.value.indexOf("S")) }.first()
    // println("S is at (row|col) = $sPos")

    val maxRow = input.lines().lastIndex
    val maxCol = input.lines().first().lastIndex

    val nextForS = getPossiblePaths(rows, sPos, maxRow, maxCol, listOf(sPos))
    // nextForS.forEach { println("next for S $sPos -> $it") }

    // there are two paths from S
    // remember path to avoid going back the path
    var next1 = nextForS[0]
    val path1 = mutableListOf(sPos, next1)
    var next2 = nextForS[1]
    val path2 = mutableListOf(sPos, next2)
    var stepCount = 1

    while (next1 != next2) {
        // println("step $stepCount: next1 = $next1, next2 = $next2")
        val nextFor1 = getPossiblePaths(rows, next1, maxRow, maxCol, path1)
        if (nextFor1.size != 1) {
            throw IllegalStateException("no exact next for $next1")
        }
        val nextFor2 = getPossiblePaths(rows, next2, maxRow, maxCol, path2)
        if (nextFor2.size != 1) {
            throw IllegalStateException("no exact next for $next1")
        }

//        nextFor1.forEach { println("next for $next1 -> $it") }
//        nextFor2.forEach { println("next for $next2 -> $it") }

        next1 = nextFor1.first()
        path1.add(next1)
        next2 = nextFor2.first()
        path2.add(next2)

        stepCount++
    }

    return stepCount
}

fun getPossiblePaths(rows: Iterable<IndexedValue<String>>, currentPos: Point, maxRow: Int, maxCol: Int, path: List<Point>): List<Point> {
    val currentField = rows.elementAt(currentPos.row).value[currentPos.col]

    val candidates = mutableListOf<Point>()

    // check if we can go up
    val above = currentPos.above()
    if (isInField(above, maxRow, maxCol) && above !in path) {
        val fieldAbove = rows.elementAt(above.row).value[above.col]
        if (currentField in listOf('S', '|', 'J', 'L') &&
            fieldAbove in listOf('|', '7', 'F')) {
            candidates.add(above)
        }
    }
    // check if we can go down
    val below = currentPos.below()
    if (isInField(below, maxRow, maxCol) && below !in path) {
        val fieldBelow = rows.elementAt(below.row).value[below.col]
        if (currentField in listOf('S', '|', '7', 'F') &&
            fieldBelow in listOf('|', 'J', 'L')) {
            candidates.add(below)
        }
    }
    // check if we can go left
    val left = currentPos.left()
    if (isInField(left, maxRow, maxCol) && left !in path) {
        val fieldLeft = rows.elementAt(left.row).value[left.col]
        if (currentField in listOf('S', '-', '7', 'J') &&
            fieldLeft in listOf('-', 'F', 'L')) {
            candidates.add(left)
        }
    }
    // check if we can go right
    val right = currentPos.right()
    if (isInField(right, maxRow, maxCol) && right !in path) {
        val fieldRight = rows.elementAt(right.row).value[right.col]
        if (currentField in listOf('S', '-', 'F', 'L') &&
            fieldRight in listOf('-', 'J', '7')) {
            candidates.add(right)
        }
    }

    if (candidates.isEmpty()) {
        throw IllegalStateException("no candidates for $currentPos")
    }
    return candidates
}

fun isInField(p: Point, maxRow: Int, maxCol: Int): Boolean {
    return p.row in 0..maxRow && p.col in 0..maxCol
}

data class Point(val row: Int, val col: Int) {
    override fun toString(): String {
        return "($row|$col)"
    }

    fun above() = Point(row - 1, col)
    fun below() = Point(row + 1, col)
    fun left() = Point(row, col - 1)
    fun right() = Point(row, col + 1)
}