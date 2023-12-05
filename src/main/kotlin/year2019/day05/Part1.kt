package year2019.day05

fun main() {
    execute("3,0,4,0,99", 1).let { println(it) }

    execute("1002,4,3,4,33", 1).let { println(it) }

    execute(PROGRAMM, 1).let { println(it) } // 5044655
}

fun execute(initialProgramm: String, input: Int): List<Int> {
    val programm = toIntList(initialProgramm).toMutableList()
    return Computer(programm).run(input)
}

class Computer(private val programm: MutableList<Int>) {
    private var currentPosition = 0

    fun run(input: Int): List<Int> {
        val outputs = mutableListOf<Int>()
        while (true) {
            val opCode = programm[currentPosition]

            // we are done!
            if (opCode == 99) {
                return outputs
            }

            // process programm
            when (val opCodeLastChar = opCode.toString().last()) {
                '1', '2' -> {
                    val operation = if (opCodeLastChar == '1')
                        { x: Int, y: Int -> x + y }
                    else
                        { x: Int, y: Int -> x * y }

                    // println("[${programm[currentPosition]} ${programm[currentPosition + 1]} ${programm[currentPosition + 2]} ${programm[currentPosition + 3]}]")

                    val (modeParam1, modeParam2) = determineParameterModes(opCode)
                    val input1 = getParam(programm, currentPosition + 1, modeParam1)
                    val input2 = getParam(programm, currentPosition + 2, modeParam2)
                    val result = operation(input1, input2)

                    // Store result in outputAddress
                    val outputAddress = programm[currentPosition + 3]
                    programm[outputAddress] = result

                    //val opChar = if (opCodeLastChar == '1') '+' else '*'
                    //println("$opCodeLastChar: storing input1=$input1 $opChar input2=$input2 = $result at address $outputAddress")
                    // println(programm)

                    // Move to next instruction, opcode 1 or 2 has consumed 4 parameters
                    currentPosition += 4
                }
                '3', '4' -> {
                    // ("[${programm[currentPosition]} ${programm[currentPosition + 1]}]")

                    if (opCodeLastChar == '3') {
                        val outputAddress = programm[currentPosition + 1]
                        // println("3: storing input=$input at address $outputAddress")
                        programm[outputAddress] = input
                    } else {
                        val modeParam1 = determineParameterModeForOpCode4(opCode)
                        val param1 = getParam(programm, currentPosition + 1, modeParam1)
                        // println("4: printing value $param1")
                        // println(param1)
                        outputs.add(param1)
                    }

                    // Move to next instruction, opcode 3 or 4 has only consumed 2 parameters
                    currentPosition += 2
                }
                else -> throw IllegalArgumentException("Unknown opCode $opCode at currentPosition $currentPosition in $programm")
            }
        }
    }
}
