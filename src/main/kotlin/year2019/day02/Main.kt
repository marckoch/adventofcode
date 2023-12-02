package year2019.day02

fun main() {
    // part1
    execute(SAMPLE, 9, 10).let { println(it) } // 9 and 10 to leave initial programm unchanged
    // restore "1202 program alarm" state
    execute(INPUT, 12, 2).let { println(it) } // 4138658

    // part2
    for (noun in 0..99) {
        for (verb in 0..99) {
            val result = execute(INPUT, noun, verb)
            if (result == 19690720) {
                println("noun: $noun, verb: $verb -> result: $result")
                println("100 * noun + verb = ${100 * noun + verb}")
                return
            }
        }
    }
}

fun execute(initialProgramm: String, noun: Int, verb: Int): Int {
    val programm = toIntList(initialProgramm).toMutableList()
    programm[1] = noun
    programm[2] = verb
    return Computer(programm).run()
}

class Computer(private val programm: MutableList<Int>) {
    private var currentPosition = 0

    fun run(): Int {
        while (true) {
            val opCode = programm[currentPosition]

            // we are done!
            if (opCode == 99) {
                return programm[0]
            }

            // calculate result
            val input1 = programm[programm[currentPosition + 1]]
            val input2 = programm[programm[currentPosition + 2]]
            val result = when (opCode) {
                1 -> input1 + input2
                2 -> input1 * input2
                else -> throw IllegalArgumentException("Unknown opCode $opCode")
            }

            // Store result in outputPos
            val outputPos = programm[currentPosition + 3]
            programm[outputPos] = result

            // Move to next instruction
            currentPosition += 4
        }
    }
}

fun toIntList(input: String): List<Int> {
    return input.split(",").map { it.toInt() }
}