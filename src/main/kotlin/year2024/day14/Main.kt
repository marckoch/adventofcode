package year2024.day14

fun main() {
    AOC2024D14(SAMPLE).solve(11, 7).also(::println)// { println(it) } // 12
    AOC2024D14(INPUT).solve(101, 103).also { println(it) } // 225552000

    AOC2024D14(INPUT).solve2(101, 103).also { println(it) } // 7371
}

data class Robot(val pc: Int, val pr: Int, val vc: Int, val vr: Int) {
    fun step(cMax: Int, rMax: Int, steps: Int): Robot {
        val newR = ((pr + steps * vr) % rMax).let { if (it < 0) it + rMax else it }
        val newC = ((pc + steps * vc) % cMax).let { if (it < 0) it + cMax else it }
        return Robot(newC, newR, vc, vr)
    }
}

class AOC2024D14(val input: String) {
    private val regExp = """p=(-?\d*),(-?\d*) v=(-?\d*),(-?\d*)""".toRegex()

    fun solve(cMax: Int, rMax: Int): Int {
        val robots = parse()

        val movedRobots = robots.map {
            it.step(cMax, rMax, 100)
        }

        val quadrants = IntArray(4) {0}
        movedRobots.forEach {
            val left = it.pc < cMax/2
            val right = it.pc > cMax/2
            val up = it.pr < rMax/2
            val down = it.pr > rMax/2

            if (left && up) quadrants[0]++
            if (right && up) quadrants[1]++
            if (right && down) quadrants[2]++
            if (left && down) quadrants[3]++
        }
        return quadrants[0] * quadrants[1] * quadrants[2] * quadrants[3]
    }

    fun solve2(cMax: Int, rMax: Int): Int {
        val robots = parse()

        var next = robots
        for (sec in 0..10000) {
            next = robots.map { it.step(cMax, rMax, sec) }
            if (detectTree(next)) {
                print(next, cMax, rMax)
                break
            }
        }

        return 0
    }

    // look for a 3x3 square nd hope that is good enough...
    private fun detectTree(robots: List<Robot>): Boolean {
        val positions = robots.map { it.pc to it.pr }
        return positions.any { positions.containsAll(neighbors(it)) }
    }

    fun neighbors(position: Pair<Int, Int>): Set<Pair<Int, Int>> {
        return setOf(
            position.first to position.second - 1,
            position.first to position.second + 1,
            position.first - 1 to position.second,
            position.first + 1 to position.second,
            position.first - 1 to position.second - 1,
            position.first + 1 to position.second + 1,
            position.first - 1 to position.second + 1,
            position.first + 1 to position.second - 1,
        )
    }

    fun print(robots: List<Robot>, cMax: Int, rMax: Int) {
        val positions = robots.map { it.pc to it.pr }
        for (r in 0..rMax) {
            for (c in 0..cMax) {
                if (positions.contains(c to r))
                    print('#')
                else
                    print('.')
            }
            println()
        }
    }

    fun parse(): List<Robot> {
        return input.lines().map {
            val (pc, pr, vc, vr) = regExp.find(it)!!.destructured.toList().map { s -> s.toInt() }
            Robot(pc, pr, vc, vr)
        }
    }
}
