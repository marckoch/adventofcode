package year2020.day08

fun main() {
    part1(SAMPLE)
    part1(INPUT)

    part2(SAMPLE)
    part2(INPUT)
}

val LINE = """(\w*) ([+-]?\d*)""".toRegex()

fun part1(input: String) {
    val operations = input.lines().map { parseLine(it) }

    run(operations)
        .let { println("part1: $it") }
}

fun part2(input: String) {
    val operations = input.lines().map { parseLine(it) }

    mutateOperations(operations)
        .map { run(it) }
        .filterNot { it.endlessLoop }
        .forEach { println("part2: $it") }
}

fun mutateOperations(operations: List<Operation>): List<List<Operation>> {
    return operations.withIndex().fold(mutableListOf()) {
        acc, (index, value) ->
        when (value.command) {
            "nop" -> {
                acc.add(operations.toMutableList().replaceAt(index, value.copy(command = "jmp")))
                acc
            }
            "jmp" -> {
                acc.add(operations.toMutableList().replaceAt(index, value.copy(command = "nop")))
                acc
            }
            else -> {
                acc
            }
        }
    }
}

fun <T> MutableList<T>.replaceAt(i: Int, newVal: T): List<T> {
    this.removeAt(i)
    this.add(i, newVal)
    return this
}

fun run(operations: List<Operation>): Result {
    var acc = 0
    val visited = BooleanArray(operations.size)
    var currentOperation = 0

    while (currentOperation in operations.indices) {
        if (visited[currentOperation]) return Result(true, acc)
        visited[currentOperation] = true

        val (command, x) = operations[currentOperation]
        when (command) {
            "nop" -> currentOperation++
            "acc" -> {
                acc += x
                currentOperation++
            }
            "jmp" -> currentOperation += x
        }
    }
    return Result(false, acc)
}

fun parseLine(line: String): Operation {
    return LINE.find(line)!!.destructured
        .let { (command, x) -> Operation(command, x.toInt()) }
}

data class Operation(val command: String, val x: Int)

data class Result(val endlessLoop: Boolean, val result: Int)