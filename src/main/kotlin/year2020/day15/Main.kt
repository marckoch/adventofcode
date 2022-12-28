package year2020.day15

fun main() {
    part1(listOf(0, 3, 6), 2020)
    part2(listOf(0, 3, 6), 30000000)

    part1(listOf(1, 12, 0, 20, 8, 16), 2020)
    part2(listOf(1, 12, 0, 20, 8, 16), 30000000)

//    part2(listOf(0, 3, 6), 30000000)

//    part1(listOf(0, 3, 6), 2020)
//    part2(listOf(0, 3, 6), 2020)


//    part2(listOf(1,12,0,20,8,16), 2020)

//    part1(listOf(1, 3, 2), 2020)
//    part2(listOf(1, 3, 2), 2020)

//    part1(listOf(2, 1, 3), 2020)
//    part1(listOf(1, 2, 3), 2020)
//    part1(listOf(2, 3, 1), 2020)
//    part1(listOf(3, 2, 1), 2020)
//    part1(listOf(3, 1, 2), 2020)
}

fun part1(numbers: List<Int>, turn: Int) {
    GameIterator(numbers).asSequence().drop(turn - 1).take(1).toList().let { println("part1: ${it.first()}") }
}

fun part2(numbers: List<Int>, turn: Int) {
    val m = HashMap<Int, Int>() // number to lastSeen
    numbers.withIndex().forEach { (index, n) -> m[n] = index + 1 }

    var lastNumber = numbers.last()

    for (i in numbers.size ..turn) {
        val nextNumber = if (m.containsKey(lastNumber)) {
            i - m[lastNumber]!!
        } else {
            0
        }

        m[lastNumber] = i
        lastNumber = nextNumber
    }
    m.filter { it.value == turn }.let { println("part2: ${it.keys.first()}") }
}

class GameIterator(private val seed: List<Int>) : Iterator<Int> {
    val numbers = mutableListOf<Int>()

    override fun hasNext() = true

    override fun next(): Int {
        val next = if (numbers.size < seed.size) {
            // first we use the seed
            seed[numbers.size]
        } else {
            // then we produce new numbers by the rules
            val last = numbers.last()
            val indexLastSeen = numbers.dropLast(1).lastIndexOf(last)
            val turnLastSeen = indexLastSeen + 1
            if (turnLastSeen == 0) {
                0
            } else {
                numbers.size - turnLastSeen
            }
        }
        numbers.add(next)
        return next
    }
}