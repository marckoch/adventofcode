package year2016.day12

import adventofcode.Problem

fun main() {
    AOC2016D12(SAMPLE).run()
    AOC2016D12(INPUT).run()
}

class AOC2016D12(input: String) : Problem(input) {
    override fun solve1() = execute(State(a = 0, b = 0, c = 0, d = 0, pos = 0))
    override fun solve2() = execute(State(a = 0, b = 0, c = 1, d = 0, pos = 0))

    private fun execute(initialState: State): Int {
        val instructions = inputLines().map { parseInstruction(it) }

        var currentState = initialState
        while (currentState.pos in instructions.indices) {
            val instruction = instructions[currentState.pos]
            currentState = instruction.modify(currentState)
        }
        return currentState.a
    }

    private fun parseInstruction(line: String): Instruction {
        val parts = line.split(" ")
        return when (parts[0]) {
            "cpy" -> Copy(source = parts[1], register = parts[2])
            "inc" -> Inc(register = parts[1])
            "dec" -> Dec(register = parts[1])
            "jnz" -> JumpNotZero(source = parts[1], offset = parts[2].toInt())
            else -> error("Unknown operation in line: $line")
        }
    }
}

data class State(val a: Int, val b: Int, val c: Int, val d: Int, val pos: Int) {
    fun modifyRegister(register: String, operation: (Int) -> Int): State {
        return when (register) {
            "a" -> this.copy(a = operation(a))
            "b" -> this.copy(b = operation(b))
            "c" -> this.copy(c = operation(c))
            "d" -> this.copy(d = operation(d))
            else -> throw IllegalArgumentException("Unknown register: $register")
        }
    }

    private fun getRegisterValue(register: String): Int {
        return when (register) {
            "a" -> a
            "b" -> b
            "c" -> c
            "d" -> d
            else -> throw IllegalArgumentException("Unknown register: $register")
        }
    }

    fun resolve(v: String): Int {
        return v.toIntOrNull() ?: getRegisterValue(v)
    }

    fun incPos(offset: Int = 1) = this.copy(pos = pos + offset)
}

interface Instruction {
    fun modify(state: State): State
}

class Copy(private val source: String, private val register: String) : Instruction {
    override fun modify(state: State): State {
        return state.modifyRegister(register) { state.resolve(source) }.incPos()
    }
}

class Inc(private val register: String) : Instruction {
    override fun modify(state: State): State {
        return state.modifyRegister(register, Int::inc).incPos()
    }
}

class Dec(private val register: String) : Instruction {
    override fun modify(state: State): State {
        return state.modifyRegister(register, Int::dec).incPos()
    }
}

class JumpNotZero(private val source: String, private val offset: Int) : Instruction {
    override fun modify(state: State): State {
        return state.incPos(offset = if (state.resolve(source) != 0) offset else 1)
    }
}