package year2015.day14

import kotlin.math.min

const val TEST_INPUT = """Comet can fly 14 km/s for 10 seconds, but then must rest for 127 seconds.
Dancer can fly 16 km/s for 11 seconds, but then must rest for 162 seconds."""

const val INPUT = """Dancer can fly 27 km/s for 5 seconds, but then must rest for 132 seconds.
Cupid can fly 22 km/s for 2 seconds, but then must rest for 41 seconds.
Rudolph can fly 11 km/s for 5 seconds, but then must rest for 48 seconds.
Donner can fly 28 km/s for 5 seconds, but then must rest for 134 seconds.
Dasher can fly 4 km/s for 16 seconds, but then must rest for 55 seconds.
Blitzen can fly 14 km/s for 3 seconds, but then must rest for 38 seconds.
Prancer can fly 3 km/s for 21 seconds, but then must rest for 40 seconds.
Comet can fly 18 km/s for 6 seconds, but then must rest for 103 seconds.
Vixen can fly 18 km/s for 5 seconds, but then must rest for 84 seconds."""

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