package year2015.day05

fun main() {
    println(
        INPUT.lines()
            .filter { isBetterNice(it) }.size
    )
}

fun isBetterNice(s: String): Boolean {
    s.windowed(2, 1)
        .withIndex()
        .firstOrNull { indexedValue -> s.substring(indexedValue.index + 2).contains(indexedValue.value) }
        ?: return false

    val indexOfRepeatingLetter = s.windowed(3,1).indexOfFirst { c -> c[0] == c[2] }
    if (indexOfRepeatingLetter < 0)
        return false

    return true
}