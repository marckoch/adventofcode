package year2020.day14

import java.math.BigInteger

fun main() {
    part1(SAMPLE)
    part1(INPUT)

    part2(SAMPLE2)
    part2(INPUT)
}

val MASK_PATTERN = """mask = ([01X]{36})""".toRegex()
val MEM_PATTERN = """mem\[(\d*)\] = (\d*)""".toRegex()

fun part1(input: String) {
    var mask = ""
    val mem = HashMap<String, BigInteger>()

    input.lines().forEach { line ->
        if (MASK_PATTERN.matches(line)) {
            mask = MASK_PATTERN.find(line)!!.groupValues[1]
        } else if (MEM_PATTERN.matches(line)) {
            val (address, value) = MEM_PATTERN.find(line)!!.destructured
            mem[address] = maskNumber(mask, BigInteger(value))
        } else {
            throw IllegalArgumentException("computer says no to this line: $line")
        }
    }
    val sum = mem.values.sumOf { it }
    println("part1: $sum")
}

fun part2(input: String) {
    var mask = ""
    val mem = HashMap<String, BigInteger>()

    input.lines().forEach { line ->
        if (MASK_PATTERN.matches(line)) {
            mask = MASK_PATTERN.find(line)!!.groupValues[1]
        } else if (MEM_PATTERN.matches(line)) {
            val (address, value) = MEM_PATTERN.find(line)!!.destructured
            maskAddresses(mask, BigInteger(address)).forEach {
                mem[it] = BigInteger(value)
            }
        } else {
            throw IllegalArgumentException("computer says no to this line: $line")
        }
    }
    val sum = mem.values.sumOf { it }
    println("part2: $sum")
}

fun maskNumber(mask: String, n: BigInteger): BigInteger {
    val nAsBinary = n.toString(2).padStart(36, '0')

    return nAsBinary.zip(mask)
        .map { (nChar, maskChar) ->
            if (maskChar == '0' || maskChar == '1') {
                maskChar
            } else
                nChar
        }.joinToString("")
        .let { BigInteger(it, 2) }
}

fun maskAddresses(mask: String, address: BigInteger): Set<String> {
    val addressAsBinary = address.toString(2).padStart(36, '0')

    val maskedAddress = addressAsBinary.zip(mask)
        .map { (nChar, maskChar) ->
            when (maskChar) {
                '0' -> nChar
                else -> maskChar // '1' and 'X' remain unchanged
            }
        }.joinToString("")

    return replaceFirstX(maskedAddress)
}

fun replaceFirstX(address: String): Set<String> {
    if (!address.contains('X'))
        return setOf(address)

    return setOf(
        address.replaceFirst('X', '1'),
        address.replaceFirst('X', '0')
    ).flatMap { replaceFirstX(it) }.toSet()
}