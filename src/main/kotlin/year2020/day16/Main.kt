package year2020.day16

fun main() {
    part1(SAMPLE)
    part1(INPUT)

//    part2(SAMPLE2)
    part2(INPUT)
}

fun part1(input: String) {
    val tickets = getInput(input)

    val fields = tickets[0].map { parseField(it) }
    val nearbyTickets = tickets[2].drop(1).map { parseTicket(it) }

    nearbyTickets.flatMap { findInvalidNumbersInTicket(it, fields) }.sum().let { println("part1: $it") }
}

fun part2(input: String) {
    val tickets = getInput(input)

    val fields = tickets[0].map { parseField(it) }
    val myTicket = tickets[1].drop(1).first().let { parseTicket(it) }
    val nearbyTickets = tickets[2].drop(1).map { parseTicket(it) }

    val validTickets = nearbyTickets.filter { isValidTicket(it, fields) }.toMutableList()
    validTickets.add(myTicket)

    val matchingFields = findFieldNumber(validTickets, fields, HashMap())

    val indicesOfDepartureFields =
        matchingFields.filterKeys { it.name.startsWith("departure") }.values
    indicesOfDepartureFields.let { println("indices in my ticket: $it") }

    val numbersInMyTicket = indicesOfDepartureFields.map { myTicket[it] }
    numbersInMyTicket.let { println("numbers in my ticket: $it") }
    numbersInMyTicket.fold(1L) { acc, l -> acc * l }.let { println("part2: $it") }
}

fun findFieldNumber(
    validTickets: List<List<Long>>,
    fieldCandidates: List<Field>,
    matchingFields: HashMap<Field, Int> // Field to Index in list of tickets numbers
): HashMap<Field, Int> {
    if (fieldCandidates.isEmpty()) return matchingFields

    val matchingFieldPotentials = validTickets.first().indices.map { i ->
        val ithNumbersOfValidTickets = validTickets.map { it[i] }
        val potentialFields =
            fieldCandidates.filter { field -> ithNumbersOfValidTickets.all { n -> n in field.range1 || n in field.range2 } }
        potentialFields
    }

    // find field that matches exactly once to a number column
    val matchingField = matchingFieldPotentials.withIndex().first { it.value.size == 1 }

    val newFieldCandidates = fieldCandidates.toMutableList()
    newFieldCandidates.remove(matchingField.value.first())
    matchingFields[matchingField.value.first()] = matchingField.index

    return findFieldNumber(validTickets, newFieldCandidates, matchingFields)
}

fun parseTicket(s: String): List<Long> {
    return s.split(",").map { it.toLong() }
}

val FIELD_PATTERN = """(.*): (\d+)-(\d+) or (\d+)-(\d+)""".toRegex()

data class Field(val name: String, val range1: IntRange, val range2: IntRange)

fun isValidTicket(ticket: List<Long>, fields: List<Field>): Boolean {
    return ticket.all { number -> fields.any { f -> number in f.range1 || number in f.range2 } }
}

fun findInvalidNumbersInTicket(ticket: List<Long>, fields: List<Field>): List<Long> {
    return ticket.filterNot { number -> fields.any { f -> number in f.range1 || number in f.range2 } }
}

fun parseField(line: String): Field {
    val (name, from1, to1, from2, to2) = FIELD_PATTERN.find(line)!!.destructured
    return Field(name, (from1.toInt()..to1.toInt()), (from2.toInt()..to2.toInt()))
}

fun getInput(input: String): List<List<String>> {
    return input.lines().fold(mutableListOf<MutableList<String>>()) { list, line ->
        if (line.isEmpty()) {
            list.add(mutableListOf())
        } else {
            if (list.isEmpty()) list.add(mutableListOf())
            list.last().add(line.trim())
        }
        list
    }
}