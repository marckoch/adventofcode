package year2022.day01

fun main() {
    part1()

    part2()
}

// aggregate the lines until blank line is reached, then start next elf
// for the sample on web page this would produce a list: 6000, 4000, 11000, 24000, ...
fun getListOfTotalCalories(): List<Int> {
    return INPUT.lines().fold(mutableListOf(0)) { list, line ->
        if (line.isEmpty()) {
            list.add(0)
        } else {
            list[list.lastIndex] += line.toInt()
        }
        list
    }
}

fun part1() {
    getListOfTotalCalories()
        .maxOrNull()
        .let { println(it) }
}

fun part2() {
    getListOfTotalCalories()
        .sorted()
        .reversed()
        .take(3)
        .sum()
        .let { println(it) }
}
