package year2024.day05

fun main() {
    part1(SAMPLE).let { println(it) } // 143
    part1(INPUT).let { println(it) }  // 4185

    part2(SAMPLE).let { println(it) } //
    part2(INPUT).let { println(it) } // 4480
}

fun part1(input: String): Int {
    val rules = input.lines().takeWhile { it.isNotEmpty() }
    val updates = input.lines().takeLastWhile { it.isNotEmpty() }.map { it.split(",").map { i -> i.toInt() } }

    return updates
        .filter { isCorrect(it, rules) }
        .sumOf { middleElement(it) }
}

fun part2(input: String): Int {
    val rules = input.lines().takeWhile { it.isNotEmpty() }
    val comp = ComparatorByRules(rules.map { ruleToPair(it) })
    val updates = input.lines().takeLastWhile { it.isNotEmpty() }.map { it.split(",").map { i -> i.toInt() } }

    return updates
        .filter { !isCorrect(it, rules) }
        .map { x -> x.sortedWith(comp) }
        .sumOf { middleElement(it) }
}

fun ruleToPair(s: String): Pair<Int, Int> {
    val (x, y) = s.split("|").map { it.toInt() }
    return Pair(x, y)
}

fun isCorrect(update: List<Int>, rules: List<String>): Boolean {
    rules.forEach { r ->
        val (x, y) = r.split("|").map { it.toInt() }
        val indexOfX = update.indexOf(x)
        val indexOfY = update.indexOf(y)

        // we found a rule that is illegal -> update is not correct
        if (indexOfX >= 0 && indexOfY >= 0 && indexOfX > indexOfY) {
            return false
        }
    }
    return true
}

fun <T> middleElement(list: List<T>): T {
    val m = list[list.size / 2]
//    println("$list -> $m")
    return m
}

// the rules define our comparison order
class ComparatorByRules(private val rules: List<Pair<Int, Int>>): Comparator<Int> {
    override fun compare(o1: Int?, o2: Int?): Int {
        if (rules.contains(Pair(o1, o2))) {
            return 1
        }
        if (rules.contains(Pair(o2, o1))) {
            return -1
        }
        return 0
    }
}