package year2016.day23

import adventofcode.Problem

fun main() {
    AOC2016D23(SAMPLE).run()
    AOC2016D23(INPUT).run()
}

class AOC2016D23(input: String) : Problem(input) {
    private val initialInstructions = inputLines().map { parseInstruction(it) }
    override fun solve1() = execute(State(a = 7, b = 0, c = 0, d = 0, pos = 0, instructions = initialInstructions))
    override fun solve2() = execute(State(a = 12, b = 0, c = 0, d = 0, pos = 0, instructions = initialInstructions))

    private fun execute(initialState: State): Int {
        var currentState = initialState
        while (currentState.pos in currentState.instructions.indices) {
//            println(currentState)
            val instruction = currentState.currentInstruction()
//            println("${currentState.pos} -> $instruction")
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
            "jnz" -> JumpNotZero(source = parts[1], offset = parts[2])
            "tgl" -> Toggle(register = parts[1])
            else -> error("Unknown operation in line: $line")
        }
    }
}

// note: instructions are now mutable and part of the state!
data class State(val a: Int, val b: Int, val c: Int, val d: Int, val pos: Int, val instructions: List<Instruction>) {
    fun modifyRegister(register: String, operation: (Int) -> Int): State {
        return when (register) {
            "a" -> this.copy(a = operation(a))
            "b" -> this.copy(b = operation(b))
            "c" -> this.copy(c = operation(c))
            "d" -> this.copy(d = operation(d))
            else -> throw IllegalArgumentException("Unknown register: $register")
        }
    }

    fun getRegisterValue(register: String): Int {
        return when (register) {
            "a" -> a
            "b" -> b
            "c" -> c
            "d" -> d
            else -> throw IllegalArgumentException("Unknown register: $register")
        }
    }

    fun resolve(v: String): Int {
        return v.toIntOrNull()?: getRegisterValue(v)
    }

    fun incPos(offset: Int = 1) = this.copy(pos = pos + offset)

    fun currentInstruction() = instructions[pos]
}

interface Instruction {
    fun modify(state: State): State
    fun toggle(): Instruction
}

data class Copy(private val source: String, private val register: String) : Instruction {
    override fun modify(state: State): State {
        return state.modifyRegister(register) { state.resolve(source) }.incPos()
    }
    override fun toggle() = JumpNotZero(this.source, this.register)
}

data class Inc(private val register: String) : Instruction {
    override fun modify(state: State): State {
        return state.modifyRegister(register, Int::inc).incPos()
    }
    override fun toggle() = Dec(this.register)
}

data class Dec(private val register: String) : Instruction {
    override fun modify(state: State): State {
        return state.modifyRegister(register, Int::dec).incPos()
    }
    override fun toggle() = Inc(this.register)
}

data class JumpNotZero(private val source: String, private val offset: String) : Instruction {
    override fun modify(state: State): State {
        return state.incPos(offset = if (state.resolve(source) != 0) state.resolve(offset) else 1)
    }
    override fun toggle() = Copy(this.source, this.offset)
}

data class Toggle(private val register: String) : Instruction {
    override fun modify(state: State): State {
        val offset = state.getRegisterValue(register)
        val toggleIndex = state.pos + offset

        // Check if the index is valid instead of relying on a try-catch
        if (toggleIndex !in state.instructions.indices) {
            println("Index out of bounds: $toggleIndex")
            return state.incPos()
        }

        // replace instruction with toggled one
        val newInstructions = state.instructions.mapIndexed { index, instruction ->
            if (index == toggleIndex) instruction.toggle() else instruction
        }

        return state.copy(instructions = newInstructions).incPos()
    }
    override fun toggle() = Inc(this.register)
}