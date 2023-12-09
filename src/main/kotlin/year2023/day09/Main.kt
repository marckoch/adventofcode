package year2023.day09

fun main() {
    part1(SAMPLE).let { println(it) } // 114
    part1(INPUT).let { println(it) } // 2105961943

    part2(SAMPLE).let { println(it) } // 2
    part2(INPUT).let { println(it) } // 1019
}

fun part1(input: String): Long {
    return input.lines().sumOf { process(it, selector = { l -> l.last() }, aggregator = { a, b -> a + b }) }
}

fun part2(input: String): Long {
    return input.lines().sumOf { process(it, selector = { l -> l.first() }, aggregator = { a, b -> a - b }) }
}

fun process(line: String, selector: (List<Int>) -> Int, aggregator: (Int, Int) -> Int): Long {
    // println("doing $line")
    val numbers = line.split(" ")
        .map { it.toInt() }

    // building the "upside down" triangle
    val rows = mutableListOf(numbers)
    var row = 0
    while (true) {
        val nextRow = rows[row].windowed(2).map { it[1] - it[0] }
        rows.add(nextRow)
        if (nextRow.all { it == 0 }) {
            break
        }
        row++
    }
    // rows.forEach { println(it) }

    // extending the triangle
    var cur = 0
    val edgeElements = rows.reversed().map { selector(it) }
    //edgeElements.forEach { println(it) }
    val newList = edgeElements.map { e ->
        val n = aggregator(e, cur)
        cur = n
        n
    }
    //newList.forEach { println(it) }

    return newList.last().toLong()
}