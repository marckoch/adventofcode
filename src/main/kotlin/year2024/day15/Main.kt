package year2024.day15


fun main() {
//    AOC2024D15(SAMPLE).solve().also(::println) // 10092
//    AOC2024D15(SAMPLE2).solve().also(::println) // 2028

//    AOC2024D15(INPUT).solve().also(::println) // 1371036

    AOC2024D15(SAMPLE).solve2().also(::println) // 9021
//    AOC2024D15(INPUT).solve2().also(::println) // 1390733 too low, 1409190 too high, 1391033 too low
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

    private fun getType(point: Point): Type {
        return fields[point.row][point.col]
    }

    private fun setType(point: Point, value: Type) {
        fields[point.row][point.col] = value
    }

    fun moveRobot(dir: Direction) {
        val fieldAhead = robot.neighbor(dir)
        when (getType(fieldAhead)) {
            Type.BOX -> {
                val boxWasMoved = moveBox(fieldAhead, dir)
                if (boxWasMoved)
                    moveRobot(dir)
            }
            Type.FREE -> {
                setType(robot, Type.FREE)
                robot = fieldAhead
            }
            Type.WALL, Type.ROBOT -> return
        }
    }

    private fun moveBox(field: Point, dir: Direction): Boolean {
        val fieldAhead = field.neighbor(dir)
        when (getType(fieldAhead)) {
            Type.BOX -> return moveBox(fieldAhead, dir)
            Type.FREE -> {
                setType(field, Type.FREE)
                setType(fieldAhead, Type.BOX)
                return true
            }
            Type.WALL, Type.ROBOT -> return false
        }
    }

    fun gpsSum(): Long {
        var sum = 0L
        for (r in 0 until rows) {
            for (c in 0 until cols) {
                if (getType(Point(r, c)) == Type.BOX) {
                    sum += 100 * r + c
                }
            }
        }
        return sum
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

    private fun getType(point: Point): Type2 {
        return fields[point.row][point.col]
    }

    private fun setType(point: Point, value: Type2) {
        fields[point.row][point.col] = value
    }

    fun moveRobot(dir: Direction) {
        val fieldAhead = robot.neighbor(dir)
        when (getType(fieldAhead)) {
            Type2.BOX_LEFT -> {
                val boxWasMoved = moveBox(fieldAhead, dir)
                if (boxWasMoved)
                    moveRobot(dir)
            }
            Type2.BOX_RIGHT -> {
                val boxWasMoved = moveBox(fieldAhead, dir)
                if (boxWasMoved)
                    moveRobot(dir)
            }
            Type2.FREE -> {
                setType(robot, Type2.FREE)
                robot = fieldAhead
            }
            Type2.WALL, Type2.ROBOT -> return
        }
    }

    private fun moveBox(field: Point, dir: Direction): Boolean {
        if (dir == Direction.WEST || dir == Direction.EAST) {
            val fieldAhead = field.neighbor(dir)
            when (getType(fieldAhead)) {
                Type2.BOX_LEFT, Type2.BOX_RIGHT -> return moveBox(fieldAhead, dir)
                Type2.FREE -> {
                    if (getType(field) == Type2.BOX_LEFT) {
                        setType(fieldAhead, Type2.BOX_LEFT)
                    } else if (getType(field) == Type2.BOX_RIGHT) {
                        setType(fieldAhead, Type2.BOX_RIGHT)
                    }
                    setType(field, Type2.FREE)
                    return true
                }
                Type2.WALL, Type2.ROBOT -> return false
            }
        } else {
            // move both halves of a box
            val thisType = getType(field)
            val partnerType = if (thisType == Type2.BOX_LEFT) Type2.BOX_RIGHT else Type2.BOX_LEFT
            val fieldAhead = field.neighbor(dir)
            val fieldAheadOfPartner = if (thisType == Type2.BOX_LEFT) fieldAhead.east() else fieldAhead.west()
            if (thisType == Type2.BOX_LEFT) {
                if (getType(fieldAhead) == Type2.FREE && getType(fieldAheadOfPartner) == Type2.FREE) {
                    // both fields ahead are free -> move both box halves
                    setType(fieldAhead, thisType)
                    setType(fieldAheadOfPartner, partnerType)
                    setType(field, Type2.FREE)
                    setType(field.east(), Type2.FREE)
                    return true
                }

                if (getType(fieldAhead) == Type2.BOX_LEFT) {
                    // full block
                    val m1 = moveBox(fieldAhead, dir)
                    val m2 = moveBox(fieldAheadOfPartner, dir)
                    return m1 || m2
                }

                if (getType(fieldAhead) == Type2.BOX_RIGHT) {
                    val m1 = moveBox(fieldAhead.west(), dir)
                    val m2 = moveBox(fieldAhead, dir)
                    return m1 || m2
                }
                if (getType(fieldAhead.east()) == Type2.BOX_LEFT) {
                    val m1 = moveBox(fieldAhead.east(), dir)
                    val m2 = moveBox(fieldAhead.east().east(), dir)
                    return m1 || m2
                }
            } else if (thisType == Type2.BOX_RIGHT) {
                if (getType(fieldAhead) == Type2.FREE && getType(fieldAheadOfPartner) == Type2.FREE) {
                    setType(fieldAhead, thisType)
                    setType(fieldAheadOfPartner, partnerType)
                    setType(field, Type2.FREE)
                    setType(field.west(), Type2.FREE)
                    return true
                } else if (getType(fieldAhead) == Type2.BOX_RIGHT) {
                    val m1 = moveBox(fieldAhead, dir)
                    val m2 = moveBox(fieldAheadOfPartner, dir)
                    return m1 || m2
                }


                if (getType(fieldAhead) == Type2.BOX_LEFT) {
                    val m1 = moveBox(fieldAhead, dir)
                    val m2 = moveBox(fieldAhead.east(), dir)
                    return m1 || m2
                }
                if (getType(fieldAhead.west()) == Type2.BOX_RIGHT) {
                    val m1 = moveBox(fieldAhead.west(), dir)
                    val m2 = moveBox(fieldAhead.west().west(), dir)
                    return m1 || m2
                }
            }
            return false
        }
    }

    fun gpsSum(): Long {
        var sum = 0L
        for (r in 0 until rows) {
            for (c in 0 until cols) {
                if (getType(Point(r, c)) == Type2.BOX_LEFT) {
                    sum += 100 * r + c
                }
            }
        }
        return sum
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

        commands.forEach {
            map.moveRobot(Direction.fromChar(it))
        }

        return map.gpsSum()
    }

    fun solve2(): Long {
        val chunks = readChunks(input)

        val map = Map2(chunks[0])
        val commands = chunks[1].joinToString("")

        map.print()

        commands.withIndex().forEach {
            println("moving ${it.value} (${it.index})")
            map.moveRobot(Direction.fromChar(it.value))
        }
        map.print()

        return map.gpsSum()
    }

    private fun readChunks(input: String): List<List<String>> {
        return input.lines().fold(mutableListOf<ArrayList<String>>()) { list, line ->
            if (line.isEmpty()) {
                list.add(ArrayList())
            } else {
                if (list.isEmpty()) {
                    list.add(ArrayList())
                }
                list.last().add(line)
            }
            list
        }
    }
}
