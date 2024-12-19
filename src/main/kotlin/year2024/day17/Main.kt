package year2024.day17

import kotlin.math.pow

fun main() {
    AOC2024D17(SAMPLE).solve().also(::println)
    AOC2024D17(SAMPLE2).solve().also(::println)
    AOC2024D17(INPUT).solve().also(::println)

//    AOC2024D17(INPUT).solve2().also(::println)
}

// 2,4,1,4,7,5,4,1,1,4,5,5,0,3,3,0
// 2,4  b = a % 8
// 1,4  b = b xor 4
// 7,5  c = a shr b
// 4,1  b = b xor c
// 1,4  b = b xor 4
// 5,5  output b
// 0,3  a = a shr 3
// 3,0  loop while a != 0

class AOC2024D17(val input: String) {
    fun solve(): String {
        Computer(input).printState()
        return Computer(input).run()
    }

    fun solve2(): Int {
        val initialProgramm = input.lines()[4].split(": ")[1]
        var resultProgramm = ""
        var newA = 0

        while (initialProgramm != resultProgramm && newA < 1024) {
            newA++
            val c = Computer(input)
            c.setA(newA)
//            c.printState()
            resultProgramm = c.run(true)
//            c.printState()
            println("$newA: $initialProgramm -> $resultProgramm")
        }
        return newA
    }
}

data class Instruction(val instr: Int)
data class Operand(val literalOperand: Int)

class Computer(input: String) {
    private var regA = 0
    private var regB = 0
    private var regC = 0
    private var programm: List<Pair<Instruction, Operand>>
    private var initialProgram: List<Int>
    private var pointer = 0
    private var output = mutableListOf<Int>()

    init {
        this.regA = parseRegister(input.lines(), 0)
        this.regB = parseRegister(input.lines(), 1)
        this.regC = parseRegister(input.lines(), 2)
        this.programm = input.lines()[4].split(": ")[1].split(",").windowed(2, step = 2).map {
            (i, o) -> Pair(Instruction(i.toInt()), Operand(o.toInt()))
        }
        this.initialProgram = input.lines()[4].split(": ")[1].split(",").map { it.toInt() }

    }

    fun run(abort: Boolean = false): String {
        while (true) {
            val (instr, operand) = programm[pointer]

            val comboOperand = when(operand.literalOperand) {
                in 0..3 -> operand.literalOperand
                4 -> regA
                5 -> regB
                6 -> regC
                else -> throw IllegalStateException("unknown op ${operand.literalOperand}")
            }

            when (instr.instr) {
                0 -> regA /= 2.0.pow(comboOperand).toInt()
                1 -> regB = regB xor operand.literalOperand
                2 -> regB = comboOperand % 8
                3 -> {
                    if (regA != 0) {
                        pointer = operand.literalOperand
                        continue
                    } else {
                        break
                    }
                }
                4 -> regB = regB xor regC
                5 -> {
                    output.add(comboOperand % 8)
                    if (abort && initialProgram.take(output.size) != output) {
                        break
                    }
                }
                6 -> regB = regA / 2.0.pow(comboOperand).toInt()
                7 -> regC = regA / 2.0.pow(comboOperand).toInt()
            }
            pointer++

//            printState2()
        }
        return output.joinToString(",")
    }

    fun printState() {
        println("A: ${this.regA}")
        println("B: ${this.regB}")
        println("C: ${this.regC}")
        println("program: ${this.programm}")
        println("pointer: ${this.pointer}")
        println("output: ${this.output}")
    }

    fun printState2() {
        println("${regA.toString(8)} ${regB.toString(8)} ${regC.toString(8)} $output")
    }

    private fun parseRegister(inputLines: List<String>, i: Int): Int = inputLines[i].split(": ").last().toInt()
    fun setA(newA: Int) {
        regA = newA
    }
}