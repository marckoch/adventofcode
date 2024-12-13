package year2024.day13

fun main() {
    AOC2024D13(SAMPLE).solve().let { println(it) } // 480
    AOC2024D13(INPUT).solve().let { println(it) } // 39748

    AOC2024D13(SAMPLE).solve(10000000000000L).let { println(it) } // 875318608908
    AOC2024D13(INPUT).solve(10000000000000L).let { println(it) } // 74478585072604
}

class AOC2024D13(val input: String) {

    private val buttonRegExp = """Button [AB]: X\+(\d*), Y\+(\d*)""".toRegex()
    private val prizeRegExp = """Prize: X=(\d*), Y=(\d*)""".toRegex()

    fun solve(prizeOffset: Long = 0L): Long {
        val chunks = readChunks(input)
        return chunks.sumOf { chunk ->
            val (ax, ay) = extractLongs(buttonRegExp, chunk[0])
            val (bx, by) = extractLongs(buttonRegExp, chunk[1])
            val (X, Y) = extractLongs(prizeRegExp, chunk[2])
            cost(ax, ay, bx, by,
                prizeOffset + X, prizeOffset + Y)
        }
    }

    fun extractLongs(reg: Regex, s: String): Pair<Long, Long> {
        val (x, y) = reg.find(s)!!.destructured
        return x.toLong() to y.toLong()
    }

    // ax * a + bx * b = X
    // ay * a + by * b = Y
    // solve for a and b:
    // a = (X * by - Y * bx) / (ax * by - ay * bx)
    // b = (X * ay - Y * ax) / (bx * ay - by * ax)
    private fun cost(ax: Long, ay: Long, bx: Long, by: Long, X: Long, Y: Long): Long {
        val bTop = X * ay - Y * ax
        val bBottom = bx * ay - by * ax
        // we are only interested in integer solutions
        // (you can not press the button 'B' only half or 0.43 times or so)
        if (bTop % bBottom != 0L) return 0L

        val aTop = X * by - Y * bx
        val aBottom = ax * by - bx * ay
        if (aTop % aBottom != 0L) return 0L

        return 3L * aTop / aBottom + bTop / bBottom
    }

    private fun readChunks(input: String): List<List<String>> {
        return input.lines().fold(mutableListOf<ArrayList<String>>()) { list, line ->
            if (line.isEmpty()) {
                list.add(ArrayList())
            } else {
                if (list.isEmpty()) {
                    list.add(ArrayList())
                }
                list.last().add(line)
            }
            list
        }
    }
}
