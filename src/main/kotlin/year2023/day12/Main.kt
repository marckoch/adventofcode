package year2023.day12

fun main() {
    bruteForce(SAMPLE1_1).let { println(it) } // 6
    bruteForce(SAMPLE1_2).let { println(it) } // 21
    bruteForce(INPUT).let { println(it) } // 7350
}

// produce every combination of the string and compare against dynamically build RegEx
fun bruteForce(input: String): Int {
    val rows = input.lines().drop(1)

    return rows.sumOf { row ->
        val (line, numbers) = row.split(" ")
        val numbersList = numbers.split(",").map { it.toInt() }

        val regExp = buildRegExp(numbersList).toRegex()
        val variations = expand(line)
        variations.count { it.matches(regExp) }
    }
}

// build a regular expression that matches the given numbers
// beginning and end of the string are padded with zero or more dots (.*)
// between each number has to be at least one dot (.+)
fun buildRegExp(numbers: List<Int>): String {
    return numbers.joinToString(separator = """\.+""", prefix = """\.*""", postfix = """\.*""") { "#{$it}" }
}

// we take a line and expand it to all possible variations
// when a '?' is encountered, we add both possibilities ('.', '#') to each string
fun expand(line: String): List<String> {
    return line.fold(mutableListOf("")) { acc, c ->
        if (c == '?') {
            // add both possibilities ('.', '#') to each string
            acc.flatMap { duplicate(it) }.toMutableList()
        } else {
            // just add the char to each string
            acc.map { it + c }.toMutableList()
        }
    }
}

fun duplicate(s: String): List<String> {
    return listOf(s + '.', s + '#')
}