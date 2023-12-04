package year2019.day07

fun main() {
    part1("3,15,3,16,1002,16,10,16,1,16,15,15,4,15,99,0,0",
        listOf(4, 3, 2, 1, 0)).let { println(it) } // 43210

    part1("3,23,3,24,1002,24,10,24,1002,23,-1,23,101,5,23,23,1,24,23,23,4,23,99,0,0",
        listOf(0, 1, 2, 3, 4)).let { println(it) } // 54321

    part1("3,31,3,32,1002,32,10,32,1001,31,-2,31,1007,31,0,33,1002,33,7,33,1,33,31,31,1,32,31,31,4,31,99,0,0,0",
        listOf(1, 0, 4, 3, 2)).let { println(it) } // 65210

    // each phase setting is used exactly once --> permutation of 0,1,2,3,4
    permute(listOf(0, 1, 2, 3, 4))
        .map { phaseSettings ->
            {
                val result = part1(PROGRAMM, phaseSettings)
                phaseSettings to result
            }
        }.maxOf { it().second }
        .let { println(it) } // 20413
}

fun part1(programm: String, phaseSettings: List<Int>): Int {
    var input = 0 // initial input is 0

    for (phaseSetting in phaseSettings) {
        // each amplifier has its own input, but always the initial programm
        val inputs = ArrayDeque(listOf(phaseSetting, input))
        val outputs = execute(programm, inputs)

        // keep output as input for next round
        input = outputs.first()
    }
    return input // input is last output
}

fun execute(initialProgramm: String, inputs: ArrayDeque<Int>): List<Int> {
    val programm = toIntList(initialProgramm).toMutableList()
    return Computer(programm).run(inputs)
}

class Computer(private val programm: MutableList<Int>) {
    private var currentPosition = 0

    fun run(inputs: ArrayDeque<Int>): List<Int> {
        val outputs = mutableListOf<Int>()
        while (true) {
            val opCode = programm[currentPosition]

            // we are done!
            if (opCode == 99) {
                return outputs.toList()
            }

            // println(programm.subList(currentPosition, programm.size))

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

                    // val opChar = if (opCodeLastChar == '1') '+' else '*'
                    // println("$opCodeLastChar: storing input1=$input1 $opChar input2=$input2 = $result at address $outputAddress")
                    // println(programm)

                    // Move to next instruction, opcode 1 or 2 has consumed 4 parameters
                    currentPosition += 4
                }

                '3', '4' -> {
                    // println("[${programm[currentPosition]} ${programm[currentPosition + 1]}]")
                    val outputAddress = programm[currentPosition + 1]

                    if (opCodeLastChar == '3') {
                        val input = inputs.removeFirst()
                        // println("3: storing input=$input at address $outputAddress")
                        programm[outputAddress] = input
                    } else {
                        // TODO: this mode 4 does not support immediate mode! (which is only used in sample)
                        // println("4: printing value at address $outputAddress: ${programm[outputAddress]}")
                        // println(programm[outputAddress])
                        outputs.add(programm[outputAddress])
                    }
                    // println(programm)

                    // Move to next instruction, opcode 3 or 4 has only consumed 2 parameters
                    currentPosition += 2
                }

                '5' -> {
                    // println("[${programm[currentPosition]} ${programm[currentPosition + 1]} ${programm[currentPosition + 2]}]")

                    val (modeParam1, modeParam2) = determineParameterModes(opCode)
                    val input1 = getParam(programm, currentPosition + 1, modeParam1)
                    val input2 = getParam(programm, currentPosition + 2, modeParam2)

                    if (input1 != 0) {
                        // println("5: input1=$input1 != 0, jumping to input2=$input2")
                        currentPosition = input2
                    } else {
                        // println("5: input1=$input1 == 0, not jumping")
                        currentPosition += 3
                    }
                }

                '6' -> {
                    // println("[${programm[currentPosition]} ${programm[currentPosition + 1]} ${programm[currentPosition + 2]}]")

                    val (modeParam1, modeParam2) = determineParameterModes(opCode)
                    val input1 = getParam(programm, currentPosition + 1, modeParam1)
                    val input2 = getParam(programm, currentPosition + 2, modeParam2)

                    if (input1 == 0) {
                        // println("6: input1=$input1 == 0, jumping to input2=$input2")
                        currentPosition = input2
                    } else {
                        // println("6: input1=$input1 != 0, not jumping")
                        currentPosition += 3
                    }
                }

                '7' -> {
                    // println("[${programm[currentPosition]} ${programm[currentPosition + 1]} ${programm[currentPosition + 2]} ${programm[currentPosition + 3]}]")

                    val (modeParam1, modeParam2) = determineParameterModes(opCode)
                    val input1 = getParam(programm, currentPosition + 1, modeParam1)
                    val input2 = getParam(programm, currentPosition + 2, modeParam2)

                    val outputAddress = programm[currentPosition + 3]
                    val result = if (input1 < input2) 1 else 0
                    programm[outputAddress] = result

                    // println("7: storing input1=$input1 < input2=$input2 = $result at address $outputAddress")
                    currentPosition += 4
                }

                '8' -> {
                    // println("[${programm[currentPosition]} ${programm[currentPosition + 1]} ${programm[currentPosition + 2]} ${programm[currentPosition + 3]}]")

                    val (modeParam1, modeParam2) = determineParameterModes(opCode)
                    val input1 = getParam(programm, currentPosition + 1, modeParam1)
                    val input2 = getParam(programm, currentPosition + 2, modeParam2)

                    val outputAddress = programm[currentPosition + 3]
                    val result = if (input1 == input2) 1 else 0
                    programm[outputAddress] = result

                    // println("8: storing input1=$input1 == input2=$input2 = $result at address $outputAddress")
                    currentPosition += 4
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

// https://rosettacode.org/wiki/Permutations#Kotlin
fun <T> permute(input: List<T>): List<List<T>> {
    if (input.size == 1) return listOf(input)

    val perms = mutableListOf<List<T>>()
    val head = input[0]
    val tail = input.drop(1)
    for (perm in permute(tail)) {
        for (i in 0..perm.size) {
            val newPerm = perm.toMutableList()
            newPerm.add(i, head)
            perms.add(newPerm)
        }
    }
    return perms
}