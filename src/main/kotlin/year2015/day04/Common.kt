package year2015.day04

import java.math.BigInteger
import java.security.MessageDigest

const val INPUT = "yzbqklnj"

fun md5(input:String): String {
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
}