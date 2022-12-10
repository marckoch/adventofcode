package year2022.day10

fun main() {
    part1(SAMPLE)
    part1(INPUT)

    part2(SAMPLE)
    part2(INPUT)
}

val measurementCycles = listOf(20, 60, 100, 140, 180, 220)

const val CRT_WIDTH = 40

private fun part1(input: String) {
    var x = 1
    var cycle = 0

    // map each line to a measurement (which can be 0 if wrong cycle)
    input.lines().map { l ->
        var measurement = 0
        if (l.startsWith("addx ")) {
            // addx V -> two cycles pass, at the end we add V to x
            // during both cycles we check if cycle is measurement cycle

            cycle += 1
            measurement += measurement(cycle, x)

            //println("$cycle x=$x addx pending")

            cycle += 1
            measurement += measurement(cycle, x)

            val V = l.split(" ")[1].toInt()
            x += V

            //println("$cycle addx $V -> x=$x")
        } else {
            // noop -> one cycle passes where nothing happens,
            // we still have to check if cycle is measurement cycle

            cycle += 1
            measurement += measurement(cycle, x)

            //println("$cycle noop x=$x")
        }
        measurement
    }
        .sum()
        .let {
            println("part1: $it")
        }
}

fun measurement(cycle: Int, x: Int): Int = if (cycle in measurementCycles) cycle * x else 0

private fun part2(input: String) {
    var x = 1
    var cycle = 0

    println("part2:")
    input.lines().flatMap { l ->
        // map each line to a List<Chars> that are printed on the screen
        val pixels = mutableListOf<Char>()
        if (l.startsWith("addx ")) {
            // addx V -> two cycles pass, at the end we add V to x

            pixels.add(drawPixel(cycle, x))
            cycle += 1
            pixels.add(drawPixel(cycle, x))
            cycle += 1

            val V = l.split(" ")[1].toInt()
            x += V
        } else {
            // noop -> one cycle passes where nothing happens,

            pixels.add(drawPixel(cycle, x))
            cycle += 1
        }
        pixels
    }
        .joinToString("")
        .chunked(40)
        .forEach { println(it) }
}

fun drawPixel(cycle: Int, x: Int): Char {
    return if (cycle % CRT_WIDTH in spritePositions(x)) '#' else '.'
}

fun spritePositions(x: Int): List<Int> = listOf(x - 1, x, x + 1)