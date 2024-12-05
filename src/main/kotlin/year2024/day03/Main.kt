package year2024.day03

fun main() {
    AOC2024D03(SAMPLE).solvePart1().let { println(it) } // 161
    AOC2024D03(INPUT).solvePart1().let { println(it) }  // 170778545

    AOC2024D03(SAMPLE2).solvePart2().let { println(it) } // 48
    AOC2024D03(INPUT).solvePart2().let { println(it) } // 82868252
}

class AOC2024D03(rawInput: String) {
    val input = rawInput.lines().joinToString()

    fun solvePart1(): Long {
        val MUL_PATTERN = """mul\((\d\d?\d?),(\d\d?\d?)\)""".toRegex()

        return MUL_PATTERN.findAll(input).sumOf {
            eval(it)
        }
    }

    fun solvePart2(): Long {
        val MUL_PATTERN2 = """mul\((\d\d?\d?),(\d\d?\d?)\)|do\(\)|don't\(\)""".toRegex()

        var enabled = true
        return MUL_PATTERN2.findAll(input).fold(0L) { acc, it ->
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
                        eval(it)
                    } else {
                        0
                    }
                }
            }
            acc + sum
        }
    }

    private fun eval(matchResult: MatchResult): Long {
        val (n1, n2) = matchResult.destructured
        return n1.toLong() * n2.toLong()
    }
}