package year2022.day20

fun main() {
    solve(SAMPLE, 1, 1)
    solve(INPUT, 1, 1)

    solve(SAMPLE, 10, 811589153L)
    solve(INPUT, 10, 811589153L)
}

fun solve(input: String, noOfMixes: Int, decryptionKey: Long) {
    val numbers = input.lines().map { it.toLong() * decryptionKey }.toList()
    //numbers.let { println("initial: $it") }

    // we do not move the numbers itself but their indices
    val mixIndices = (numbers.indices).toMutableList()

    repeat(noOfMixes) {
        for (i in numbers.indices) {
            move(mixIndices, i, numbers[i])
        }
    }

    val reorderedList = mixIndices.map { numbers[it] }
    //reorderedList.let { println("reordered: $it") }

    val indexOf0 = reorderedList.indexOf(0)

    val n1000 = reorderedList[(indexOf0 + 1000) % reorderedList.size]
    val n2000 = reorderedList[(indexOf0 + 2000) % reorderedList.size]
    val n3000 = reorderedList[(indexOf0 + 3000) % reorderedList.size]
    println("$n1000 $n2000 $n3000")

    println("part1: ${n1000 + n2000 + n3000}")
}

// in given list move element delta positions (with wrap around)
fun move(indexList: MutableList<Int>, element: Int, delta: Long) {
    val index = indexList.indexOf(element)
    indexList.removeAt(index)
    val newIndex = (index + indexList.size + (delta % indexList.size)) % indexList.size
    indexList.add(newIndex.toInt(), element)
}