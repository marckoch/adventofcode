package year2021.day13

fun main() {
    part1(SAMPLE,
        // TODO: could we parse this from input strings?
        { p -> foldAtRow(p, 7) },
        { p -> foldAtCol(p, 5) })

    part1(INPUT,
        // TODO: could we parse this from input strings?
        { p -> foldAtCol(p, 655) })

    part2(INPUT,
        // TODO: could we parse this from input strings?
        { p -> foldAtCol(p, 655) },
        { p -> foldAtRow(p, 447) },
        { p -> foldAtCol(p, 327) },
        { p -> foldAtRow(p, 223) },
        { p -> foldAtCol(p, 163) },
        { p -> foldAtRow(p, 111) },
        { p -> foldAtCol(p, 81) },
        { p -> foldAtRow(p, 55) },
        { p -> foldAtCol(p, 40) },
        { p -> foldAtRow(p, 27) },
        { p -> foldAtRow(p, 13) },
        { p -> foldAtRow(p, 6) })
}

fun part1(input: String, vararg folds: (List<Point>) -> List<Point>) {
    val points = parse(input)

    val foldedPoints = folds.fold(points) { acc, function ->
        function(acc)
    }
    println(foldedPoints.toSet().size)
}

fun part2(input: String, vararg folds: (List<Point>) -> List<Point>) {
    val points = parse(input)

    val foldedPoints = folds.fold(points) { acc, function ->
        function(acc)
    }
    print(foldedPoints)
}

fun parse(input: String): List<Pair<Int, Int>> {
    return input.lines().map { s ->
        val (r, c) = "(\\d*),(\\d*)".toRegex().find(s)!!.destructured
        Pair(r.toInt(), c.toInt())
    }
}

fun print(points: List<Point>) {
    val rMax = points.maxOf { pair: Point -> pair.first }
    val cMax = points.maxOf { pair: Point -> pair.second }

    println("---")
    (0..cMax).forEach { c ->
        (0..rMax).forEach { r ->
            if (points.contains(Pair(r, c))) {
                print('#')
            } else
                print('.')
        }
        println()
    }
}

fun foldAtRow(points: List<Point>, row: Int): List<Point> {
    return points.map { pair ->
        if (pair.second - row > 0) {
            Pair(pair.first, pair.second - 2 * (pair.second - row))
        } else {
            pair
        }
    }
}

fun foldAtCol(points: List<Point>, col: Int): List<Point> {
    return points.map { pair ->
        if (pair.first - col > 0) {
            Pair(pair.first - 2 * (pair.first - col), pair.second)
        } else {
            pair
        }
    }
}

typealias Point = Pair<Int, Int>