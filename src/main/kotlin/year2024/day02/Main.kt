package year2024.day02

fun main() {
    AOC2024D02(SAMPLE).solvePart1().let { println(it) } // 2
    AOC2024D02(INPUT).solvePart1().let { println(it) }  // 680

    AOC2024D02(SAMPLE).solvePart2().let { println(it) } // 4
    AOC2024D02(INPUT).solvePart2().let { println(it) }  // 710
}

class AOC2024D02(rawInput: String) {
    private val reports = rawInput.lines().map { s -> s.split(" ").map { it.toLong() } }

    fun solvePart1(): Int {
        return reports.count { isSafe1(it) }
    }

    fun solvePart2(): Int {
        return reports.count { isSafe2(it) }
    }
}

fun isSafe1(report: Report) : Boolean {
    val listOfLevelPairs = report.windowed(2, 1).map { it[0] to it[1] }
    return isSafe(listOfLevelPairs)
}

fun isSafe2(report: Report) : Boolean {
    val reducedLevels = createListsByRemovingOne(report)
    return reducedLevels.any { it ->
        val listOfLevelPairs = it.windowed(2, 1).map { it[0] to it[1] }
        isSafe(listOfLevelPairs)
    }
}

fun isSafe(pairs: List<Pair<Long, Long>>): Boolean {
    val allIncreasing = pairs.all { it.second - it.first in 1..3 }
    val allDecreasing = pairs.all { it.first - it.second in 1..3 }
    val isSafe = allIncreasing || allDecreasing
    return isSafe
}

// create list of original lists with one element removed
// [1, 2, 3, 4] -> [[2, 3, 4], [1, 3, 4], [1, 2, 4], [1, 2, 3]]
fun <T> createListsByRemovingOne(original: List<T>): List<List<T>> {
    return List(original.size) { index ->
        original.filterIndexed { i, _ -> i != index }
    }
}

typealias Report = List<Long>