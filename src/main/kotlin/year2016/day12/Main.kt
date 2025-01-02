package year2016.day12

import adventofcode.Problem
import util.strings.isNumber

fun main() {
    AOC2016D12(SAMPLE).run()
    AOC2016D12(INPUT).run()
}

class AOC2016D12(input: String) : Problem(input) {
    var a = 0
    var b = 0
    var c = 0
    var d = 0

    override fun solve1() = execute(0)
    override fun solve2() = execute(1)

    private fun execute(initialC: Int): Int {
        // reset state to initial values before run
        a = 0
        b = 0
        c = initialC
        d = 0

        var pos = 0

        val instructions = inputLines().map { parseInstruction(it) }
        while (pos in instructions.indices) {
            val (operation, register, newValue, offset) = instructions[pos]
            when (operation) {
                Instruction.CPY -> {
                    modifyRegister(register) { getValue(newValue) }
                    pos++
                }
                Instruction.INC -> {
                    modifyRegister(register, Int::inc)
                    pos++
                }
                Instruction.DEC -> {
                    modifyRegister(register, Int::dec)
                    pos++
                }
                Instruction.JNZ -> if (getValue(register) != 0) pos += offset!! else pos++
            }
        }
        return a
    }

    fun getValue(v: String?): Int {
        return if (v!!.isNumber()) {
             v.toInt()
        } else {
             getRegisterValue(v)
        }
    }

    private fun parseInstruction(line: String): InstructionData {
        val parts = line.split(" ")
        when (val operation = Instruction.valueOf(parts[0].uppercase())) {
            Instruction.CPY -> {
                val newValue = parts[1]
                val register = parts[2]
                return InstructionData(operation, register, newValue, null)
            }
            Instruction.INC, Instruction.DEC -> {
                val register = parts[1]
                return InstructionData(operation, register, null, null)
            }
            Instruction.JNZ -> {
                val register = parts[1]
                val offset = parts[2].toInt()
                return InstructionData(operation, register, null, offset)
            }
        }
    }

    private fun getRegisterValue(register: String?): Int {
        return when (register) {
            "a" -> a
            "b" -> b
            "c" -> c
            "d" -> d
            else -> throw IllegalArgumentException("Unknown register: $register")
        }
    }

    private fun modifyRegister(register: String?, operation: (Int) -> Int) {
        when (register) {
            "a" -> a = operation(a)
            "b" -> b = operation(b)
            "c" -> c = operation(c)
            "d" -> d = operation(d)
        }
    }

    private data class InstructionData(
        val operation: Instruction,
        val register: String?,
        val newValue: String?,
        val offset: Int?
    )

    private enum class Instruction {
        CPY, INC, DEC, JNZ
    }
}
