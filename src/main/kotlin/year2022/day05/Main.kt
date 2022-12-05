package year2022.day05

fun main() {
    // VGBBJCRMN
    solve(Part1::moveFromTo)

    // LBBVJBRMH
//    solve(Part2::moveFromTo)
}

val regex = Regex("""move (\d+) from (\d) to (\d)""")

object Part1 {
    fun moveFromTo(stacks: Stacks, noOfCrates: Int, from: Int, to: Int): Stacks {
        repeat(noOfCrates) {
            val element = stacks[from].removeLast()
            stacks[to].add(element)
        }
        return stacks
    }
}

object Part2 {
    fun moveFromTo(stacks: Stacks, noOfCrates: Int, from: Int, to: Int): Stacks {
        val elements = stacks[from].takeLast(noOfCrates)
        repeat(noOfCrates) { stacks[from].removeLast() }
        stacks[to].addAll(elements)
        return stacks
    }
}

fun solve(move: (stacks: Stacks, noOfCrates: Int, from: Int, to: Int) -> Stacks) {
    INPUT.lines()
        .map { parse(it) }
        .fold(stacks) { acc, ints ->
            move(acc, ints[0], ints[1] - 1, ints[2] - 1)
        }
        .let { printTopContainers(it) }
}

fun printTopContainers(stacks: Stacks) {
    stacks
        .map { it.last() }
        .joinToString("")
        .let { println(it) }
}

// parse line 'move $count from $from to $to' and return list with (count, from, to)
fun parse(line: String): List<Int> {
    val matchResult = regex.find(line)
    val (count, from, to) = matchResult!!.destructured
    return listOf(count, from, to).map { s -> s.toInt() }
}

typealias Stacks = List<MutableList<Char>>