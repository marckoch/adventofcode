package year2015.day14

fun main() {
    val reindeers = INPUT.lines().map { line ->
        val (reindeer, kms, seconds, rest) = LINE_PATTERN.find(line)!!.destructured
        Reindeer(reindeer, kms.toInt(), seconds.toInt(), rest.toInt(), 0)
    }

    reindeers.maxByOrNull { r ->
        r.getDistanceAfter(2503)
    }.let { println("$it ${it?.getDistanceAfter(2503)}") }

//    reindeers.forEach { reindeer ->
//        println("$reindeer ${reindeer.getDistanceAfter(2503)}")
//    }
}