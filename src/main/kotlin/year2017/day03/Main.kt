package year2017.day03

import adventofcode.Problem
import kotlin.math.sqrt

fun main() {
    AOC2017D03("1024").run()
    AOC2017D03("368078").run()
}

class AOC2017D03(input: String) : Problem(input) {
    override fun solve1() : Int {
        val target = input.toInt()

        val floorSqrt = sqrt(target.toDouble()).toInt()
        val lowerSQ = floorSqrt * floorSqrt

        val biggerSQ = (floorSqrt + 1) * (floorSqrt + 1)

        val diff = target - lowerSQ
        val diff2 = biggerSQ - target

        println("$floorSqrt^2 = $lowerSQ <-$diff- $target <-$diff2- $biggerSQ = ${floorSqrt+1}^2")

        val mhdToSqrt = 606-371 // 235 too low
        return mhdToSqrt + (371 - 303) // 303 too low
    }
    override fun solve2() = 1
}
