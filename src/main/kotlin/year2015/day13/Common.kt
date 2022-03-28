package year2015.day13

const val TEST_INPUT = """Alice would gain 54 happiness units by sitting next to Bob.
Alice would lose 79 happiness units by sitting next to Carol.
Alice would lose 2 happiness units by sitting next to David.
Bob would gain 83 happiness units by sitting next to Alice.
Bob would lose 7 happiness units by sitting next to Carol.
Bob would lose 63 happiness units by sitting next to David.
Carol would lose 62 happiness units by sitting next to Alice.
Carol would gain 60 happiness units by sitting next to Bob.
Carol would gain 55 happiness units by sitting next to David.
David would gain 46 happiness units by sitting next to Alice.
David would lose 7 happiness units by sitting next to Bob.
David would gain 41 happiness units by sitting next to Carol."""

const val INPUT = """Alice would gain 54 happiness units by sitting next to Bob.
Alice would lose 81 happiness units by sitting next to Carol.
Alice would lose 42 happiness units by sitting next to David.
Alice would gain 89 happiness units by sitting next to Eric.
Alice would lose 89 happiness units by sitting next to Frank.
Alice would gain 97 happiness units by sitting next to George.
Alice would lose 94 happiness units by sitting next to Mallory.
Bob would gain 3 happiness units by sitting next to Alice.
Bob would lose 70 happiness units by sitting next to Carol.
Bob would lose 31 happiness units by sitting next to David.
Bob would gain 72 happiness units by sitting next to Eric.
Bob would lose 25 happiness units by sitting next to Frank.
Bob would lose 95 happiness units by sitting next to George.
Bob would gain 11 happiness units by sitting next to Mallory.
Carol would lose 83 happiness units by sitting next to Alice.
Carol would gain 8 happiness units by sitting next to Bob.
Carol would gain 35 happiness units by sitting next to David.
Carol would gain 10 happiness units by sitting next to Eric.
Carol would gain 61 happiness units by sitting next to Frank.
Carol would gain 10 happiness units by sitting next to George.
Carol would gain 29 happiness units by sitting next to Mallory.
David would gain 67 happiness units by sitting next to Alice.
David would gain 25 happiness units by sitting next to Bob.
David would gain 48 happiness units by sitting next to Carol.
David would lose 65 happiness units by sitting next to Eric.
David would gain 8 happiness units by sitting next to Frank.
David would gain 84 happiness units by sitting next to George.
David would gain 9 happiness units by sitting next to Mallory.
Eric would lose 51 happiness units by sitting next to Alice.
Eric would lose 39 happiness units by sitting next to Bob.
Eric would gain 84 happiness units by sitting next to Carol.
Eric would lose 98 happiness units by sitting next to David.
Eric would lose 20 happiness units by sitting next to Frank.
Eric would lose 6 happiness units by sitting next to George.
Eric would gain 60 happiness units by sitting next to Mallory.
Frank would gain 51 happiness units by sitting next to Alice.
Frank would gain 79 happiness units by sitting next to Bob.
Frank would gain 88 happiness units by sitting next to Carol.
Frank would gain 33 happiness units by sitting next to David.
Frank would gain 43 happiness units by sitting next to Eric.
Frank would gain 77 happiness units by sitting next to George.
Frank would lose 3 happiness units by sitting next to Mallory.
George would lose 14 happiness units by sitting next to Alice.
George would lose 12 happiness units by sitting next to Bob.
George would lose 52 happiness units by sitting next to Carol.
George would gain 14 happiness units by sitting next to David.
George would lose 62 happiness units by sitting next to Eric.
George would lose 18 happiness units by sitting next to Frank.
George would lose 17 happiness units by sitting next to Mallory.
Mallory would lose 36 happiness units by sitting next to Alice.
Mallory would gain 76 happiness units by sitting next to Bob.
Mallory would lose 34 happiness units by sitting next to Carol.
Mallory would gain 37 happiness units by sitting next to David.
Mallory would gain 40 happiness units by sitting next to Eric.
Mallory would gain 18 happiness units by sitting next to Frank.
Mallory would gain 7 happiness units by sitting next to George."""

val LINE_PATTERN = """(\w*) would (gain|lose) (\d*) happiness units by sitting next to (\w*).""".toRegex()

// permute all seating arrangements and calculate total happiness for each arrangement,
// take the one with maximal happiness
fun getMaximalHappiness(lines: List<String>, dim: Int) {
    val matrix = convertToMatrix(lines, dim)

    permute((0 until dim).toList())
        .maxOf { ints ->
            val happiness = ints.windowed(2, 1)
                .sumOf {
                    // count both happiness values! A to B and B to A !
                    matrix[it[0]][it[1]] + matrix[it[1]][it[0]]
                }
            // add wrap around happiness! e.g. first and last of list!
            happiness + matrix[ints.first()][ints.last()] + matrix[ints.last()][ints.first()]
        }.let { println(it) }
}

// convert lines of format "<from> would (gain|lose) <happiness> happiness units by sitting next to <to>." to matrix form
fun convertToMatrix(lines: List<String>, dim: Int): Array<IntArray> {
    val guests = ArrayList<String>()
    val matrix = Array(dim) { IntArray(dim) }

    lines.forEach { line ->
        val (from, gain_lose, happiness, to) = LINE_PATTERN.find(line)!!.destructured

        val indexOfFrom = indexOfOrPut(guests, from)
        val indexOfTo = indexOfOrPut(guests, to)

        matrix[indexOfFrom][indexOfTo] = if (gain_lose == "lose") -happiness.toInt() else happiness.toInt()
    }

    printMatrix(matrix, "\t")
    return matrix
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