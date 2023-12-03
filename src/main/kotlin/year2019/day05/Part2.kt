package year2019.day05

fun main() {
    execute2(PROGRAMM, 5) // 7408802
}

fun execute2(initialProgramm: String, input: Int) {
    val programm = toIntList(initialProgramm).toMutableList()
    Computer2(programm).run(input)
}

class Computer2(private val programm: MutableList<Int>) {
    private var currentPosition = 0

    fun run(input: Int) {
        while (true) {
            val opCode = programm[currentPosition]

            // we are done!
            if (opCode == 99) {
                return
            }

            println(programm.subList(currentPosition, programm.size))

            // calculate result
            when (val opCodeLastChar = opCode.toString().last()) {
                '1', '2' -> {
                    val operation = if (opCodeLastChar == '1')
                        { x: Int, y: Int -> x + y }
                    else
                        { x: Int, y: Int -> x * y }

                    println("[${programm[currentPosition]} ${programm[currentPosition + 1]} ${programm[currentPosition + 2]} ${programm[currentPosition + 3]}]")

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
                    println("[${programm[currentPosition]} ${programm[currentPosition + 1]}]")
                    val outputAddress = programm[currentPosition + 1]

                    if (opCodeLastChar == '3') {
                        println("3: storing input=$input at address $outputAddress")
                        programm[outputAddress] = input
                    } else {
                        // TODO: this mode 4 does not support immediate mode! (which is only used in sample)
                        println("4: printing value at address $outputAddress: ${programm[outputAddress]}")
                        println(programm[outputAddress])
                    }
                    // println(programm)

                    // Move to next instruction, opcode 3 or 4 has only consumed 2 parameters
                    currentPosition += 2
                }
                '5' -> {
                    println("[${programm[currentPosition]} ${programm[currentPosition + 1]} ${programm[currentPosition + 2]}]")

                    val (modeParam1, modeParam2) = determineParameterModes(opCode)
                    val input1 = getParam(programm, currentPosition + 1, modeParam1)
                    val input2 = getParam(programm, currentPosition + 2, modeParam2)

                    if (input1 != 0) {
                        println("5: input1=$input1 != 0, jumping to input2=$input2")
                        currentPosition = input2
                    } else {
                        println("5: input1=$input1 == 0, not jumping")
                        currentPosition += 3
                    }
                }
                '6' -> {
                    println("[${programm[currentPosition]} ${programm[currentPosition + 1]} ${programm[currentPosition + 2]}]")

                    val (modeParam1, modeParam2) = determineParameterModes(opCode)
                    val input1 = getParam(programm, currentPosition + 1, modeParam1)
                    val input2 = getParam(programm, currentPosition + 2, modeParam2)

                    if (input1 == 0) {
                        println("6: input1=$input1 == 0, jumping to input2=$input2")
                        currentPosition = input2
                    } else {
                        println("6: input1=$input1 != 0, not jumping")
                        currentPosition += 3
                    }
                }
                '7' -> {
                    println("[${programm[currentPosition]} ${programm[currentPosition + 1]} ${programm[currentPosition + 2]} ${programm[currentPosition + 3]}]")

                    val (modeParam1, modeParam2) = determineParameterModes(opCode)
                    val input1 = getParam(programm, currentPosition + 1, modeParam1)
                    val input2 = getParam(programm, currentPosition + 2, modeParam2)

                    val outputAddress = programm[currentPosition + 3]
                    val result = if (input1 < input2) 1 else 0
                    programm[outputAddress] = result

                    println("7: storing input1=$input1 < input2=$input2 = $result at address $outputAddress")
                    currentPosition += 4
                }
                '8' -> {
                    println("[${programm[currentPosition]} ${programm[currentPosition + 1]} ${programm[currentPosition + 2]} ${programm[currentPosition + 3]}]")

                    val (modeParam1, modeParam2) = determineParameterModes(opCode)
                    val input1 = getParam(programm, currentPosition + 1, modeParam1)
                    val input2 = getParam(programm, currentPosition + 2, modeParam2)

                    val outputAddress = programm[currentPosition + 3]
                    val result = if (input1 == input2) 1 else 0
                    programm[outputAddress] = result

                    println("8: storing input1=$input1 == input2=$input2 = $result at address $outputAddress")
                    currentPosition += 4
                }
                else -> throw IllegalArgumentException("Unknown opCode $opCode at currentPosition $currentPosition in $programm")
            }
        }
    }
}