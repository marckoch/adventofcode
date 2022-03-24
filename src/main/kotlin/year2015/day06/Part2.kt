package year2015.day06

fun main() {
    val lights = Array(1000) { IntArray(1000) { 0 } }

    INPUT.lines().forEach { line ->
        val (command, aX, aY, bX, bY) = "(turn on|turn off|toggle) (\\d*),(\\d*) through (\\d*),(\\d*)".toRegex()
            .find(line)!!.destructured

        for (x in aX.toInt()..bX.toInt()) {
            for (y in aY.toInt()..bY.toInt()) {
                when (command) {
                    "turn on" -> lights[x][y] += 1
                    "turn off" -> {
                        if (lights[x][y] > 0) lights[x][y] -= 1
                    }
                    "toggle" -> lights[x][y] += 2
                }
            }
        }
    }

    lights.sumOf { ints: IntArray -> ints.sum() }
        .let { print(it) }
}
