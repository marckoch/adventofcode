package year2020.day13

import kotlin.math.ceil

fun main() {
    part1(SAMPLE)
    part1(INPUT)

    part2(SAMPLE)
    part2(INPUT)

//    ChineseRemainder().findMinX(arrayOf(3L, 4, 5), arrayOf(2L, 3, 1)).let { println("x is $it") }
//    ChineseRemainder().findMinX(arrayOf(17L, 13, 19), arrayOf(0L, -2, -3)).let { println("x is $it") }
//    ChineseRemainder().findMinX(arrayOf(67L, 7, 59, 61), arrayOf(0L, -1, -2, -3)).let { println("x is $it") }
//    ChineseRemainder().findMinX(arrayOf(67L, 7, 59, 61), arrayOf(0L, -2, -3, -4)).let { println("x is $it") }
//    ChineseRemainder().findMinX(arrayOf(67L, 7, 59, 61), arrayOf(0L, -1, -3, -4)).let { println("x is $it") }
//    ChineseRemainder().findMinX(arrayOf(1789L, 37, 47, 1889), arrayOf(0L, -1, -2, -3)).let { println("x is $it") }
//    ChineseRemainder().findMinX(arrayOf(7L, 13, 59, 31, 19), arrayOf(0L, -1, -4, -6, -7)).let { println("x is $it") }
}

fun part1(input: String) {
    val earliestTimestamp = input.lines().first().toLong()
    val busses = input.lines()[1]
        .split(",")
        .filter { it != "x" }
        .map { it.toLong() }

    busses
        .map { it to timeToWait(it, earliestTimestamp) }
        .minBy { it.second }
        .let { println("part1: ${it.first * it.second}") }
}

fun part2(input: String) {
    val busses = input.lines()[1]
        .split(",")
        .withIndex()
        .filter { (_, value) -> value != "x" }
        .map { it.value.toLong() to it.index.toLong() }

    val nums = busses.map { it.first }.toTypedArray()
    val rems = busses.map { -it.second }.toTypedArray() // remainders are negative!

    val res = ChineseRemainder().findMinX(nums, rems)
    println("part2: $res")
}

fun timeToWait(bus: Long, earliestTimestamp: Long): Long {
    val intervalsToCoverTimestamp = ceil(earliestTimestamp.toDouble() / bus)
    val nextArrivalAfterTimestamp = bus * intervalsToCoverTimestamp
    return nextArrivalAfterTimestamp.toLong() - earliestTimestamp
}

// https://www.geeksforgeeks.org/implementation-of-chinese-remainder-theorem-inverse-modulo-based-implementation/
class ChineseRemainder {
    // Returns modulo inverse of 'a' with respect to m using extended Euclid Algorithm.
    // see for details: https://www.geeksforgeeks.org/multiplicative-inverse-under-modulo-m/
    private fun inv(a: Long, m: Long): Long {
        var pa = a
        var pm = m
        var t: Long
        var q: Long
        var x0 = 0L
        var x1 = 1L

        if (pm == 1L)
            return 0

        // Apply extended Euclid Algorithm
        while (pa > 1) {
            // q is quotient
            q = pa.div(pm);

            t = pm;

            // m is remainder now, process
            // same as euclid's algo
            pm = pa.mod(pm);
            pa = t;

            t = x0;

            x0 = x1 - q * x0;

            x1 = t;
        }

        // Make x1 positive
        if (x1 < 0)
            x1 += m;

        return x1;
    }

    // k is size of num[] and rem[].
    // Returns the smallest number x such that:
    // x % num[0] = rem[0],
    // x % num[1] = rem[1],
    // ..................
    // x % num[k-2] = rem[k-1]
    // Assumption: Numbers in num[] are pairwise coprime (gcd for every pair is 1)
    fun findMinX(num: Array<Long>, rem: Array<Long>): Long {
        // Compute product of all numbers
        val prod = num.fold(1L) { acc, i -> acc * i }

        return num.indices.fold(0L) { acc, i ->
            val pp = prod / num[i]
            acc + (rem[i] * inv(pp, num[i]) * pp)
        }.mod(prod)
    }
}