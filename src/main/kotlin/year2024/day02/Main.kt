package year2024.day02


fun main() {
    part1(SAMPLE).let { println(it) } // 2
    part1(INPUT).let { println(it) }  // 680

    part2(SAMPLE).let { println(it) } // 4
    part2(INPUT).let { println(it) }  // 710
}

fun part1(input: String): Int {
    return input.lines().count { line -> isSafe1(line) }
}

fun part2(input: String): Int {
    return input.lines().count { line -> isSafe2(line) }
}

fun isSafe1(line: String) : Boolean {
    val levels = line.split(" ").map { it.toLong() }
    val listOfLevelPairs = levels.windowed(2, 1).map { it[0] to it[1] }
    return isSafe(listOfLevelPairs)
}

fun isSafe2(line: String) : Boolean {
    val levels = line.split(" ").map { it.toLong() }
    val reducedLevels = createListsByRemovingOne(levels)
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