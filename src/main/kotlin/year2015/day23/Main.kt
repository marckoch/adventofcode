package year2015.day23

import adventofcode.Problem
import util.ints.isEven

fun main() {
    AOC2015D23(SAMPLE).run()
    AOC2015D23(INPUT).run()
}

class AOC2015D23(input: String) : Problem(input) {
    var a = 0
    var b = 0

    override fun solve1() = run(0)
    override fun solve2() = run(1)

    private fun run(initialA: Int): Int {
        // reset state to initial values before run
        a = initialA
        b = 0

        var pos = 0

        val instructions = inputLines().map { parseInstruction(it) }
        while (pos in instructions.indices) {
            val (operation, register, offset) = instructions[pos]
            when (operation) {
                Instruction.HLF -> {
                    modifyRegister(register) { it / 2 }
                    pos++
                }
                Instruction.TPL -> {
                    modifyRegister(register) { it * 3 }
                    pos++
                }
                Instruction.INC -> {
                    modifyRegister(register) { it + 1 }
                    pos++
                }
                Instruction.JMP -> pos += offset!!
                Instruction.JIE -> {
                    if (getRegisterValue(register).isEven()) pos += offset!! else pos++
                }
                Instruction.JIO -> {
                    if (getRegisterValue(register) == 1) pos += offset!! else pos++
                }
            }
        }
        return b
    }

    private fun parseInstruction(line: String): InstructionData {
        val parts = line.replace(",", "").split(" ")
        val operation = Instruction.valueOf(parts[0].uppercase())

        if (operation == Instruction.JMP) {
            val offset = parts[1].toInt()
            return InstructionData(operation, null, offset)
        } else {
            val register = parts.getOrNull(1)
            val offset = parts.getOrNull(2)?.toInt()
            return InstructionData(operation, register, offset)
        }
    }

    private fun getRegisterValue(register: String?): Int {
        return when (register) {
            "a" -> a
            "b" -> b
            else -> throw IllegalArgumentException("Unknown register: $register")
        }
    }

    private fun modifyRegister(register: String?, operation: (Int) -> Int) {
        when (register) {
            "a" -> a = operation(a)
            "b" -> b = operation(b)
        }
    }

    private data class InstructionData(
        val operation: Instruction,
        val register: String?,
        val offset: Int?
    )

    private enum class Instruction {
        HLF, TPL, INC, JMP, JIE, JIO
    }
}
