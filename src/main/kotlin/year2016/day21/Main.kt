package year2016.day21

import adventofcode.Problem

fun main() {
    AOC2016D21(SAMPLE).run()
    AOC2016D21(INPUT).run()
}

class AOC2016D21(input: String) : Problem(input) {
    override fun solve1() = encode()
    override fun solve2() = decode()

    private fun encode(): String {
        val initialInput = inputLines()[0]
        val instructions = inputLines().drop(2).map { parseInstruction(it) }

        return instructions.fold(initialInput) { acc, instr -> instr.modify(acc) }
    }

    private fun decode(): String {
        val initialInput = inputLines()[1]
        val instructions = inputLines().drop(2).map { parseInstruction(it) }

        // execute instructions in reversed order and undo each operation
        return instructions.reversed().fold(initialInput) { acc, instr -> instr.undo(acc) }
    }

    private fun parseInstruction(line: String): Instruction {
        val parts = line.split(" ")
        return when {
            line.startsWith("swap position") -> SwapPosition(parts)
            line.startsWith("swap letter") -> SwapLetter(parts)
            line.startsWith("rotate left") -> RotateLeft(parts)
            line.startsWith("rotate right") -> RotateRight(parts)
            line.startsWith("rotate based on position") -> RotatePosition(parts)
            line.startsWith("reverse positions") -> Reverse(parts)
            line.startsWith("move position") -> MovePosition(parts)
            else -> error("Unknown operation in line: $line")
        }
    }
}

interface Instruction {
    fun modify(s: String) : String
    fun undo(s: String) : String
}

data class SwapPosition(private val x: Int, private val y: Int) : Instruction {
    constructor(tokens: List<String>) : this(x = tokens[2].toInt(), y = tokens[5].toInt())

    override fun modify(s: String): String {
        val cx = s[x]
        val cy = s[y]

        val ca = s.toCharArray()
        ca[x] = cy
        ca[y] = cx
        return ca.joinToString("")
    }

    override fun undo(s: String) = SwapPosition(y, x).modify(s)
}

data class SwapLetter(private val x: Char, private val y: Char) : Instruction {
    constructor(tokens: List<String>) : this(x = tokens[2].first(), y = tokens[5].first())

    override fun modify(s: String) = s.replace(x, '_').replace(y, x).replace('_', y)
    override fun undo(s: String) = SwapLetter(y, x).modify(s)
}

data class RotateLeft(private val x: Int) : Instruction {
    constructor(tokens: List<String>) : this(x = tokens[2].toInt())

    override fun modify(s: String): String {
        if (x == 0) return s
        return s.drop(x % s.length) + s.take(x % s.length)
    }

    override fun undo(s: String) = RotateRight(x).modify(s)
}

data class RotateRight(private val x: Int) : Instruction {
    constructor(tokens: List<String>) : this(x = tokens[2].toInt())

    override fun modify(s: String): String {
        if (x == 0) return s
        var z = (s.length - x) % s.length
        if (z < 0) z += s.length
        return s.takeLast(x % s.length) + s.take(z)
    }

    override fun undo(s: String) = RotateLeft(x).modify(s)
}

data class Reverse(private val x: Int, private val y: Int) : Instruction {
    constructor(tokens: List<String>) : this(x = tokens[2].toInt(), y = tokens[4].toInt())

    override fun modify(s: String): String {
        val left = s.substring(0, x)
        val middle = s.substring(x, y + 1)
        val right = s.substring(y + 1)
        return left + middle.reversed() + right
    }

    override fun undo(s: String) = modify(s)

}

data class RotatePosition(private val x: Char) : Instruction {
    constructor(tokens: List<String>) : this(x = tokens[6].first())

    override fun modify(s: String): String {
        val i = s.indexOf(x)
        val rotate = 1 + i + if (i >= 4) 1 else 0
        return RotateRight(rotate % s.length).modify(s)
    }

    override fun undo(s: String): String {
        // just brute force it
        return (0..s.length).map { i -> RotateLeft(i).modify(s) }.first { modify(it) == s }
    }
}

data class MovePosition(private val x: Int, private val y: Int) : Instruction {
    constructor(tokens: List<String>) : this(x = tokens[2].toInt(), y = tokens[5].toInt())

    override fun modify(s: String): String {
        val c = s[x]
        val t = s.toMutableList()
        t.removeAt(x)
        t.add(y, c)
        return t.joinToString("")
    }

    override fun undo(s: String) = MovePosition(y, x).modify(s)
}