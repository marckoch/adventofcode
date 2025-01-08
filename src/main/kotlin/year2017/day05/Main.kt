package year2017.day05

import adventofcode.Problem

fun main() {
    AOC2017D05(SAMPLE).run()
    AOC2017D05(INPUT).run()
}

class AOC2017D05(input: String) : Problem(input) {
    override fun solve1() : Int {
        val numbers = inputLines().map { it.toInt() }.toMutableList()
        var steps = 0
        var pos = 0
        while (pos in numbers.indices) {
            pos += numbers[pos]++
            steps++
        }
        return steps
    }

    override fun solve2() : Int {
        val numbers = inputLines().map { it.toInt() }.toMutableList()
        var steps = 0
        var pos = 0
        while (pos in numbers.indices) {
            val nextPos = pos + numbers[pos]
            if (numbers[pos] >= 3) numbers[pos]-- else numbers[pos]++
            pos = nextPos
            steps++
        }
        return steps
    }
}
