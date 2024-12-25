package year2024.day15

import util.parser.readChunks

fun main() {
//    AOC2024D15(SAMPLE).solve().also(::println) // 10092
//    AOC2024D15(SAMPLE2).solve().also(::println) // 2028

//    AOC2024D15(INPUT).solve().also(::println) // 1371036

    AOC2024D15(SAMPLE).solve2().also(::println) // 9021
//    AOC2024D15(INPUT).solve2().also(::println) // 1390733 too low, 1409190 too high, 1391033 too low , 1393439 1406434
//    AOC2024D15(SAMPLE4).solve2().also(::println)
}

enum class Direction(val c: Char) {
    NORTH('^'), WEST('<'), SOUTH('v'), EAST('>');

    companion object {
        fun fromChar(pChar: Char): Direction {
            return Direction.entries.find { it.c == pChar }
                ?: throw IllegalArgumentException("Invalid char for Direction enum: $pChar")
        }
    }
}

enum class Type(val c: Char) {
    WALL('#'), BOX('O'), ROBOT('@'), FREE('.');

    companion object {
        fun fromChar(pChar: Char): Type {
            return entries.find { it.c == pChar }
                ?: throw IllegalArgumentException("Invalid char for Type enum: $pChar")
        }
    }
}

enum class Type2(val c: Char) {
    WALL('#'), BOX_LEFT('['), BOX_RIGHT(']'), ROBOT('@'), FREE('.');

    fun otherBoxHalf(): Type2 {
        return when (this) {
            BOX_LEFT -> BOX_RIGHT
            BOX_RIGHT -> BOX_LEFT
            WALL, ROBOT, FREE -> throw IllegalArgumentException("wrong input")
        }
    }
    companion object {
        fun fromChar(pChar: Char): Type2 {
            return entries.find { it.c == pChar }
                ?: throw IllegalArgumentException("Invalid char for Type enum: $pChar")
        }
    }
}

data class Point(val row: Int, val col: Int) {
    fun neighbor(dir: Direction): Point {
        return when (dir) {
            Direction.NORTH -> north()
            Direction.WEST -> west()
            Direction.SOUTH -> south()
            Direction.EAST -> east()
        }
    }

    fun north(): Point = this.copy(row = this.row - 1)
    fun south(): Point = this.copy(row = this.row + 1)
    fun west(): Point = this.copy(col = this.col - 1)
    fun east(): Point = this.copy(col = this.col + 1)

    override fun toString(): String {
        return "$row|$col"
    }
}

class Map(val lines: List<String>) {
    private val rows = lines.count()
    private val cols = lines.first().length
    private val fields = List(rows) { Array(cols) { Type.FREE } }

    private var robot = Point(0, 0)

    init {
        lines.withIndex().map { indexedLine ->
            val row = indexedLine.index
            indexedLine.value.withIndex().map { indexedChar ->
                val col = indexedChar.index
                fields[row][col] = Type.fromChar(indexedChar.value)

                if (fields[row][col] == Type.ROBOT) {
                    robot = Point(row, col)
                }
            }
        }
    }

    private fun typeOf(point: Point): Type {
        return fields[point.row][point.col]
    }

    private fun set(point: Point, value: Type) {
        fields[point.row][point.col] = value
    }

    fun tryRobotMove(dir: Direction) {
        val nextRobotField = robot.neighbor(dir)
        if (typeOf(nextRobotField) == Type.BOX && canBoxMove(nextRobotField, dir)) {
            moveBox(nextRobotField, dir)
        }
        if (typeOf(nextRobotField) == Type.FREE) {
            set(robot, Type.FREE)
            robot = nextRobotField
        }
    }

//    private fun canRobotMove(field: Point, dir: Direction): Boolean {
//        val fieldAhead = field.neighbor(dir)
//        if (typeOf(fieldAhead) == Type.FREE) return true
//        if (typeOf(fieldAhead) == Type.BOX) return canBoxMove(fieldAhead, dir)
//        return false
//    }

    private fun moveBox(field: Point, dir: Direction) {
        val fieldAhead = field.neighbor(dir)
        if (typeOf(fieldAhead) == Type.BOX && canBoxMove(fieldAhead, dir)) {
            moveBox(fieldAhead, dir)
        }
        if (typeOf(fieldAhead) == Type.FREE) {
            set(field, Type.FREE)
            set(fieldAhead, Type.BOX)
        }
    }

    private fun canBoxMove(field: Point, dir: Direction): Boolean {
        val fieldAhead = field.neighbor(dir)
        if (typeOf(fieldAhead) == Type.FREE) return true
        if (typeOf(fieldAhead) == Type.BOX) return canBoxMove(fieldAhead, dir)
        return false
    }

    fun gpsSum(): Long {
        return allPoints().filter { typeOf(it) == Type.BOX }.sumOf { 100L * it.row + it.col }
    }

    private fun allPoints(): List<Point> {
        val points = mutableListOf<Point>()
        for (row in 0 until rows) {
            for (col in 0 until cols) {
                points.add(Point(row, col))
            }
        }
        return points
    }

    override fun toString(): String {
        val sb = StringBuilder()
        for (r in 0 until rows) {
            for (c in 0 until cols) {
                if (Point(r, c) == robot) {
                    sb.append(Type.ROBOT.c)
                } else {
                    sb.append(fields[r][c].c)
                }
            }
            sb.append('\n')
        }
        return sb.toString()
    }

    fun print() {
        println(toString())
    }
}

class Map2(val lines: List<String>) {
    private val rows = lines.count()
    private val cols = lines.first().length * 2
    private val fields = List(rows) { Array(cols) { Type2.FREE } }

    private var robot = Point(0, 0)

    init {
        lines.withIndex().map { indexedLine ->
            val row = indexedLine.index
            indexedLine.value.withIndex().map { indexedChar ->
                val colLeft = 2 * indexedChar.index
                val colRight = colLeft + 1
                val (left, right) = when (indexedChar.value) {
                    '#' -> Type2.WALL to Type2.WALL
                    'O' -> Type2.BOX_LEFT to Type2.BOX_RIGHT
                    '@' -> {
                        robot = Point(row, colLeft)
                        Type2.ROBOT to Type2.FREE
                    }
                    else -> Type2.FREE to Type2.FREE
                }
                fields[row][colLeft] = left
                fields[row][colRight] = right
            }
        }
    }
    private fun typeOf(point: Point): Type2 {
        return fields[point.row][point.col]
    }
    private fun set(point: Point, value: Type2) {
        fields[point.row][point.col] = value
    }

    fun tryRobotMove(dir: Direction) {
        val nextRobotField = robot.neighbor(dir)
        if (typeOf(nextRobotField) == Type2.BOX_LEFT || typeOf(nextRobotField) == Type2.BOX_RIGHT
            && canBoxMove(nextRobotField, dir)
            ) {
            moveBox(nextRobotField, dir)
        }
        if (typeOf(nextRobotField) == Type2.FREE) {
            set(robot, Type2.FREE)
            robot = nextRobotField
        }
    }

    private fun canBoxMove(field: Point, dir: Direction): Boolean {
        val thisType = typeOf(field)
        val fieldAhead = field.neighbor(dir)
        val fieldAheadOtherHalf = if (thisType == Type2.BOX_LEFT) fieldAhead.east() else fieldAhead.west()
        val fieldOtherHalf = if (thisType == Type2.BOX_LEFT) field.east() else field.west()

        if (dir == Direction.WEST || dir == Direction.EAST) {
            if (typeOf(fieldAhead) == Type2.FREE) return true
            if (typeOf(fieldAhead) == Type2.BOX_LEFT || typeOf(fieldAhead) == Type2.BOX_RIGHT)
                return canBoxMove(fieldAhead, dir)
            return false
        }

        if (typeOf(fieldAhead) == Type2.FREE && typeOf(fieldAheadOtherHalf) == Type2.FREE) return true

        // full block
        // []
        // []
        if ( (thisType == Type2.BOX_LEFT && typeOf(fieldAhead) == Type2.BOX_LEFT) ||
             (thisType == Type2.BOX_RIGHT && typeOf(fieldAhead) == Type2.BOX_RIGHT) ) {
            return canBoxMove(fieldAhead, dir) && canBoxMove(fieldAheadOtherHalf, dir)
        }

        // half block west
        // []
        //  []
        if (thisType == Type2.BOX_LEFT && typeOf(fieldAhead) == Type2.BOX_RIGHT && typeOf(fieldAheadOtherHalf) == Type2.FREE) {
            return canBoxMove(fieldAhead, dir)
        }

        // half block east
        //  []
        // []
        if (thisType == Type2.BOX_RIGHT && typeOf(fieldAhead) == Type2.BOX_LEFT && typeOf(fieldAheadOtherHalf) == Type2.FREE) {
            return canBoxMove(fieldAhead, dir)
        }

        // double block
        // [][]
        //  []
        if ((thisType == Type2.BOX_LEFT && typeOf(fieldAhead) == Type2.BOX_RIGHT && typeOf(fieldAheadOtherHalf) == Type2.BOX_LEFT) ||
            (thisType == Type2.BOX_RIGHT && typeOf(fieldAhead) == Type2.BOX_LEFT && typeOf(fieldAheadOtherHalf) == Type2.BOX_RIGHT) ) {
            return canBoxMove(fieldAhead, dir) && canBoxMove(fieldAheadOtherHalf, dir)
        }

        return false
    }

    // TODO: SAMPLE4 shows error:
    //    #
    // [][]
    //  []
    //  @
    // should not move up, but my code does move left box up :-(
    // [] #
    //   []
    //  []
    //  @
    private fun moveBox(field: Point, dir: Direction) {
        val fieldAhead = field.neighbor(dir)

        if (dir == Direction.WEST || dir == Direction.EAST) {
            if (typeOf(fieldAhead) == Type2.BOX_LEFT || typeOf(fieldAhead) == Type2.BOX_RIGHT) {
                moveBox(fieldAhead, dir)
            }

            if (typeOf(fieldAhead) == Type2.FREE) {
                set(fieldAhead, typeOf(field))
                set(field, Type2.FREE)
            }
        } else {
            // move both halves of a box
            val thisType = typeOf(field)
            val fieldAheadOtherHalf = if (thisType == Type2.BOX_LEFT) fieldAhead.east() else fieldAhead.west()
            val fieldOtherHalf = if (thisType == Type2.BOX_LEFT) field.east() else field.west()
            if (thisType == Type2.BOX_LEFT) {

                if (typeOf(fieldAhead) == thisType) {
                    // full block
                    // []
                    // []
                    val m1 = moveBox(fieldAhead, dir)
                    val m2 = moveBox(fieldAheadOtherHalf, dir)
                }

                // half block west
                // []
                //  []
                if (typeOf(fieldAhead) == Type2.BOX_RIGHT && isFree(fieldAheadOtherHalf)) {
                    val m1 = moveBox(fieldAhead.west(), dir)
                    val m2 = moveBox(fieldAhead, dir)
                }

                // half block east
                //  []
                // []
                if (typeOf(fieldAheadOtherHalf) == Type2.BOX_LEFT && isFree(fieldAhead)) {
                    val m1 = moveBox(fieldAheadOtherHalf, dir)
                    val m2 = moveBox(fieldAheadOtherHalf.east(), dir)
                }

                // double block
                // [][]
                //  []
                if (typeOf(fieldAhead) == Type2.BOX_RIGHT && typeOf(fieldAheadOtherHalf) == Type2.BOX_LEFT) {
                    val m1 = moveBox(fieldAhead.west(), dir)
                    val m2 = moveBox(fieldAhead, dir)
                    val m3 = moveBox(fieldAheadOtherHalf, dir)
                    val m4 = moveBox(fieldAheadOtherHalf.east(), dir)
                }

                if (isFree(fieldAhead) && isFree(fieldAheadOtherHalf)) {
                    // both fields ahead are free -> move both box halves
                    set(fieldAhead, thisType)
                    set(fieldAheadOtherHalf, thisType.otherBoxHalf())
                    set(field, Type2.FREE)
                    set(fieldOtherHalf, Type2.FREE)
                }
            } else if (thisType == Type2.BOX_RIGHT) {


                if (typeOf(fieldAhead) == thisType) {
                    val m1 = moveBox(fieldAhead, dir)
                    val m2 = moveBox(fieldAheadOtherHalf, dir)
                }

                if (typeOf(fieldAhead) == Type2.BOX_LEFT && isFree(fieldAheadOtherHalf)) {
                    val m1 = moveBox(fieldAhead, dir)
                    val m2 = moveBox(fieldAhead.east(), dir)
                }
                if (typeOf(fieldAheadOtherHalf) == Type2.BOX_RIGHT && isFree(fieldAhead)) {
                    val m1 = moveBox(fieldAheadOtherHalf, dir)
                    val m2 = moveBox(fieldAheadOtherHalf.west(), dir)
                }

                if (typeOf(fieldAhead) == Type2.BOX_LEFT && typeOf(fieldAheadOtherHalf) == Type2.BOX_RIGHT) {
                    val m1 = moveBox(fieldAhead.east(), dir)
                    val m2 = moveBox(fieldAhead, dir)
                    val m3 = moveBox(fieldAheadOtherHalf, dir)
                    val m4 = moveBox(fieldAheadOtherHalf.west(), dir)
                }

                if (isFree(fieldAhead) && isFree(fieldAheadOtherHalf)) {
                    set(fieldAhead, thisType)
                    set(fieldAheadOtherHalf, thisType.otherBoxHalf())
                    set(field, Type2.FREE)
                    set(fieldOtherHalf, Type2.FREE)
                }
            }
        }
    }

    private fun isFree(point: Point): Boolean {
        return typeOf(point) == Type2.FREE
    }

    fun gpsSum(): Long {
        return allPoints().filter { typeOf(it) == Type2.BOX_LEFT }.sumOf { 100L * it.row + it.col }
    }

    private fun allPoints(): List<Point> {
        val points = mutableListOf<Point>()
        for (row in 0 until rows) {
            for (col in 0 until cols) {
                points.add(Point(row, col))
            }
        }
        return points
    }

    override fun toString(): String {
        val sb = StringBuilder()
        for (r in 0 until rows) {
            for (c in 0 until cols) {
                if (Point(r, c) == robot) {
                    sb.append(Type.ROBOT.c)
                } else {
                    sb.append(fields[r][c].c)
                }
            }
            sb.append('\n')
        }
        return sb.toString()
    }

    fun print() {
        println(toString())
    }
}

class AOC2024D15(val input: String) {

    fun solve(): Long {
        val chunks = readChunks(input)

        val map = Map(chunks[0])
        val commands = chunks[1].joinToString("")

        for (it in commands.withIndex()) {
//            println("moving ${it.value} (${it.index})")
            map.tryRobotMove(Direction.fromChar(it.value))
//            map.print()
        }

        return map.gpsSum()
    }

    fun solve2(): Long {
        val chunks = readChunks(input)

        val map = Map2(chunks[0])
        val commands = chunks[1].joinToString("")

        map.print()

        for (it in commands.withIndex()) {
            println("moving ${it.value} (${it.index})")
            map.tryRobotMove(Direction.fromChar(it.value))
            map.print()
        }

        return map.gpsSum()
    }
}
