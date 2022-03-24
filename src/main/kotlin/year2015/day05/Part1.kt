package year2015.day05

fun main() {
    println(
        INPUT.lines()
            .filter { isNice(it) }.size
    )
}

fun isNice(s: String): Boolean {
    val numberOfVowels = s.map { c -> if ("aeiou".contains(c)) 1 else 0 }.sum()
    if (numberOfVowels < 3)
        return false

    val indexOfFirstDuplicate = s.windowed(2, 1).indexOfFirst { c -> c[0] == c[1] }
    if (indexOfFirstDuplicate < 0)
        return false

    if (s.contains("ab") || s.contains("cd") || s.contains("pq") || s.contains("xy"))
        return false

    return true
}