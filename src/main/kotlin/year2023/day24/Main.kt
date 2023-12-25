package year2023.day24

fun main() {
    println(part1(SAMPLE, 7, 27)) // 2
    println(part1(INPUT, 200_000_000_000_000, 400_000_000_000_000)) // 15318
}

fun part1(input: String, min: Long, max: Long): Int {
    val stones = input.lines().drop(1).map { HailStone.fromString(it) }

    val limits = (min.toDouble()..max.toDouble())

    val count = buildAllPairs(stones.size - 1).mapNotNull {
//        println("Pair: $it")
//        println("   stones: ${stones[it.first]}")
//        println("           ${stones[it.second]}")

        if (stones[it.first].dx * stones[it.second].dy == stones[it.first].dy * stones[it.second].dx) {
//            println(" --> parallel!")
            return@mapNotNull null
        }

        val crossing = intersect(stones[it.first], stones[it.second])
//        println("   crossing: $crossing")
        if ((crossing.first !in limits) || (crossing.second !in limits)) {
//            println(" --> off limits!")
            return@mapNotNull null
        }

        val timeFirstStone = timeOfImpact(stones[it.first], crossing)
//        println("   timeFirstStone: $timeFirstStone")
        if (timeFirstStone <= 0.0) {
//            println(" --> in past of stone 1!")
            return@mapNotNull null
        }
        val timeSecondStone = timeOfImpact(stones[it.second], crossing)
//        println("   timeFirstStone: $timeSecondStone")
        if (timeSecondStone <= 0.0) {
//            println(" --> in past of stone 2!")
            return@mapNotNull null
        }

        1
    }.sumOf { it }

    return count
}

fun intersect(p1: HailStone, p2: HailStone): Pair<Double, Double> {
    val x_top = p1.dx.toDouble() * p2.dx * (p2.y - p1.y) + p1.dy.toDouble() * p2.dx * p1.x - p1.dx.toDouble() * p2.dy * p2.x
    val x_bottom = p1.dy.toDouble() * p2.dx - p1.dx * p2.dy

    // same as above but switch all x <--> y
    val y_top = p1.dy.toDouble() * p2.dy * (p2.x - p1.x) + p1.dx.toDouble() * p2.dy * p1.y - p1.dy.toDouble() * p2.dx * p2.y
    val y_bottom = p1.dx.toDouble() * p2.dy - p1.dy * p2.dx

    return x_top / x_bottom.toDouble() to y_top / y_bottom.toDouble()
}

fun timeOfImpact(p: HailStone, crossing: Pair<Double, Double>): Double {
    val t1 = (crossing.first - p.x) / p.dx
    val t2 = (crossing.second - p.y) / p.dy
//    println("   t1: $t1, t2: $t2")
//    println(t1 - t2)
//    return t1 to t2
    return t1 // or t2? does not matter, see difference above t1-t2
}

data class HailStone(val x: Long, val y: Long, val z: Long, val dx: Int, val dy: Int, val dz: Int) {
    companion object {
        fun fromString(input: String): HailStone {
            val (c, v) = input.split("@")
            val (x, y, z) = c.trim().split(",").map { it.trim().toLong() }
            val (dx, dy, dz) = v.trim().split(",").map { it.trim().toInt() }
            return HailStone(x, y, z, dx, dy, dz)
        }
    }
}

fun buildAllPairs(n: Int): List<Pair<Int, Int>> {
    (0..n).flatMap { i ->
        (i + 1..n).map { j ->
            i to j
        }
    }.let { return it }
}