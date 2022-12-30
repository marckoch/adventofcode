package year2020.day18

fun main() {
//    Part1().solve("1 + 2 * 3 + 4 * 5 + 6").let { println("part1: $it") }
//    Part1().solve("1 + (2 * 3) + (4 * (5 + 6))").let { println("part1: $it") }
//    Part1().solve("2 * 3 + (4 * 5)").let { println("part1: $it") }
//    Part1().solve("5 + (8 * 3 + 9 + 3 * 4 * 3)").let { println("part1: $it") }
//    Part1().solve("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))").let { println("part1: $it") }
//    Part1().solve("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2").let { println("part1: $it") }

    Part1().solve(SAMPLE).let { println("part1: $it") }
    Part1().solve(INPUT).let { println("part1: $it") }

//    Part2().solve("1 + 2 * 3 + 4 * 5 + 6").let { println("part2: $it") }
//    Part2().solve("1 + (2 * 3) + (4 * (5 + 6))").let { println("part2: $it") }
//    Part2().solve("2 * 3 + (4 * 5)").let { println("part2: $it") }
//    Part2().solve("5 + (8 * 3 + 9 + 3 * 4 * 3)").let { println("part2: $it") }
//    Part2().solve("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))").let { println("part2: $it") }
//    Part2().solve("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2").let { println("part2: $it") }

    Part2().solve(SAMPLE).let { println("part2: $it") }
    Part2().solve(INPUT).let { println("part2: $it") }
}

abstract class Solver {
    // means: we have a line with parentheses, inner group has no parentheses!
    // e.g. group1(group2)group3   group2 is digits, blanks, plus or minus, NO parentheses!
    val PARENTHESES = """(.*?)\(([\d +*]*)\)(.*)""".toRegex()
    val ADDITION = """(.*?)(\d+) \+ (\d+)(.*)""".toRegex()
    val MULTIPLICATION = """(.*?)(\d+) \* (\d+)(.*)""".toRegex()

    // number1 op number2 rest
    val FIRST_OPERATION = """(\d+) ([+*]) (\d+)(.*)""".toRegex()

    fun solve(input: String): Long {
        return input.lines().sumOf { evaluate(it) }
    }

    abstract fun evaluate(line: String): Long
}

class Part1 : Solver() {
    override fun evaluate(line: String): Long {
        // parentheses always come first!
        if (line.matches(PARENTHESES)) {
            val (beforeParentheses, inParentheses, afterParentheses) = PARENTHESES.find(line)!!.destructured
            return evaluate(beforeParentheses + evaluate(inParentheses) + afterParentheses)
        }
        // now evaluate from left to right, + and * are same precedence
        if (line.matches(FIRST_OPERATION)) {
            val (n1, op, n2, post) = FIRST_OPERATION.find(line)!!.destructured
            if (op == "*")
                return evaluate("" + (n1.toLong() * n2.toLong()) + post)
            else if (op == "+")
                return evaluate("" + (n1.toLong() + n2.toLong()) + post)
        }
        return line.toLong()
    }
}

class Part2 : Solver() {
    override fun evaluate(line: String): Long {
        // parentheses always come first!
        if (line.matches(PARENTHESES)) {
            val (beforeParentheses, inParentheses, afterParentheses) = PARENTHESES.find(line)!!.destructured
            return evaluate(beforeParentheses + evaluate(inParentheses) + afterParentheses)
        }
        // addition before multiplication!
        if (line.matches(ADDITION)) {
            val (pre, n1, n2, post) = ADDITION.find(line)!!.destructured
            return evaluate(pre + (n1.toLong() + n2.toLong()) + post)
        }
        if (line.matches(MULTIPLICATION)) {
            val (pre, n1, n2, post) = MULTIPLICATION.find(line)!!.destructured
            return evaluate(pre + (n1.toLong() * n2.toLong()) + post)
        }
        return line.toLong()
    }
}