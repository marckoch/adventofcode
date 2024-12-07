package year2024.day07

fun main() {
    AOC2024D07(SAMPLE).solve(false).let { println(it) } // 3749
    AOC2024D07(INPUT).solve(false).let { println(it) } // 4364915411363

    AOC2024D07(SAMPLE).solve(true).let { println(it) } // 11387
    AOC2024D07(INPUT).solve(true).let { println(it) } // 38322057216320
}

class AOC2024D07(input: String) {
    private val equations = input.lines().map { parseEquation(it) }

    fun solve(checkConcatenation: Boolean): Long {
        return equations.filter { isTrue(it, checkConcatenation) }.sumOf { it.first }
    }

    /**
     * idea: recursively check if the equation is true by going backwards
     */
    private fun isTrue(eq: Equation, checkConcatenation: Boolean): Boolean {
        val (sum, arguments) = eq

        if (arguments.isEmpty()) return sum == 0L

        if (arguments.size == 1) {
            if (sum == arguments.first())
                return true
        } else {
            // check minus
            val newEqMinus = Equation(sum - arguments.last(), arguments.dropLast(1))
            if (isTrue(newEqMinus, checkConcatenation))
                return true

            // check division
            if (sum % arguments.last() == 0L) {
                val newEqDiv = Equation(sum / arguments.last(), arguments.dropLast(1))
                if (isTrue(newEqDiv, checkConcatenation)) {
                    return true
                }
            }

            if (checkConcatenation) {
                // invert concatenation
                val sumStr = sum.toString()
                val lastArgStr = arguments.last().toString()
                if (sumStr.endsWith(lastArgStr)) {
                    val newSumStrippedStr = sumStr.substring(0, sumStr.length - lastArgStr.length)
                    if (newSumStrippedStr.isEmpty()) return false
                    val newEqConcat = Equation(newSumStrippedStr.toLong(), arguments.dropLast(1))
                    if (isTrue(newEqConcat, true)) {
                        return true
                    }
                }
            }
        }
        return false
    }

    private fun parseEquation(s: String): Equation {
        val (leftStr, rightStr) = s.split(":")
        val lhs = leftStr.trim().toLong()
        val rhs = rightStr.trim().split(" ").map { it.toLong() }
        return Pair(lhs, rhs)
    }
}

typealias Equation = Pair<Long, List<Long>>