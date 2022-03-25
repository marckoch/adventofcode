package year2015.day07

fun main() {
    var instr = INPUT.lines()

    // replace '.* -> b' with '46065 -> b' which was a's signal in Part 1
    instr = instr.map { i ->
        if ("(.*) -> b".toRegex().matches(i)) {
            "46065 -> b"
        } else i
    }

    // rest is the same as in Part 1
    do {
        println("---")
        println("no of instructions: ${instr.size}")
        println(instr)

        instr = reduce(instr)
    } while (instr.size > 1) // or break when we have evaluated 'a'
}
