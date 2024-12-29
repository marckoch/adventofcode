package year2015.day24

import adventofcode.Problem
import util.lists.combinations

fun main() {
    AOC2015D24(SAMPLE).run()
    AOC2015D24(INPUT).run()
}

class AOC2015D24(input: String) : Problem(input) {
    override fun solve1() = balance(3)
    override fun solve2() = balance(4)

    private fun balance(groups: Int) : Long {
        val presents = inputLines().map { it.toInt() }.sortedDescending()
        val total = presents.sum()
        val targetWeight = total / groups

        var min = Long.MAX_VALUE

        // we need to find the minimal groups size that has a valid minimal quantum score
        for (i in 1..presents.size) {
            val lm = presents.combinations(i)
                .filter { it.sum() == targetWeight }//.forEach(::println)
                .minByOrNull { it.quantum() }
            if (lm != null) {
                min = lm.quantum()
                break
            }
        }

        return min
    }

    private fun List<Int>.quantum(): Long = this.map { it.toLong() }.reduce { acc, i -> acc * i }
}

