package util.parser

fun String.asInts(delimiter: String = " "): List<Int> {
    return this.split(delimiter).map { it.toInt() }
}

fun readChunks(input: String): List<List<String>> {
    return input.lines().fold(mutableListOf(mutableListOf<String>())) { list, line ->
        if (line.isEmpty()) {
            list.add(ArrayList())
        } else {
            list.last().add(line)
        }
        list
    }
}
