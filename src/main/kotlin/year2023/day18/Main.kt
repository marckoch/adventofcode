package year2023.day18

fun main() {
    println(part1(SAMPLE)) // 62
    println(part1(INPUT)) // 70026
}

fun part1(input: String): Int {
    var currentPos = 0 to 0
    var minCol = 0
    var maxCol = 0
    var maxRow = 0
    var minRow = 0
    val positions = mutableSetOf(currentPos)
    input.lines().drop(1).forEach { line: String ->
        val (direction, s, color) = line.split(" ")
        val steps = s.toInt()

        repeat(steps) {
            currentPos = when (direction) {
                "R" -> currentPos.first to currentPos.second + 1
                "L" -> currentPos.first to currentPos.second - 1
                "U" -> currentPos.first - 1 to currentPos.second
                "D" -> currentPos.first + 1 to currentPos.second
                else -> error("Unknown direction $direction")
            }
            positions.add(currentPos)
        }

        minCol = minOf(minCol, currentPos.second)
        maxCol = maxOf(maxCol, currentPos.second)
        minRow = minOf(minRow, currentPos.first)
        maxRow = maxOf(maxRow, currentPos.first)
    }
//    positions.forEach { println(it) }
    println("minCol = $minCol")
    println("maxCol = $maxCol")
    println("minRow = $minRow")
    println("maxRow = $maxRow")

    //print(positions, minRow, maxRow, minCol, maxCol)

    floodFill(positions, 1 to 1)

    //print(positions, minRow, maxRow, minCol, maxCol)
    return positions.size
}

fun print(fields: Set<Pair<Int, Int>>, minRow: Int, maxRow: Int, minCol: Int, maxCol: Int) {
    println("----")
    for (row in minRow..maxRow) {
        for (col in minCol..maxCol) {
            if (fields.contains(row to col)) {
                print("#")
            } else {
                print(".")
            }
        }
        println()
    }
}

fun floodFill(fields: MutableSet<Pair<Int, Int>>, from: Pair<Int, Int>) {
    val toVisit = mutableListOf(from)
    while (toVisit.isNotEmpty()) {
        val current = toVisit.removeAt(0)
        val up = current.first - 1 to current.second
        if (!fields.contains(up)) {
            fields.add(up)
            toVisit.add(up)
        }
        val down = current.first + 1 to current.second
        if (!fields.contains(down)) {
            fields.add(down)
            toVisit.add(down)
        }
        val left = current.first to current.second - 1
        if (!fields.contains(left)) {
            fields.add(left)
            toVisit.add(left)
        }
        val right = current.first to current.second + 1
        if (!fields.contains(right)) {
            fields.add(right)
            toVisit.add(right)
        }
    }
}

// StackOverflowError!!
fun floodFillRec(fields: MutableSet<Pair<Int, Int>>, from: Pair<Int, Int>) {
    val up = from.first - 1 to from.second
    if (!fields.contains(up)) {
        fields.add(up)
        floodFillRec(fields, up)
    }
    val down = from.first + 1 to from.second
    if (!fields.contains(down)) {
        fields.add(down)
        floodFillRec(fields, down)
    }
    val left = from.first to from.second - 1
    if (!fields.contains(left)) {
        fields.add(left)
        floodFillRec(fields, left)
    }
    val right = from.first to from.second + 1
    if (!fields.contains(right)) {
        fields.add(right)
        floodFillRec(fields, right)
    }
}