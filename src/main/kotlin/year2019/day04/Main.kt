package year2019.day04

fun main() {
    part1(235741, 706948) // 1178
    part2(235741, 706948) // 763
}

fun part1(start: Int, end: Int) {
    val passwords = (start..end).filter { isValidPassword1(it) }
    println(passwords.size)
}

fun part2(start: Int, end: Int) {
    val passwords = (start..end).filter { isValidPassword2(it) }
    println(passwords.size)
}

fun isValidPassword1(password: Int): Boolean {
    return neverDecreases(password) && hasDouble(password)
}

fun isValidPassword2(password: Int): Boolean {
    return neverDecreases(password) && hasDouble(password) && hasOneUniqueDouble(password)
}

fun neverDecreases(password: Int): Boolean {
    password.toString().windowed(2).forEach {
        if (it[0] > it[1]) {
            return false
        }
    }
    return true
}

fun hasDouble(password: Int): Boolean {
    password.toString().windowed(2).forEach {
        if (it[0] == it[1]) {
            return true
        }
    }
    return false
}

// check the frequency of each digit, if there is a group of 2, then it is a valid password
// note: 113533 would have freq 3 for '3', but is not possible because it is decreasing 5->3
// and eliminated by other rules
fun hasOneUniqueDouble(password: Int): Boolean {
    password.toString().groupBy { it }.forEach {
        if (it.value.size == 2) {
            return true
        }
    }
    return false
}