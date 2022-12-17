package year2022.day17

fun main() {
    part1(SAMPLE, 2022)
    part1(INPUT, 2022)
}

private fun String.cycle() = iterator { while (true) yieldAll(iterator()) }

class JetIterator(private val seed: String): Iterator<Char> {
    private var currentIndex = 0
    override fun hasNext() = true // because it is an endless Iterator

    override fun next(): Char {
        return seed[currentIndex++ % seed.length]
    }
}

fun part1(input: String, noOfRocks: Int) {
    var fallenRocks = mutableSetOf<Point>()
    val rocks = listOf(::rock1Points, ::rock2Points, ::rock3Points, ::rock4Points, ::rock5Points)
    val jet = JetIterator(input)

//    var cutHeight = 0L

    for (rockIt in 0 until noOfRocks) {
        val start = Point(3, getHeightOfFallenRocks(fallenRocks) + 4)
        val rock = rocks[(rockIt % 5).toInt()]

        fallenRocks.addAll(dropOneRock(start, jet, rock, fallenRocks))

//        val minMaxRow =
//            (1..7).minOf { fallenRocks.filter { pair -> pair.first == it }.maxOfOrNull { it.second } ?: 0 }
//        if (minMaxRow > 0) {
//            println("resetting all by $minMaxRow")
//            cutHeight += minMaxRow
//            fallenRocks = fallenRocks.map { Pair(it.first, it.second - minMaxRow) }
//                .filter { it.second > 0 }
//                .toMutableSet()
//        }
//        if (getHeightOfFallenRocks(fallenRocks) > 100_000) {
//            cutHeight += 50_000
//            fallenRocks = fallenRocks.map { Pair(it.first, it.second - 50_000) }
//                .filter { it.second > 0 }
//                .toMutableSet()
//        }
    }

    //print(emptySet(), fallenRocks)
    //println(fallenRocks)
    println("part1: ${getHeightOfFallenRocks(fallenRocks)}")
}

fun getHeightOfFallenRocks(fallenRocks: Set<Point>): Int {
    return fallenRocks.maxOfOrNull { it.second } ?: 0
}

fun dropOneRock(start: Point, jet: JetIterator, rock: (Point) -> Set<Point>, fallenRocks: Set<Point>): Set<Point> {
    var currentPos = start
//    println("start")
//    print(rock(currentPos), fallenRocks)

    while (true) {
        var hasMoved = false

        val jetMove = jet.next()

        var newPos = jet(currentPos, jetMove)
        if (isWithinWalls(rock(newPos)) && !hitsFallenRock(rock(newPos), fallenRocks)) {
            currentPos = newPos
        }
//        println("after jet $jetMove")
//        print(rock(currentPos), fallenRocks)

        newPos = down(currentPos)
        if (isAboveBottom(rock(newPos)) && !hitsFallenRock(rock(newPos), fallenRocks)) {
            currentPos = newPos
            hasMoved = true
        }
//        println("after down")
//        print(rock(currentPos), fallenRocks)

        if (!hasMoved)
            return rock(currentPos)
    }
}

fun jet(p: Point, jet: Char): Point {
    if (jet == '<')
        return p + Point(-1, 0) // move left
    else if (jet == '>')
        return p + Point(1, 0) // move right
    throw IllegalArgumentException("computer says no to this jet char: $jet")
}

fun down(p: Point): Point {
    return p + Point(0, -1)
}

fun isWithinWalls(rock: Set<Point>): Boolean {
    return rock.minOf { it.first } > 0 &&
            rock.maxOf { it.first } < 8
}

fun isAboveBottom(rock: Set<Point>): Boolean {
    return rock.all { p -> p.second > 0 }
}

fun hitsFallenRock(rock: Set<Point>, fallenRocks: Set<Point>): Boolean {
    return rock.any { p -> fallenRocks.contains(p) }
}

fun rock1Points(ref: Point): Set<Point> {
    return setOf(Point(0, 0), Point(1, 0), Point(2, 0), Point(3, 0)).map { it + ref }.toSet()
}

fun rock2Points(ref: Point): Set<Point> {
    return setOf(Point(1, 0), Point(0, 1), Point(1, 1), Point(2, 1), Point(1, 2)).map { it + ref }.toSet()
}

fun rock3Points(ref: Point): Set<Point> {
    return setOf(Point(0, 0), Point(1, 0), Point(2, 0), Point(2, 1), Point(2, 2)).map { it + ref }.toSet()
}

fun rock4Points(ref: Point): Set<Point> {
    return setOf(Point(0, 0), Point(0, 1), Point(0, 2), Point(0, 3)).map { it + ref }.toSet()
}

fun rock5Points(ref: Point): Set<Point> {
    return setOf(Point(0, 0), Point(0, 1), Point(1, 0), Point(1, 1)).map { it + ref }.toSet()
}

fun print(currentRock: Set<Point>, fallenRocks: Set<Point>) {
    val topRow = (currentRock + fallenRocks).maxOfOrNull { it.second } ?: 3
    for (r in topRow downTo 0) {
        if (r == 0)
            println("+-------+")
        else {
            print('|')
            for (c in 1..7) {
                if (currentRock.contains(Point(c, r)))
                    print('@')
                else if (fallenRocks.contains(Point(c, r)))
                    print('#')
                else
                    print('.')
            }
            print('|')
            println()
        }
    }
}

typealias Point = Pair<Int, Int>

infix operator fun Point.plus(p: Point) = Point(this.first + p.first, this.second + p.second)
