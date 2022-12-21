package year2020.day04

fun main() {
    part1(SAMPLE)
    part1(INPUT)

    part2(SAMPLE)
    part2(INPUT)
}

fun part1(input: String) {
    readPassports(input).count { p -> isValid1(p) }.let { println("part1: $it") }
}

fun part2(input: String) {
    readPassports(input).count { p -> isValid2(p) }.let { println("part2: $it") }
}

fun isValid1(passport: List<String>): Boolean {
    return passport.any { l -> l.contains("byr:") } &&
            passport.any { l -> l.contains("iyr:") } &&
            passport.any { l -> l.contains("eyr:") } &&
            passport.any { l -> l.contains("hgt:") } &&
            passport.any { l -> l.contains("hcl:") } &&
            passport.any { l -> l.contains("ecl:") } &&
            passport.any { l -> l.contains("pid:") }
}

fun isValid2(passport: List<String>): Boolean {
    return passport.any { l -> l.contains("byr:")  && isValidDate(l,"byr:", 1920..2002) } &&
            passport.any { l -> l.contains("iyr:") && isValidDate(l, "iyr:", 2010..2020) } &&
            passport.any { l -> l.contains("eyr:") && isValidDate(l, "eyr:",  2020..2030) } &&
            passport.any { l -> l.contains("hgt:") && isValidHeight(l, "hgt:") } &&
            passport.any { l -> l.contains("hcl:") && isValidHairColor(l, "hcl:") } &&
            passport.any { l -> l.contains("ecl:") && isValidEyeColor(l, "ecl:") } &&
            passport.any { l -> l.contains("pid:") && isValidPid(l, "pid:") }
}

fun isValidDate(l: String, key: String, range: IntRange): Boolean {
    return getValue(l, key).toInt() in range
}

fun isValidHairColor(l: String, key: String): Boolean {
    val c = getValue(l, key)
    return """#[0-9a-f]{6}""".toRegex().matches(c)
}

fun isValidPid(l: String, key: String): Boolean {
    val c = getValue(l, key)
    return """[0-9]{9}""".toRegex().matches(c)
}

fun isValidEyeColor(l: String, key: String): Boolean {
    val e = getValue(l, key)
    return e in listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")
}

fun isValidHeight(l: String, key: String): Boolean {
    val h = getValue(l, key)
    if (h.endsWith("cm")) {
        val n = h.substring(0, h.indexOf("cm")).toInt()
        return n in 150..193
    } else if (h.endsWith("in")) {
        val n = h.substring(0, h.indexOf("in")).toInt()
        return n in 59..76
    }
    return false
}

fun getValue(l:String, key: String): String {
    val from = l.indexOf(key) + key.length
    val to = l.indexOf(" ", from)
    return if (to < 0) {
        l.substring(from)
    } else {
        l.substring(from, to)
    }
}

fun readPassports(input: String): List<MutableList<String>> {
    return input.lines().fold(mutableListOf()) { acc, line ->
        if (line.isEmpty()) {
            acc.add(mutableListOf())
        } else {
            if (acc.isEmpty()) {
                acc.add(mutableListOf())
            }
            acc.last().add(line)
        }
        acc
    }
}