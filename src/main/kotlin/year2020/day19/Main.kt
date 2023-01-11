package year2020.day19

fun main() {
    part1(SAMPLE)
    part1(INPUT)
}

fun part1(input: String) {
    val (rules, messages) = readLineChunks(input)
//    println(rules)
//    println(messages)

    val r = rules.map { parseRule(it) }
    val reg = genRegExp(r, 0).also { println(it) }

    messages.filter { it.matches(reg.toRegex()) }.let { println(it) }
}

fun genRegExp(rules: List<Rule>, rule: Int = 0): String {
    if (rules[rule].rhs.first().first().startsWith("\"")) {
        return rules[rule].rhs.first().first().replace("\"", "")
    }
    return "(" + rules[rule].rhs.joinToString("|") { ruleList ->
        ruleList.joinToString("") { r2 -> genRegExp(rules, r2.toInt()) }
    } + ")"
}

fun parseRule(rule: String): Rule {
    val (id, rhs) = rule.split(": ")
    val x = rhs.split("|").map { it.trim().split(" ") }
    return Rule(id, x)
}

class Rule(val id: String, val rhs: List<List<String>>)

fun readLineChunks(input: String): List<List<String>> {
    return input.lines().fold(mutableListOf<MutableList<String>>()) { list, line ->
        if (line.isEmpty()) {
            list.add(mutableListOf())
        } else {
            if (list.isEmpty()) {
                list.add(mutableListOf())
            }
            list.last().add(line.trim())
        }
        list
    }
}