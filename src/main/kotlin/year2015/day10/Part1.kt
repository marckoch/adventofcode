package year2015.day10

fun main() {
    var s = "1321131112"
    repeat(40) {
        s = lookAndSay(s)
    }
    println(s.length)
}