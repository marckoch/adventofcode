package year2015.day08

fun main() {
    println(TEST_INPUT.lines()
        .map { line -> count(line) }
        .also { println(it) }
        .sumOf { pair -> pair.first - pair.second })

    println(INPUT.lines()
        .map { line -> count(line) }
        .also { println(it) }
        .sumOf { pair -> pair.first - pair.second })
}

fun count(s: String): Pair<Int, Int> {
    val newString = s
        .replace(("(\\\\x[0-9a-fA-F][0-9a-fA-F])").toRegex(), "_") // hex
        .replace(("(\\\\\\\\)").toRegex(), "_") // \\
        .replace(("(\\\\\")").toRegex(), "_")  // \"
        .replace(("(\\\")").toRegex(), "") // single "

//    println("$s -> $newString")

    return Pair(s.length, newString.length)
}