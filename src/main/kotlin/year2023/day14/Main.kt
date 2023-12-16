package year2023.day14

fun main() {
    println(part1(SAMPLE)) // 136
    println(part1(INPUT)) // 106990
}

fun part1(input: String): Int {
    val field = input.lines().drop(1).map { it.toMutableList() }

    tiltNorth(field)

    return calculateLoad(field.map { it.joinToString("") })
}

fun tiltNorth(field: List<MutableList<Char>>) {
    // step through the field and find free fields
    for (row in field.indices) {
        for (col in field[row].indices) {
            if (field[row][col] == '.') {
                // from this free field, go column down (by increasing row index) and find rock to pull up to this field
                for (r in (row+1..field.lastIndex)) {
                    if (field[r][col] == '#') {
                        break
                    } else if (field[r][col] == '.') {
                        continue
                    } else if (field[r][col] == 'O') {
                        // pull rock up
                        field[r][col] = '.'
                        field[row][col] = 'O'
                        break
                    }
                }
            }
        }
    }
}

// instead of tilting west, south, east, we can rotate the field and tilt north again
fun cycle(field: List<MutableList<Char>>, loads: MutableList<Int>): List<MutableList<Char>> {
    var f = field
    repeat(4) {
        tiltNorth(f)
        f = rotateRight(f)
    }
    loads.add(calculateLoad(f.map { it.joinToString("") }))
    // println(calculateLoad(f.map { it.joinToString("") }))
    return f
}

fun part2(input: String) {
    val field = input.lines().drop(1).map { it.toMutableList() }
    printField(field)
    println(calculateLoad(field.map { it.joinToString("") }))

    val loads = mutableListOf<Int>()

    var newField = field
    repeat(100) {
        newField = cycle(newField, loads)
    }
}

fun calculateLoad(field: List<String>): Int {
    return field.withIndex().sumOf { (index, row)  ->
        row.count { it == 'O' } * (field.size - index)
    }
}

fun printField(field: List<List<Char>>) {
    println("---")
    field.forEach { println(it.joinToString(""))}
}

// rotate the field to the right
// 123    A741
// 456 -> B852
// 789    C963
// ABC
fun rotateRight(field: List<MutableList<Char>>): List<MutableList<Char>> {
    val newField = mutableListOf<MutableList<Char>>()
    for (row in field.indices) {
        val newRow = mutableListOf<Char>()
        for (col in field[row].indices) {
            newRow.add(field[field.lastIndex - col][row])
        }
        newField.add(newRow)
    }
    return newField
}