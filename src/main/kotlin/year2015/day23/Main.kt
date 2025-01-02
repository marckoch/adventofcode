package year2015.day23

import adventofcode.Problem
import util.ints.isEven

fun main() {
    AOC2015D23(SAMPLE).run()
    AOC2015D23(INPUT).run()
}

class AOC2015D23(input: String) : Problem(input) {
    override fun solve1() = run(State(a = 0, b = 0, pos = 0))
    override fun solve2() = run(State(a = 1, b = 0, pos = 0))

    private fun run(initial: State): Int {
        val instructions = inputLines().map { parseInstruction(it) }

        var currentState = initial
        while (currentState.pos in instructions.indices) {
            val instruction = instructions[currentState.pos]
            currentState = instruction.modify(currentState)
        }
        return currentState.b
    }

    private fun parseInstruction(line: String): Instruction {
        val parts = line.replace(",", "").split(" ")
        return when (parts[0]) {
            "hlf" -> Half(register = parts[1])
            "tpl" -> Triple(register = parts[1])
            "inc" -> Inc(register = parts[1])
            "jmp" -> Jump(offset = parts[1].toInt())
            "jie" -> JumpIfEven(register = parts[1], offset = parts[2].toInt())
            "jio" -> JumpIfOne(register = parts[1], offset = parts[2].toInt())
            else -> error("Unknown operation in line: $line")
        }
    }
}

data class State(val a: Int, val b: Int, val pos: Int) {
    fun modifyRegister(register: String, operation: (Int) -> Int): State {
        return when (register) {
            "a" -> this.copy(a = operation(a))
            "b" -> this.copy(b = operation(b))
            else -> throw IllegalArgumentException("Unknown register: $register")
        }
    }

    fun getRegisterValue(register: String): Int {
        return when (register) {
            "a" -> a
            "b" -> b
            else -> throw IllegalArgumentException("Unknown register: $register")
        }
    }

    fun incPos(offset: Int = 1) = this.copy(pos = pos + offset)
}

interface Instruction {
    fun modify(state: State): State
}

class Half(private val register: String) : Instruction {
    override fun modify(state: State): State {
        return state.modifyRegister(register) { it / 2 }.incPos()
    }
}

class Triple(private val register: String) : Instruction {
    override fun modify(state: State): State {
        return state.modifyRegister(register) { it * 3 }.incPos()
    }
}

class Inc(private val register: String) : Instruction {
    override fun modify(state: State): State {
        return state.modifyRegister(register, Int::inc).incPos()
    }
}

class Jump(private val offset: Int) : Instruction {
    override fun modify(state: State) = state.incPos(offset = offset)
}

class JumpIfEven(private val register: String, private val offset: Int) : Instruction {
    override fun modify(state: State): State {
        return state.incPos(offset = if (state.getRegisterValue(register).isEven()) offset else 1)
    }
}

class JumpIfOne(private val register: String, private val offset: Int) : Instruction {
    override fun modify(state: State): State {
        return state.incPos(offset = if (state.getRegisterValue(register) == 1) offset else 1)
    }
}