package year2015.day08

fun main() {
    println(TEST_INPUT.lines()
        .map { line -> blowUp(line) }
        .also { println(it) }
        .sumOf { pair -> pair.first - pair.second })

    println(INPUT.lines()
        .map { line -> blowUp(line) }
        .also { println(it) }
        .sumOf { pair -> pair.first - pair.second })
}

fun blowUp(s: String): Pair<Int, Int> {
    val newString = s
        .replace(("(\\\\)").toRegex(), "__") // single \
        .replace(("(\")").toRegex(), "__") // single "

//    println("$s -> $newString")

    return Pair(newString.length + 2, s.length) // + 2 for " in front and back
}