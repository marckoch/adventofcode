package year2023.day08

val  LINE_PATTERN = """(\w+) = \((\w+), (\w+)\)""".toRegex()

fun main() {
    part1(SAMPLE1_1).let { println(it) } // 2
    part1(SAMPLE1_2).let { println(it) } // 6
    part1(INPUT).let { println(it) } // 16531

    part2(SAMPLE2_1).let { println(it) } // 6
    part2(INPUT).let { println(it) }
}

fun part1(input: String): Int {
    val instructions = input.lines().first()

    val nodes = input.lines().drop(2).map { line ->
        val (name, left, right) = LINE_PATTERN.matchEntire(line)!!.destructured
        name to Pair(left, right)
    }.toMap()

    var count = 0
    var pos = "AAA"

    while (true) {
        // println("$count: $pos")
        if (pos == "ZZZ") {
            return count
        }
        val node = nodes[pos]

        val lr = instructions[count % instructions.length]
        pos = if (lr == 'L') {
            node!!.first
        } else {
            node!!.second
        }

        // println("going $lr to $pos")

        count++
    }
}

fun part2(input: String): Long {
    val instructions = input.lines().first()

    val nodes = input.lines().drop(2).map { line ->
        val (name, left, right) = LINE_PATTERN.matchEntire(line)!!.destructured
        name to Pair(left, right)
    }.toMap()

    var count = 0L
    var pos = nodes.keys.filter { it.endsWith("A") }

    while (true) {
        // println("$count: $pos")
        if (pos.all { it.endsWith("Z") }) {
            return count
        }

        val lr = instructions[((count % instructions.length).toInt())]

        pos = pos.map { p ->
            val node = nodes[p]
            if (lr == 'L') {
                node!!.first
            } else {
                node!!.second
            }
        }

        count++
    }
}
