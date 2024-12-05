package year2024.day05

fun main() {
    AOC2024D05(SAMPLE).solvePart1().let { println(it) } // 143
    AOC2024D05(INPUT).solvePart1().let { println(it) } // 4185

    AOC2024D05(SAMPLE).solvePart2().let { println(it) } // 123
    AOC2024D05(INPUT).solvePart2().let { println(it) } // 4480
}

class AOC2024D05(input: String) {
    private val rules = input.lines().takeWhile { it.isNotEmpty() }.map { toRule(it) }
    private val updates = input.lines().takeLastWhile { it.isNotEmpty() }.map { it.split(",").map { i -> i.toInt() } }

    fun solvePart1(): Int {
        return updates
            .filter { isCorrect(it, rules) }
            .sumOf { middleElement(it) }
    }

    fun solvePart2(): Int {
        val comp = ComparatorByRules(rules)
        return updates
            .filter { !isCorrect(it, rules) }
            .map { x -> x.sortedWith(comp) }
            .sumOf { middleElement(it) }
    }

    private fun toRule(s: String): Rule {
        val (x, y) = s.split("|").map { it.toInt() }
        return Pair(x, y)
    }

    private fun isCorrect(updates: Updates, rules: List<Rule>): Boolean {
        rules.forEach { r ->
            val indexOfX = updates.indexOf(r.first)
            val indexOfY = updates.indexOf(r.second)

            // we found a rule that is illegal -> update is not correct
            if (indexOfX >= 0 && indexOfY >= 0 && indexOfX > indexOfY) {
                return false
            }
        }
        return true
    }

    private fun <T> middleElement(list: List<T>): T {
        return list[list.size / 2]
    }

    // the rules define our comparison order
    class ComparatorByRules(private val rules: List<Rule>): Comparator<Int> {
        override fun compare(o1: Int, o2: Int): Int {
            if (rules.contains(Rule(o1, o2))) {
                return 1
            }
            if (rules.contains(Rule(o2, o1))) {
                return -1
            }
            return 0
        }
    }
}

typealias Rule = Pair<Int, Int>

typealias Updates = List<Int>