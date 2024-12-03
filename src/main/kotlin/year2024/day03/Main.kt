package year2024.day03

val MUL_PATTERN = """mul\((\d\d?\d?),(\d\d?\d?)\)""".toRegex()

val MUL_PATTERN2 = """mul\((\d\d?\d?),(\d\d?\d?)\)|do\(\)|don't\(\)""".toRegex()

fun main() {
    part1(SAMPLE).let { println(it) } // 161
    part1(INPUT).let { println(it) }  // 170778545

    part2(SAMPLE2).let { println(it) } // 48
    part2(INPUT).let { println(it) } // 82868252
}

fun part1(input: String): Long {
    return input.lines().joinToString().let { line -> mul1(line) }
}

fun part2(input: String): Long {
    return input.lines().joinToString().let { line -> mul2(line) }
}

fun mul1(line:String): Long {
    return MUL_PATTERN.findAll(line).sumOf {
        val (n1,n2) = it.destructured
        n1.toLong() * n2.toLong()
    }
}

fun mul2(line: String): Long {
    var enabled = true
    return MUL_PATTERN2.findAll(line).fold(0L) { acc, it ->
        val group = it.groupValues.first()
        val sum = when (group) {
            "do()" -> {
                enabled = true
                0
            }
            "don't()" -> {
                enabled = false
                0
            }
            else -> {
                if (enabled) {
                    val (n1, n2) = it.destructured
                    n1.toLong() * n2.toLong()
                } else {
                    0
                }
            }
        }
        acc + sum
    }
}