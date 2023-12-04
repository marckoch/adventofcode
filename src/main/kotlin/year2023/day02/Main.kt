package year2023.day02

val LINE_PATTERN = """Game (\d*): (.*)""".toRegex()

fun main() {
    part1(SAMPLE, 12, 13, 14).let { println(it) } // 8
    part1(INPUT, 12, 13, 14).let { println(it) }  // 2563

    part2(SAMPLE).let { println(it) } // 2286
    part2(INPUT).let { println(it) }  // 70768
}

fun part1(input: String, maxRed: Int, maxGreen: Int, maxBlue: Int): Int {
    return input.lines()
        .map { line -> Game.fromString(line) }
        .filter { game -> game.isPossible(maxRed, maxGreen, maxBlue) }
        .sumOf { game -> game.number }
}

fun part2(input: String): Int {
    return input.lines()
        .map { line -> Game.fromString(line) }
        .sumOf { game -> game.power() }
}

data class Game(val number: Int, val cubeSets: List<CubeSet>) {
    fun isPossible(maxRed: Int, maxGreen: Int, maxBlue: Int): Boolean {
        return cubeSets.maxOfOrNull { it.red }!! <= maxRed &&
                cubeSets.maxOfOrNull { it.green }!! <= maxGreen &&
                cubeSets.maxOfOrNull { it.blue }!! <= maxBlue
    }

    fun power(): Int {
        return cubeSets.maxOf { it.red } *
                cubeSets.maxOf { it.green } *
                cubeSets.maxOf { it.blue }
    }

    companion object Factory {
        fun fromString(input: String): Game {
            val (gameNumber, sets) = LINE_PATTERN.matchEntire(input)!!.destructured
            val cubeSets = sets.split("; ").map { CubeSet.fromString(it) }
            return Game(gameNumber.toInt(), cubeSets)
        }
    }
}

data class CubeSet(val red: Int, val green: Int, val blue: Int) {
    // convert a line ("6 red, 1 blue, 3 green") to a CubeSet (r,g,b) = (6, 3, 1)
    companion object Factory {
        fun fromString(input: String): CubeSet {
            val colors = input.trim().split(", ")
            val red = colors.filter { it.endsWith(" red") }.map { it.split(" ")[0].toInt() }.firstOrNull() ?: 0
            val green = colors.filter { it.endsWith(" green") }.map { it.split(" ")[0].toInt() }.firstOrNull() ?: 0
            val blue = colors.filter { it.endsWith(" blue") }.map { it.split(" ")[0].toInt() }.firstOrNull() ?: 0
            return CubeSet(red, green, blue)
        }
    }
}