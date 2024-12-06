package year2024.day06

fun main() {
    AOC2024D06(SAMPLE).solvePart1().let { println(it) } // 41
    AOC2024D06(INPUT).solvePart1().let { println(it) } // 5030

    AOC2024D06(SAMPLE).solvePart2().let { println(it) } // 6
    AOC2024D06(INPUT).solvePart2().let { println(it) } // 1928
}

class AOC2024D06(input: String) {
    private val initialMap = Map(input.lines())

    fun solvePart1(): Int {
        val newMap = Map(initialMap.lines)
        while (newMap.isGuardInField()) {
//            newMap.print()
            newMap.nextGuardStep()
        }
//        newMap.print()
        return newMap.countVisitedFields()
    }

    fun solvePart2(): Int {
        return initialMap.allPoints().count { point ->
            if (initialMap.getField(point) != '#') {
                val newMap = Map(initialMap.lines)
                newMap.setField(point, '#')

                newMap.isEndingInLoop()
            } else {
                false
            }
        }
    }

    class Map(val lines: List<String>) {
        private val rows = lines.count()
        private val cols = lines.first().length
        private val fields = List(rows) { CharArray(cols) }

        private var guard = Position(Point(0, 0), Facing.UP)
        // store if a field was visited and from which direction
        private val visitedPositions: MutableMap<Point, MutableList<Facing>> = mutableMapOf()

        init {
            lines.withIndex().map { indexedLine ->
                val row = indexedLine.index
                indexedLine.value.withIndex().map { indexedChar ->
                    val col = indexedChar.index
                    fields[row][col] = indexedChar.value

                    initGuardPosition(row, col)
                }
            }
        }

        fun allPoints(): List<Point> {
            val points = mutableListOf<Point>()
            for (row in 0 until rows) {
                for (col in 0 until cols) {
                    points.add(Pair(row, col))
                }
            }
            return points
        }

        private fun initGuardPosition(row: Int, col: Int) {
            if (fields[row][col] == '^') {
                guard = Position(Point(row, col), Facing.UP)
            } else if (fields[row][col] == 'v') {
                guard = Position(Point(row, col), Facing.DOWN)
            } else if (fields[row][col] == '<') {
                guard = Position(Point(row, col), Facing.LEFT)
            } else if (fields[row][col] == '>') {
                guard = Position(Point(row, col), Facing.RIGHT)
            }
        }

        fun getField(point: Point): Char {
            return fields[point.first][point.second]
        }

        fun setField(point: Point, value: Char) {
            fields[point.first][point.second] = value
        }

        fun nextGuardStep() {
            visitedPositions.getOrPut(guard.point) { mutableListOf() }.add(guard.facing)

            // move guard
            guard = if (isFacingObstacle()) {
                guard.turnRight()
            } else {
                guard.moveForward()
            }
        }

        private fun isFacingObstacle(): Boolean {
            if (isLeavingField()) {
                return false
            }
            return getFieldAhead() == '#'
        }

        private fun getFieldAhead(): Char {
            return when (guard.facing) {
                Facing.UP -> fields[guard.point.first - 1][guard.point.second]
                Facing.DOWN -> fields[guard.point.first + 1][guard.point.second]
                Facing.LEFT -> fields[guard.point.first][guard.point.second - 1]
                Facing.RIGHT -> fields[guard.point.first][guard.point.second + 1]
            }
        }

        fun isEndingInLoop():Boolean {
            while (isGuardInField()) {
                nextGuardStep()
                if (isGuardEnteringLoop()) {
                    break
                }
            }

            return isGuardEnteringLoop()
        }

        private fun isLeavingField(): Boolean {
            return when (guard.facing) {
                Facing.UP -> (guard.point.first == 0)
                Facing.DOWN -> (guard.point.first == rows - 1)
                Facing.LEFT -> (guard.point.second == 0)
                Facing.RIGHT -> (guard.point.second == cols - 1)
            }
        }

        fun isGuardInField(): Boolean {
            return guard.point.first in 0 until rows &&
                    guard.point.second in 0 until cols
        }

        private fun isGuardEnteringLoop(): Boolean {
            // field was already visited from this direction
            return visitedPositions[guard.point]?.contains(guard.facing) == true
        }

        fun countVisitedFields(): Int {
            return visitedPositions.keys.count()
        }

        override fun toString(): String {
            val sb = StringBuilder()
            for (r in 0 until rows) {
                for (c in 0 until cols) {
                    if (visitedPositions.containsKey(Point(r, c))) {
                        sb.append('X')
                    } else {
                        sb.append(fields[r][c])
                    }
                }
                sb.append('\n')
            }
            return sb.toString()
        }

        fun print() {
            println(toString())
            println("Guard: $guard, isInField=${isGuardInField()}, visited=${countVisitedFields()}")
        }

        data class Position(val point: Point, val facing: Facing) {
            fun turnRight(): Position {
                val newFacing = when (facing) {
                    Facing.UP -> Facing.RIGHT
                    Facing.RIGHT -> Facing.DOWN
                    Facing.DOWN -> Facing.LEFT
                    Facing.LEFT -> Facing.UP
                }
//                println("turning right from $this to $newFacing")
                return Position(point, newFacing)
            }
            fun moveForward(): Position {
                val newPoint = when (facing) {
                    Facing.UP -> Point(point.first - 1, point.second)
                    Facing.DOWN -> Point(point.first + 1, point.second)
                    Facing.LEFT -> Point(point.first, point.second - 1)
                    Facing.RIGHT -> Point(point.first, point.second + 1)
                }
//                println("moving forward from $this to $newPoint")
                return Position(newPoint, facing)
            }

            override fun toString(): String {
                return "($point, $facing)"
            }
        }

        enum class Facing(val c: Char) {
            UP('^'), DOWN('V'), LEFT('<'), RIGHT('>');
        }
    }
}

typealias Point = Pair<Int, Int>