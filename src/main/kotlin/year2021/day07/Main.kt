package year2021.day07

import kotlin.math.abs

fun main() {
    solve(SAMPLE, ::calcFuelConsumption1)
    solve(INPUT, ::calcFuelConsumption1)

    solve(SAMPLE, ::calcFuelConsumption2)
    solve(INPUT, ::calcFuelConsumption2)
}

fun solve(input: String, calcFuel: (Int, Int) -> Int) {
    val numbers = input.split(",").map { it.toInt() }

    (0..numbers.max())
        .minOfOrNull { mean ->
            numbers.sumOf { calcFuel(it, mean) } }
        .let { println(it) }
}

fun calcFuelConsumption1(mean: Int, x: Int): Int {
    return abs(mean - x)
}

fun calcFuelConsumption2(mean: Int, x: Int): Int {
    val n = abs(mean - x)
    return n * (n+1) / 2  // Triangular number
}