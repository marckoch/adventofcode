package util.hex


// https://github.com/KoxAlen/AdventOfCode2016
// src/main/kotlin/aoc/day14/Day14.kt
private val LOWER_HEX_CHARS = "0123456789abcdef".toCharArray()

fun ByteArray.toLowerHex(): String {
    val retval = StringBuilder(size*2)

    forEach {
        val octet = it.toInt()
        retval.append(LOWER_HEX_CHARS[octet ushr 4 and 0x0F])
        retval.append(LOWER_HEX_CHARS[octet and 0x0F])
    }

    return retval.toString()
}