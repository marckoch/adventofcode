package year2015.day09

const val TEST_INPUT = """London to Dublin = 464
London to Belfast = 518
Dublin to Belfast = 141"""

const val INPUT = """Tristram to AlphaCentauri = 34
Tristram to Snowdin = 100
Tristram to Tambi = 63
Tristram to Faerun = 108
Tristram to Norrath = 111
Tristram to Straylight = 89
Tristram to Arbre = 132
AlphaCentauri to Snowdin = 4
AlphaCentauri to Tambi = 79
AlphaCentauri to Faerun = 44
AlphaCentauri to Norrath = 147
AlphaCentauri to Straylight = 133
AlphaCentauri to Arbre = 74
Snowdin to Tambi = 105
Snowdin to Faerun = 95
Snowdin to Norrath = 48
Snowdin to Straylight = 88
Snowdin to Arbre = 7
Tambi to Faerun = 68
Tambi to Norrath = 134
Tambi to Straylight = 107
Tambi to Arbre = 40
Faerun to Norrath = 11
Faerun to Straylight = 66
Faerun to Arbre = 144
Norrath to Straylight = 115
Norrath to Arbre = 135
Straylight to Arbre = 127"""

val LINE_PATTERN = "(.*) to (.*) = (\\d*)".toRegex()

// convert lines of format "<from> to <to> = <distance>" to matrix form
fun convertToMatrix(lines: List<String>, dim: Int): Array<IntArray> {
    val cities = ArrayList<String>()
    val matrix = Array(dim) { IntArray(dim) }

    lines.forEach { line ->
        val (from, to, distance) = LINE_PATTERN.find(line)!!.destructured
        // println("$from, $to, $distance")

        val indexOfFrom = indexOfOrPut(cities, from)
        val indexOfTo = indexOfOrPut(cities, to)

        // println("  $indexOfFrom $indexOfTo $distance")
        matrix[indexOfFrom][indexOfTo] = distance.toInt()
        matrix[indexOfTo][indexOfFrom] = distance.toInt()
    }

    printMatrix(matrix, "\t")
    return matrix
}

fun printMatrix(matrix: Array<IntArray>, delimiter: String = "") {
    println("-------")
    for (row in matrix) {
        var del = ""
        for (i in row) {
            print(del + i)
            del = delimiter
        }
        println()
    }
}

// https://rosettacode.org/wiki/Permutations#Kotlin
fun <T> permute(input: List<T>): List<List<T>> {
    if (input.size == 1) return listOf(input)

    val perms = mutableListOf<List<T>>()
    val head = input[0]
    val tail = input.drop(1)
    for (perm in permute(tail)) {
        for (i in 0..perm.size) {
            val newPerm = perm.toMutableList()
            newPerm.add(i, head)
            perms.add(newPerm)
        }
    }
    return perms
}

// get index of 's' in 'list'.
// if 's' is not in 'list', insert 's' first at end of 'list'
fun indexOfOrPut(list: MutableList<String>, s: String): Int {
    if (!list.contains(s)) {
        list.add(s)
    }
    return list.indexOf(s)
}