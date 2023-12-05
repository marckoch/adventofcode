package year2019.day05


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

// ugly hack to determine parameter mode for opcode 4,
// could and should be refactored/merged with determineParameterModes above
fun determineParameterModeForOpCode4(opCode: Int): ParameterMode {
    // format int to 3 digits, pad left with '0'
    val opCodeStr = opCode.toString().padStart(3, '0')
    return ParameterMode.fromChar(opCodeStr[0])
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