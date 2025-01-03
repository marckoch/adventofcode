package year2016.day19

import adventofcode.Problem
import java.util.stream.IntStream


const val SAMPLE = "5"
const val INPUT = "3018458"

fun main() {
    AOC2016D19(SAMPLE).run()
    AOC2016D19(SAMPLE).partOne().also { println(it) }
    AOC2016D19(SAMPLE).partTwo().also { println(it) }
//    AOC2016D19(INPUT).run() SLOW !!
}

class AOC2016D19(input: String) : Problem(input) {
    private val elves = input.toInt()

    override fun solve1() = stealFromLeftElf()
    override fun solve2() = stealCircle()

    private fun stealFromLeftElf() : Int {
        val presents = IntArray(elves) { 1 }
        var currentElf = 0
        var nextElf = findNextElfWithPresents(presents, currentElf)
        var count = 0
        while (currentElf in presents.indices) {
            // steal presents
            presents[currentElf] += presents[nextElf]
            presents[nextElf] = 0
            count++

            currentElf = findNextElfWithPresents(presents, nextElf)
            nextElf = findNextElfWithPresents(presents, currentElf)

            // only one elf left -> done
            if (currentElf == nextElf)
                break
        }
        return currentElf + 1
    }

    private fun findNextElfWithPresents(presents: IntArray, start: Int): Int {
        var next = start
        do {
            next = (next + 1) % elves
        } while (presents[next] == 0)
        return next
    }

    private fun stealCircle() : Int {
        val elves = elves
        val presents = (1..elves).map { it to 1 }.toMutableList()
        var currentElf = 0
        var nextElf = findOppositeElf(presents, currentElf)
        while (presents.size > 1) {
            // steal presents
            val curr = presents[currentElf]
            val next = presents[nextElf]
            presents[currentElf] = curr.first to curr.second + next.second
            presents.removeAt(nextElf)
            if (nextElf < currentElf) currentElf--
//            println("$curr stealing from $next, removing next at $nextElf")

            if (presents.size % 1_000 == 0) println(presents.size)

            currentElf = (currentElf + 1) % presents.size
            nextElf = findOppositeElf(presents, currentElf)

            // only one elf left -> done
            if (currentElf == nextElf)
                break
        }
        return presents.first().first
    }

    private fun findOppositeElf(presents: List<Pair<Int, Int>>, start: Int): Int {
        return (start + presents.size / 2) % presents.size
    }

    // https://www.reddit.com/r/adventofcode/comments/5j4lp1/comment/dbdj8jh/?utm_source=share&utm_medium=web3x&utm_name=web3xcss&utm_term=1&utm_content=share_button
    fun partOne(): Int {
        val size = elves
        val queue = ArrayDeque<Int>()
        queue.addAll(IntStream.rangeClosed(1, size).boxed().toList())
        while (queue.size > 1) {
            queue.add(queue.removeFirst())
            queue.removeFirst()
        }
        return queue.removeFirst()
    }

    fun partTwo(): Int {
        val size = elves
        val left = ArrayDeque((1..size/2).toList())
        val right = ArrayDeque((size/2+1.. size).toList())

        while (left.size + right.size > 1) {
            right.removeFirst()
            right.add(left.removeFirst())
            if ((left.size + right.size) % 2 == 0) left.add(right.removeFirst())
        }
        return right.removeFirst()
    }
}
