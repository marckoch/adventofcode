package year2020.day02

val LINE_PATTERN = """(\d+)-(\d+) (\w): (\w*)""".toRegex()

fun main() {
    SAMPLE.lines().count { validPassword1(it) }.let { println(it) }
    INPUT.lines().count { validPassword1(it) }.let { println(it) }

    SAMPLE.lines().count { validPassword2(it) }.let { println(it) }
    INPUT.lines().count { validPassword2(it) }.let { println(it) }
}

fun validPassword1(line: String): Boolean {
    val (min, max, c, pw) = LINE_PATTERN.find(line)!!.destructured
    return pw.count { it == c.first() } in min.toInt()..max.toInt()
}

fun validPassword2(line: String): Boolean {
    val (i1, i2, c, pw) = LINE_PATTERN.find(line)!!.destructured
    return (pw[i1.toInt() - 1] == c.first()) xor (pw[i2.toInt() - 1] == c.first())
}