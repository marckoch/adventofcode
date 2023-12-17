package year2023.day15

fun main() {
    println(part1("HASH")) // 52
    println(part1(SAMPLE)) // 1320
    println(part1(INPUT)) // 1320

    println(part2(SAMPLE)) // 145
    println(part2(INPUT)) // 261505
}

fun part1(input: String): Int {
    val lenses = input.split(",").map { it.trim() }
    return lenses.sumOf {
        hash(it)
    }
}

fun hash(string: String): Int {
    return string.fold(0) { acc: Int, c: Char -> ((c.code + acc) * 17).mod(256) }
}

fun part2(input: String): Long {
    val lenses = input.split(",").map { it.trim() }
    val boxes = fillBoxes(lenses)
    return focusingPower(boxes)
}

fun fillBoxes(lenses: List<String>): HashMap<Int, MutableList<Pair<String, Int>>> {
    val map = HashMap<Int, MutableList<Pair<String, Int>>>()

    lenses.forEach { lens ->
        if (lens.contains("=")) {
            val (label, value) = lens.split("=")
            val hash = hash(label)
            if (map[hash] == null) {
                map[hash] = mutableListOf()
            }
            if (map[hash] != null) {
                val index = map[hash]!!.indexOfFirst { it.first == label }
                if (index == -1) {
                    map[hash]!!.add(Pair(label, value.toInt()))
                } else {
                    map[hash]!![index] = Pair(label, value.toInt())
                }
            }
        } else if (lens.endsWith("-")) {
            val label = lens.dropLast(1)
            val hash = hash(label)
            map[hash]?.removeIf { it.first == label }
            if (map[hash]?.isEmpty() == true) {
                map.remove(hash)
            }
        } else {
            throw IllegalArgumentException("Invalid lens: $lens")
        }
    }
    return map
}

fun focusingPower(boxes: HashMap<Int, MutableList<Pair<String, Int>>>): Long {
    return boxes.entries.sumOf { (key, value) ->
        (key+1) * focusingPowerOfLenses(value)
    }
}

fun focusingPowerOfLenses(lenses: List<Pair<String, Int>>): Long {
    return lenses.withIndex().fold(0) { acc, (index, lens) ->
        acc + (index + 1) * lens.second
    }
}