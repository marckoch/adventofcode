package year2019.day05

fun main() {
    execute("3,0,4,0,99", 1)

    execute("1002,4,3,4,33", 1)

    execute(PROGRAMM, 1) // 5044655
}

fun execute(initialProgramm: String, input: Int) {
    val programm = toIntList(initialProgramm).toMutableList()
    Computer(programm).run(input)
}

class Computer(private val programm: MutableList<Int>) {
    private var currentPosition = 0

    fun run(input: Int) {
        while (true) {
            val opCode = programm[currentPosition]

            // we are done!
            if (opCode == 99) {
                return
            }

            // calculate result
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

                    val opChar = if (opCodeLastChar == '1') '+' else '*'
                    println("$opCodeLastChar: storing input1=$input1 $opChar input2=$input2 = $result at address $outputAddress")
                    // println(programm)

                    // Move to next instruction, opcode 1 or 2 has consumed 4 parameters
                    currentPosition += 4
                }
                '3', '4' -> {
                    //println("[${programm[currentPosition]} ${programm[currentPosition + 1]}]")
                    val outputAddress = programm[currentPosition + 1]

                    if (opCodeLastChar == '3') {
                        println("3: storing input=$input at address $outputAddress")
                        programm[outputAddress] = input
                    } else {
                        println("4: printing value at address $outputAddress: ${programm[outputAddress]}")
                        println(programm[outputAddress])
                    }
                    // println(programm)

                    // Move to next instruction, opcode 3 or 4 has only consumed 2 parameters
                    currentPosition += 2
                }
                else -> throw IllegalArgumentException("Unknown opCode $opCode at currentPosition $currentPosition in $programm")
            }
        }
    }
}

fun determineParameterModes(opCode: Int): Pair<ParameterMode, ParameterMode> {
    // format int to 5 digits, pad left with '0'
    val opCodeStr = opCode.toString().padStart(5, '0')
    val modeParam1 = ParameterMode.fromChar(opCodeStr[2])
    val modeParam2 = ParameterMode.fromChar(opCodeStr[1])

    // param 3 is output param and should always be 0 (position mode) and never 1 (immediate mode)
    // "Parameters that an instruction writes to will never be in immediate mode."
    val modeParam3 = ParameterMode.fromChar(opCodeStr[0])
    assert(modeParam3 == ParameterMode.POSITION)

    return modeParam1 to modeParam2
}


fun getParam(programm: List<Int>, position: Int, mode: ParameterMode): Int {
    val param1 = programm[position]
    return if (mode == ParameterMode.POSITION) programm[param1] else param1
}

fun toIntList(input: String): List<Int> {
    return input.split(",").map { it.toInt() }
}

enum class ParameterMode {
    POSITION,
    IMMEDIATE;

    // create enum from char
    companion object {
        fun fromChar(c: Char): ParameterMode {
            return when (c) {
                '0' -> POSITION
                '1' -> IMMEDIATE
                else -> throw IllegalArgumentException("Unknown parameter mode $c")
            }
        }
    }
}