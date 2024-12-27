package year2015.day14

import adventofcode.Problem
import util.parser.findTokens
import kotlin.math.min

val LINE_PATTERN = """(\w*) can fly (\d*) km/s for (\d*) seconds, but then must rest for (\d*) seconds.""".toRegex()

data class Reindeer(val name: String, val kms: Int, val seconds: Int, val rest: Int, var points: Int) {
    fun getDistanceAfter(duration: Int): Int {
        // duration of one round (that means running + resting)
        val durationOfOneRound = seconds + rest

        // no of complete rounds (that means running + resting)
        val noOfRounds = duration / durationOfOneRound

        val distanceAfterCompleteRounds = kms * seconds * noOfRounds

        // how many seconds are left after the complete rounds?
        val timeLeft = duration - (noOfRounds * durationOfOneRound)

        // find out if we end during the run or in the rest phase
        val lastSprint = min(timeLeft, seconds) * kms

        return distanceAfterCompleteRounds + lastSprint
    }
}

fun main() {
    AOC2015D14(SAMPLE, 1000).run()
    AOC2015D14(INPUT, 2503).run()
}

class AOC2015D14(input: String, private val seconds: Int) : Problem(input) {
    private val reindeers = inputLines().map { line ->
        val (reindeer, kms, seconds, rest) = LINE_PATTERN.findTokens(line)
        Reindeer(reindeer, kms.toInt(), seconds.toInt(), rest.toInt(), 0)
    }

    override fun solve1(): Int {
        //    reindeers.forEach { r ->
        //        println("r ${r.getDistanceAfter(seconds)}")
        //    }

        return reindeers.maxOfOrNull { r -> r.getDistanceAfter(seconds) }?: 0
    }

    override fun solve2(): Int {
        (1 .. seconds).forEach { second ->
            // determine maximum distance at this point in time
            val maxDistance = reindeers.maxOf { r ->
                r.getDistanceAfter(second)
            }

            // determine leader(s) and award points
            reindeers.filter { r -> r.getDistanceAfter(second) == maxDistance }.forEach { r -> r.points++ }
        }

//        reindeers.forEach { println(it) }

        return reindeers.maxByOrNull { r -> r.points }?.points?: 0
    }
}

