package year2022.day25

fun main() {
    part1(SAMPLE)
    part1(INPUT)
}

fun part1(input: String) {
    val numbers = input.lines().map { it.toDec() }
    //numbers.let { println(it) }

    val sum = numbers.sum()
    println("dec: $sum")
    println("snafu: ${sum.toSnafu()}")
}

fun String.toDec(): Long {
    return this.fold(0L) { acc, c -> acc * 5 + decodeSnafuDigit(c) }
}

fun decodeSnafuDigit(c: Char): Int {
    return when (c) {
        '1' -> 1
        '2' -> 2
        '0' -> 0
        '-' -> -1
        '=' -> -2
        else -> throw IllegalArgumentException("computer says no to this snafu char: $c")
    }
}

fun Long.toSnafu(): String {
    val result = mutableListOf<Char>()
    var temp = this
    while (temp > 0L) {
        when (temp.mod(5L)) {
            0L -> result.add('0')
            1L -> result.add('1')
            2L -> result.add('2')
            3L -> {
                result.add('=')
                temp += 5L
            }
            4L -> {
                result.add('-')
                temp += 5L
            }
        }
        temp /= 5L
    }
    return result.reversed().joinToString("")
}

