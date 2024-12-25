package year2015.day03

import adventofcode.Problem
import util.point.Point
import util.point.move

fun main() {
    AOC2015D03(INPUT).run()
}

class AOC2015D03(input: String) : Problem(input) {
    override fun solve1(): Int {
        val houses = HashMap<Point, Int>()
        var currentHouseSanta = Point(0, 0)
        houses[currentHouseSanta] = 1

        for (dir in input) {
            val nextHouse = currentHouseSanta.move(dir)
            houses.merge(nextHouse, 1, Int::plus)
            currentHouseSanta = nextHouse
        }
        return houses.size
    }

    override fun solve2(): Int {
        val houses = HashMap<Point, Int>()
        var currentHouseSanta = Point(0, 0)
        var currentHouseRoboSanta = Point(0, 0)
        houses[currentHouseSanta] = 1

        for ((i, dir) in input.withIndex()) {
            if (i % 2 == 0) { // Santa
                val nextHouse = currentHouseSanta.move(dir)
                houses.merge(nextHouse, 1, Int::plus)
                currentHouseSanta = nextHouse
            } else { // RoboSanta
                val nextHouse = currentHouseRoboSanta.move(dir)
                houses.merge(nextHouse, 1, Int::plus)
                currentHouseRoboSanta = nextHouse
            }
        }
        return houses.size
    }
}
