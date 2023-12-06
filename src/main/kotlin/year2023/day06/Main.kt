package year2023.day06

fun main() {
    part1(SAMPLE).let { println(it) } // 288
    part1(INPUT).let { println(it) }  // 840336

    part2(SAMPLE).let { println(it) } // 71503
    part2(INPUT).let { println(it) }  //
}

fun part1(input: String): Long {
    // lines()[0] is empty line
    val times = input.lines()[1].split(" ").drop(1).filter { it.isNotEmpty() }.map { it.toLong() }
    val distances = input.lines()[2].split(" ").drop(1).filter { it.isNotEmpty() }.map { it.toLong() }

    val races = times.zip(distances)

    return races
        .map { waysToWinRace(it) }
        .reduce { acc, i -> acc * i }
}

fun part2(input: String): Long {
    // lines()[0] is empty line
    val times = input.lines()[1]
        .split(":")
        .drop(1)
        .first()
        .replace(" ", "")
        .toLong()
    val distances = input.lines()[2]
        .split(":")
        .drop(1)
        .first()
        .replace(" ", "")
        .toLong()

    return waysToWinRace(times to distances)
}

/**
 * brute force way to count the winning races
 *
 * another way would be to start from 0 and count the races you lost,
 * then when you win for the first time, abort, double this
 * and subtract this number from the total time
 * (we have a parabola here)
 * */
fun waysToWinRace(race: Pair<Long, Long>): Long {
    val totalTime = race.first
    val distanceToBeat = race.second

    var raceWon = 0
    for (timeHoldingButton in 0..totalTime) {
        val speed = timeHoldingButton
        val timeRemaining = totalTime - timeHoldingButton
        val distanceCovered = timeRemaining * speed
        if (distanceCovered > distanceToBeat) {
            raceWon += 1
        }
    }
    return raceWon.toLong()
}
