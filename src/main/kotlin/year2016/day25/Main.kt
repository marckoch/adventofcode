package year2016.day25

import adventofcode.Problem

fun main() {
    AOC2016D25(INPUT).run()
}

// 175
class AOC2016D25(input: String) : Problem(input) {
    private val instructions = inputLines().map { parseInstruction(it) }
    override fun solve1() = solve()
    override fun solve2() = 1//solve3()

    fun solve() {
        (1..1000000).forEach { execute(State(a = it, b = 0, c = 0, d = 0, pos = 0)) }
    }

    private fun execute(initialState: State): Int {
        println(initialState.a)
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
            "jnz" -> JumpNotZero(source = parts[1], offset = parts[2])
            "out" -> Out(source = parts[1])
            else -> error("Unknown operation in line: $line")
        }
    }
}

data class State(val a: Int, val b: Int, val c: Int, val d: Int, val pos: Int) {
    val clock = generateSequence(0, Int::inc).iterator()
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
}

interface Instruction {
    fun modify(state: State): State
}

data class Copy(private val source: String, private val register: String) : Instruction {
    override fun modify(state: State): State {
        return state.modifyRegister(register) { state.resolve(source) }.incPos()
    }
}

data class Inc(private val register: String) : Instruction {
    override fun modify(state: State): State {
        return state.modifyRegister(register, Int::inc).incPos()
    }
}

data class Dec(private val register: String) : Instruction {
    override fun modify(state: State): State {
        return state.modifyRegister(register, Int::dec).incPos()
    }
}

data class JumpNotZero(private val source: String, private val offset: String) : Instruction {
    override fun modify(state: State): State {
        return state.incPos(offset = if (state.resolve(source) != 0) state.resolve(offset) else 1)
    }
}

data class Out(private val source: String) : Instruction {
    override fun modify(state: State): State {
        val v = state.resolve(source)
        if (state.clock.next() != v) return state.incPos(offset = 1000)
        return state.incPos()
    }
}

class AssembunnyVM(raw: List<String>) {
    class BadClock : Throwable()

    val registers = IntArray(4)
    private var pc = 0

    private abstract class Value {
        abstract val value: Int
        abstract val register: Int
    }

    private class Literal(override val value: Int) : Value() {
        override val register: Int
            get() = error("Wrong value passed to op")
    }

    private inner class Register(override val register: Int) : Value() {
        override val value: Int
            get() = this@AssembunnyVM.registers[register]
    }

    private data class Instruction(val op: Op, val arg1: Value, val arg2: Value) {
        fun execute() {
            op(arg1, arg2)
        }
    }

    private abstract class Op {
        abstract operator fun invoke(vararg args: Value)
    }

    private inner class Copy : Op() {
        override fun invoke(vararg args: Value) {
            registers[args[1].register] = args[0].value
        }
    }

    private inner class Inc : Op() {
        override fun invoke(vararg args: Value) {
            registers[args[0].register]++
        }
    }

    private inner class Dec : Op() {
        override fun invoke(vararg args: Value) {
            registers[args[0].register]--
        }
    }

    private inner class JumpNotZero : Op() {
        override fun invoke(vararg args: Value) {
            if (args[0].value != 0) pc += args[1].value - 1
        }
    }

    var clock = generateSequence(0) {
        when (it) {
            0 -> 1
            else -> 0
        }
    }.iterator()

    private inner class Out : Op() {
        override fun invoke(vararg args: Value) {
            if (args[0].value != clock.next())
                throw BadClock()
        }
    }

    private val code: MutableList<Instruction>

    private val opMap = mapOf(
        "cpy" to Copy(),
        "inc" to Inc(),
        "dec" to Dec(),
        "jnz" to JumpNotZero(),
        "out" to Out()
    )

    init {
        code = raw.map { it.trim().split(' ') }
            .map { Instruction(opMap[it[0]]!!, parse(it.getOrNull(1)), parse(it.getOrNull(2))) }.toMutableList()
    }

    private fun execute() {
        code[pc].execute()
        pc++
    }

    private fun parse(arg: String?): Value {
        return arg?.let {
            try {
                Literal(arg.toInt())
            } catch (e: NumberFormatException) {
                val c = arg[0] - 'a'
                Register(c)
            }
        } ?: Register(-1) // This rise and exception if used
    }

    fun run() {
        while (pc < code.size) {
            try {
                execute()
            } catch (e: BadClock) {
                println("Error in the output")
                break
            }
        }
    }

    fun reset() {
        clock = generateSequence(0) {
            when (it) {
                0 -> 1
                else -> 0
            }
        }.iterator()
        pc = 0
        for (n in 0..registers.size - 1)
            registers[n] = 0
    }
}

fun solve3() {
    val input = INPUT

    val vm = AssembunnyVM(input.lines())

    generateSequence(0, Int::inc).forEach {
        vm.reset()
        vm.registers[0] = it
        print("Running vm for a=$it... ")
        vm.run()
    }
}
