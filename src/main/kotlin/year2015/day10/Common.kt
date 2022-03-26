package year2015.day10

fun lookAndSay(s: String): String {
    val result = StringBuilder()

    var last: Char = 0.toChar()
    var count = 0
    for (c in s.toCharArray()) {
        if (last == 0.toChar()) { // at the start init last with first character and set count to 1
            last = c
            count++
        } else if (c != last && last != 0.toChar()) { // new char, so finish up last batch
            result.append(count)
            result.append(last)
            last = c
            count = 1
        } else if (c == last) { // char does not change, so just inc count
            count++
        }
    }
    result.append(count)
    result.append(last)
//    println("$s -> $result")
    return result.toString()
}