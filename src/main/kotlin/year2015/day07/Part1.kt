package year2015.day07

fun main() {
    var instr = INPUT.lines()
    do {
        println("---")
        println("no of instructions: ${instr.size}")
        println(instr)

        instr = reduce(instr)
    } while (instr.size > 1) // or break when we have evaluated 'a'
}
