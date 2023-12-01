package year2023.day01

fun main() {
    part1(SAMPLE)
    part1(INPUT)

    part2(SAMPLE2)
    part2(INPUT)
}

fun part1(input: String) {
    input.lines().sumOf { extractNumber1(it) }.let { println(it) }
}

fun part2(input: String) {
    input.lines().sumOf { extractNumber2(it) }.let { println(it) }
}

fun extractNumber1(line: String): Int {
    // just keep all digit chars
    val digitChars = line.toCharArray().filter { it.isDigit() }

    val digitAsString = "" + digitChars.first() + digitChars.last()
    return digitAsString.toInt()
}

fun extractNumber2(line: String): Int {
    val digitAsString = "" + getFirstDigit(line) + getLastDigit(line)
    return digitAsString.toInt()
}

// look from the start until a digit is found, either as char '1'-'9' or spelled out as word
fun getFirstDigit(line: String): Char {
    if (line.first().isDigit())
        return line.first()

    if (line.startsWith("one")) {
        return '1'
    } else if (line.startsWith("two")) {
        return '2'
    } else if (line.startsWith("three")) {
        return '3'
    } else if (line.startsWith("four")) {
        return '4'
    } else if (line.startsWith("five")) {
        return '5'
    } else if (line.startsWith("six")) {
        return '6'
    } else if (line.startsWith("seven")) {
        return '7'
    } else if (line.startsWith("eight")) {
        return '8'
    } else if (line.startsWith("nine")) {
        return '9'
    }

    // nothing found, drop first char and try remaining string
    return getFirstDigit(line.substring(1))
}

// look from the end until a digit is found, either as char '1'-'9' or spelled out as word
fun getLastDigit(line: String): Char {
    // look from the end until digit is found
    if (line.last().isDigit())
        return line.last()

    if (line.endsWith("one")) {
        return '1'
    } else if (line.endsWith("two")) {
        return '2'
    } else if (line.endsWith("three")) {
        return '3'
    } else if (line.endsWith("four")) {
        return '4'
    } else if (line.endsWith("five")) {
        return '5'
    } else if (line.endsWith("six")) {
        return '6'
    } else if (line.endsWith("seven")) {
        return '7'
    } else if (line.endsWith("eight")) {
        return '8'
    } else if (line.endsWith("nine")) {
        return '9'
    }

    // nothing found, drop last char and try remaining string
    return getLastDigit(line.dropLast(1))
}

// replacing spelled out letters
// does not work :-(
// 'eightwothree' will become 'eigh23' (23) which is wrong, correct should be '8wo3' (83)
fun cleanUpWrong(line: String): String {
    return line
        .replace("one", "1")
        .replace("two", "2")
        .replace("three", "3")
        .replace("four", "4")
        .replace("five", "5")
        .replace("six", "6")
        .replace("seven", "7")
        .replace("eight", "8")
        .replace("nine", "9")
}

// replacing the first found spelled-out digit
// does not work for last digits,
// 'atwonex' would become 'a2nex' but last digit should be 1 ('at1x')
fun cleanUp(line: String): String {
    if (line.isEmpty()) return ""

    if (line.startsWith("one")) {
        return "1" + cleanUp(line.substring("one".length))
    } else if (line.startsWith("two")) {
        return "2" + cleanUp(line.substring("two".length))
    } else if (line.startsWith("three")) {
        return "3" + cleanUp(line.substring("three".length))
    } else if (line.startsWith("four")) {
        return "4" + cleanUp(line.substring("four".length))
    } else if (line.startsWith("five")) {
        return "5" + cleanUp(line.substring("five".length))
    } else if (line.startsWith("six")) {
        return "6" + cleanUp(line.substring("six".length))
    } else if (line.startsWith("seven")) {
        return "7" + cleanUp(line.substring("seven".length))
    } else if (line.startsWith("eight")) {
        return "8" + cleanUp(line.substring("eight".length))
    } else if (line.startsWith("nine")) {
        return "9" + cleanUp(line.substring("nine".length))
    }

    return line.first() + cleanUp(line.substring(1))
}