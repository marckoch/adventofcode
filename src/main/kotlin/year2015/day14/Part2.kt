package year2015.day14

fun main() {
    val reindeers = INPUT.lines().map { line ->
        val (reindeer, kms, seconds, rest) = LINE_PATTERN.find(line)!!.destructured
        Reindeer(reindeer, kms.toInt(), seconds.toInt(), rest.toInt(), 0)
    }

    (1 .. 2503).forEach { second ->
        // determine maximum distance at this point in time
        val maxDistance = reindeers.maxOf { r ->
            r.getDistanceAfter(second)
        }

        // determine leader(s)
        val leadingReindeers = reindeers.filter { r -> r.getDistanceAfter(second) == maxDistance }

        // award points to leader(s)
        leadingReindeers.forEach { r -> r.points++ }
    }

    reindeers.forEach { println(it) }

    println("winner is:")
    println(reindeers.maxByOrNull { r -> r.points }!!)
}