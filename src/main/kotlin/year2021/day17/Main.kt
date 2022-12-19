package year2021.day17

import kotlin.math.sqrt

fun main() {
    part1(20..30, -10..-5)
    part1(257..286, -101..-57)

    part2(20..30, -10..-5)
    part2(257..286, -101..-57)
}

fun part1(targetX: IntRange, targetY: IntRange) {
    val minVX = sqrt(targetX.min().toDouble() * 2).toInt()
    val maxVX = targetX.max()
    val minVY = 1
    val maxVY = 200 // todo how to find a good max?

    (minVX..maxVX).flatMap { vx ->
        (minVY..maxVY).map { vy ->
            path(vx, vy, targetX, targetY)
        }
    }.filter { probes -> probes.any { p -> p.x in targetX && p.y in targetY } }
        .maxBy { probes -> probes.maxOf { p -> p.y } }
        .also { println(it) }
        .maxOf { p -> p.y }
        .let { println(it) }
}

fun part2(targetX: IntRange, targetY: IntRange) {
    val minVX = sqrt(targetX.min().toDouble() * 2).toInt()
    val maxVX = targetX.max()
    val minVY = targetY.min()
    val maxVY = 200 // todo how to find a good max?

    (minVX..maxVX).flatMap { vx ->
        (minVY..maxVY).map { vy ->
            path(vx, vy, targetX, targetY)
        }
    }.filter { probes -> probes.any { p -> p.x in targetX && p.y in targetY } }
        .count()
        .let { println(it) }
}

fun path(vx: Int, vy: Int, targetX: IntRange, targetY: IntRange): List<Probe> {
    val initial = Probe(0, 0, vx, vy)
    return generateSequence(initial) { probe ->
        val newP = probe.next()
        val isTooFarOut = newP.y < targetY.min() || newP.x > targetX.max()
        if (isTooFarOut)
            null
        else
            newP
    }.toList()
}

class Probe(val x: Int, val y: Int, val vx: Int, val vy: Int) {
    fun next(): Probe {
        val newVX = if (vx > 0) vx - 1 else if (vx < 0) vx + 1 else 0
        return Probe(x + vx, y + vy, newVX, vy - 1)
    }

    override fun toString(): String {
        return "($x, $y) v=($vx, $vy)"
    }
}