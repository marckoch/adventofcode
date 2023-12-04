package year2023.day03

fun main() {
    part1(SAMPLE).let { println(it) } // 4361
    part1(INPUT).let { println(it) }  // 527446

    part2(SAMPLE).let { println(it) } // 467835
    part2(INPUT).let { println(it) }  // 73201705
}

fun part1(input: String): Int {
    val (numbers, symbols) = extract(input)

//    numbers.forEach { println(it) }
//    symbols.forEach { println(it) }

    val (notAdjacent, adjacent) = numbers.partition { n ->
        n.neighbors().intersect(symbols.map { it.point }.toSet()).isEmpty()
    }

//    println("not adjacent: ${notAdjacent.size}")
//    notAdjacent.forEach { println(it) }
//    println("adjacent: ${adjacent.size}")
//    adjacent.forEach { println(it) }

    return adjacent.sumOf { it.number() }
}

fun part2(input: String): Int {
    val (numbers, symbols) = extract(input)

    return symbols.filter { it.char == '*' }// potentially gears
        .sumOf { gear -> gearPower(gear, numbers) }
}

fun gearPower(gear: Symbol, allNumbers: Set<PartNumber>): Int {
    val neighbors = neighborsOfGear(gear, allNumbers)
    return if (neighbors.size == 2) {
        val partNumbers = neighbors.map { it.number() }
        val gearRatio = partNumbers[0] * partNumbers[1]
        gearRatio
    } else {
        0 // not a gear, power is 0
    }
}

fun neighborsOfGear(gear: Symbol, allNumbers: Set<PartNumber>): Set<PartNumber> {
    return allNumbers.filter { it.neighbors().contains(gear.point) }.toSet()
}

fun extract(input: String): Pair<Set<PartNumber>, Set<Symbol>> {
    val numbers = mutableSetOf<PartNumber>()
    val symbols = mutableSetOf<Symbol>()

    var y = 1

    input.lines()
        .drop(1) // skip first blank line
        .forEach { line ->
        var x = 0

        var currentNumber: PartNumber? = null

        line.toCharArray().forEach { char ->
            x++

            if (char.isDigit()) {
                // start new current number if needed
                if (currentNumber == null) {
                    currentNumber = PartNumber()
                    currentNumber!!.start = Point(x, y)
                }

                // add char to current number
                currentNumber!!.chars.add(char)
            } else {
                // not a digit, so finish current number and add it to numbers list
                if (currentNumber != null) {
                    currentNumber!!.end = Point(x - 1, y)
                    numbers.add(currentNumber!!)
                    currentNumber = null
                }

                // add everything that is not a period '.' to symbols list
                if (char != '.') {
                    symbols.add(Symbol(Point(x, y), char))
                }
            }
        }
        // at the end of the line: finish current number and add it to numbers list
        if (currentNumber != null) {
            currentNumber!!.end = Point(x, y)
            numbers.add(currentNumber!!)
            currentNumber = null
        }

        y++
    }

    return Pair(numbers, symbols)
}

class PartNumber {
    var start: Point? = null
    var end: Point? = null
    var chars: MutableList<Char> = mutableListOf()

    fun number(): Int {
        return chars.joinToString("").toInt()
    }

    fun neighbors(): Set<Point> {
        val neighbors = mutableSetOf<Point>()

        for (x in start!!.x - 1..end!!.x + 1) {
            neighbors.add(Point(x, start!!.y - 1)) // for row above
        }
        for (x in start!!.x - 1..end!!.x + 1) {
            neighbors.add(Point(x, start!!.y + 1)) // for row below
        }
        neighbors.add(Point(start!!.x - 1, start!!.y)) // left of start point
        neighbors.add(Point(end!!.x + 1, end!!.y))     // right of end point

        assert(neighbors.size == 6 + 2 * chars.size)

        return neighbors
    }

    override fun toString(): String {
        return "(${number()} from $start to $end, neighbors: ${neighbors()})"
    }
}

data class Point(val x: Int, val y: Int) {
    override fun toString(): String {
        return "($x|$y)"
    }
}

data class Symbol(val point: Point, val char: Char) {
    override fun toString(): String {
        return "($char at ${point})"
    }
}