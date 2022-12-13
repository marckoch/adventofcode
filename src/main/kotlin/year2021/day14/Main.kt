package year2021.day14

fun main() {
//    bruteForce(SAMPLE, 10, "NNCB")
//    bruteForce(INPUT, 10, "SVCHKVFKCSHVFNBKKPOC")

//    bruteForce(SAMPLE, 40, "NNCB")
//    bruteForce(INPUT, 40, "SVCHKVFKCSHVFNBKKPOC") // will not finish

    println("part1")
    workWithMatrix(SAMPLE, 10, "NNCB")
    workWithMatrix(INPUT, 10, "SVCHKVFKCSHVFNBKKPOC")

    println("part2")
    workWithMatrix(SAMPLE, 40, "NNCB")
    workWithMatrix(INPUT, 40, "SVCHKVFKCSHVFNBKKPOC")
}

// basic idea: the string itself is irrelevant, only the char counts are important
// we encode the pair of chars in a matrix and update the matrix in each step
// according to the transformation rules
fun workWithMatrix(input: String, steps: Int, start: String) {
    val rules = parse(input)

    // the differentChars are sorted and tell us the index of row/col in the matrix
    // e.g. in sample we have 4 different chars: B,C,H,N
    // so B is 0, C is 1, ..., N is 3
    // e.g. row with index 0 in matrix is pairs that start with B
    // col with index 2 in matrix is all pairs that end with H
    val differentChars = rules.map { it.key.toSet() + it.value.toSet() }.flatten().toSet().sorted().toList()
    val diffCharsCount = differentChars.size

    // our matrix: the first char of a pair is encoded to row, last char to col
    var m = Array(diffCharsCount) { LongArray(diffCharsCount) { 0 } }
    start.windowed(2, 1).map { s ->
        val row = differentChars.indexOf(s.first())
        val col = differentChars.indexOf(s.last())
        m[row][col] += 1L
    }

    repeat(steps) {
        m = step(m, rules, differentChars)
    }

    // the row sums count the characters in the beginning of a pair,
    // thus the last char of start is missing!
    // here we correct this
    val rowSums = m.map { it.sum() }.toMutableList()
    rowSums[differentChars.indexOf(start.last())]++
    val rowsSorted = rowSums.sorted()
    println("$start transformed $steps times: " + (rowsSorted.max() - rowsSorted.min()))
}

fun step(m: Array<LongArray>, rules: Map<String, String>, differentChars: List<Char>): Array<LongArray> {
    val newMatrix = Array(m.size) { LongArray(m[0].size) { 0 } }

    rules.map { entry ->
//        println(entry)
        val pair = entry.key
        val row = pair.first()
        val col = pair.last()
        val newChar = entry.value.first()

        val indexOfRow = differentChars.indexOf(row)
        val indexOfCol = differentChars.indexOf(col)
        val indexOfNewChar = differentChars.indexOf(newChar)

        // the value r/c in the old matrix will create two new elements in the
        // new matrix at r/n and n/c
        val mValue = m[indexOfRow][indexOfCol]
        if (mValue > 0) {
            newMatrix[indexOfRow][indexOfNewChar] += mValue
            newMatrix[indexOfNewChar][indexOfCol] += mValue
        }
    }
    return newMatrix
}

// works for part1 but not for part2
fun bruteForce(input: String, steps: Int, start: String) {
    val rules = parse(input)

    var s = start

    repeat(steps) {
        s = grow(s, rules)
    }
    val freqs = s.groupingBy { it }.eachCount()
    println(freqs)
    val f = freqs.map { it.value }.sorted().reversed()
    println(f.first() - f.last())
}

fun grow(s: String, rules: Map<String, String>): String {
    return s.windowed(2, 1)
        .withIndex()
        .map {
            if (rules.contains(it.value)) {
                if (it.index == 0) {
                    it.value[0] + rules[it.value]!! + it.value[1]
                } else {
                    rules[it.value]!! + it.value[1]
                }
            } else
                it
        }.joinToString("")
}

fun parse(input: String): Map<String, String> {
    return input.lines().associate { s ->
        val parts = s.split(" ")
        Pair(parts[0], parts[2])
    }
}