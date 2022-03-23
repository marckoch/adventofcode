package year2015.day01

fun main() {
    var total = 0

    for ((i, c) in input.withIndex()) {
        total += mapChar(c)
        if (total == -1) {
            println(i+1)
            return
        }
    }
}
