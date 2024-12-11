package year2024.day11


fun main() {
    AOC2024D11(SAMPLE).solve(1).let { println(it) } // 3
    AOC2024D11(SAMPLE).solve(2).let { println(it) } // 4
    AOC2024D11(SAMPLE).solve(3).let { println(it) } // 5
    AOC2024D11(SAMPLE).solve(4).let { println(it) } // 9
    AOC2024D11(SAMPLE).solve(5).let { println(it) } // 3
    AOC2024D11(SAMPLE).solve(6).let { println(it) } // 22
    AOC2024D11(SAMPLE).solve(25).let { println(it) } // 55312
    AOC2024D11(INPUT).solve(25).let { println(it) } // 231278

    AOC2024D11(INPUT).solve(75).let { println(it) } // 274229228071551
}

fun Long.split(): List<Long> {
    val s = this.toString()
    val middle = s.length / 2
    return listOf(
        s.substring(0, middle).toLong(),
        s.substring(middle).toLong()
    )
}

fun Long.hasEvenDigits() = this.toString().length % 2 == 0

class AOC2024D11(val input: String) {
    private val cache = mutableMapOf<String, Long>()

    fun solve(times: Int): Long {
        val stones = input.split(" ").map { it.toLong() }
        return stones.sumOf { blink(it, times) }
    }

    private fun blink(l: Long, times: Int): Long {
        if (times == 0) return 1L
        val key = "$l-$times"
        return cache.getOrPut(key) {
            process(l).sumOf { blink(it, times - 1) }
        }
    }

    private fun process(l: Long): List<Long> {
        return if (l == 0L) {
            listOf(1L)
        } else if (l.hasEvenDigits()) {
            l.split()
        } else {
            listOf(l * 2024)
        }
    }

    // good for part 1, but fails part 2
    // problem: this calculates the list in each blink
    // better: take first number and "blink" it n times, cache intermediate results,
    // then "blink" second number and profit from the filled cache
//    fun solve_BAD(blinks: Int): Int {
//        var stones = input.split(" ").map { it.toLong() }.toMutableList()
//        repeat(blinks) {
//            stones = blinkOnce(stones)
//        }
//        println(stones.toList())
//        return stones.size
//    }
//
//    private fun blinkOnce(stones: MutableList<Long>): MutableList<Long> {
//        var index = 0
//        while (index < stones.size) {
//            if (stones[index] == 0L) {
//                stones[index] = 1
//            } else if (stones[index].toString().length % 2 == 0) {
//                val n = stones[index].toString()
//                val left = n.substring(0, n.length / 2)
//                val right = n.substring(n.length / 2)
//                stones[index] = left.toLong()
//                stones.add(index + 1, right.toLong())
//                index++
//            } else {
//                stones[index] = stones[index] * 2024
//            }
//            index++
//        }
//        return stones
//    }
}
