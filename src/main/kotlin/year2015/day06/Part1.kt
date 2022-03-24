package year2015.day06


fun main() {
    val lights = Array(1000) { BooleanArray(1000) { false } }

    INPUT.lines().forEach { line ->
        val (command, aX, aY, bX, bY) = "(turn on|turn off|toggle) (\\d*),(\\d*) through (\\d*),(\\d*)".toRegex()
            .find(line)!!.destructured

        for (x in aX.toInt()..bX.toInt()) {
            for (y in aY.toInt()..bY.toInt()) {
                when (command) {
                    "turn on" -> lights[x][y] = true
                    "turn off" -> lights[x][y] = false
                    "toggle" -> lights[x][y] = !lights[x][y]
                }
            }
        }
    }

    lights.flatMap { booleans -> booleans.filter { it } }
        .count()
        .let { print(it) }
}
