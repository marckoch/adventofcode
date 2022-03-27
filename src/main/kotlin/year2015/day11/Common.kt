package year2015.day11

fun findNextPassword(s: String): String {
    var next = s
    var valid = false
    while (!valid) {
        next = inc(next)
        valid = hasThreeIncreasingChars(next) && hasValidLetters(next) && hasTwoPairs(next)
    }
    return next
}

// increment the given string
fun inc(s: String): String {
    if (s.isEmpty()) return "a"
    return if (s.last() == 'z') {
        // edge case: last char is z, that means last char becomes 'a' and rest is incremented! (wrap around)
        inc(s.substring(0, s.lastIndex)) + "a"
    } else {
        // normal case: just inc last char
        s.substring(0, s.lastIndex) + s.last().inc()
    }
}

fun hasThreeIncreasingChars(s: String): Boolean {
    return s.windowed(3, 1)
        .any { it[2] - it[1] == 1 && it[1] - it[0] == 1 }
}

fun hasValidLetters(s: String): Boolean {
    return !s.contains('i') && !s.contains('o') && !s.contains('l')
}

fun hasTwoPairs(s: String): Boolean {
    return """([a-z])\1[a-z]*([a-z])\2""".toRegex().containsMatchIn(s)
}