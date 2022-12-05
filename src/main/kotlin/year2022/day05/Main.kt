package year2022.day05

fun main() {
    solve(SampleInput(), Part1::moveCrates)
    solve(SampleInput(), Part2::moveCrates)

    // VGBBJCRMN
    solve(RealInput(), Part1::moveCrates)
    // LBBVJBRMH
    solve(RealInput(), Part2::moveCrates)
}

object Part1 {
    fun moveCrates(stacks: Stacks, noOfCrates: Int, from: Int, to: Int): Stacks {
        repeat(noOfCrates) {
            val element = stacks[from].removeLast()
            stacks[to].add(element)
        }
        return stacks
    }
}

object Part2 {
    fun moveCrates(stacks: Stacks, noOfCrates: Int, from: Int, to: Int): Stacks {
        val elements = stacks[from].takeLast(noOfCrates)
        repeat(noOfCrates) { stacks[from].removeLast() }
        stacks[to].addAll(elements)
        return stacks
    }
}

fun solve(input: Input, move: (stacks: Stacks, noOfCrates: Int, from: Int, to: Int) -> Stacks) {
    input.moves()
        .map { parse(it) }
        .fold(input.stacks) { acc, ints ->
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
    val matchResult = Regex("""move (\d+) from (\d) to (\d)""").find(line)
    val (count, from, to) = matchResult!!.destructured
    return listOf(count, from, to).map { s -> s.toInt() }
}
