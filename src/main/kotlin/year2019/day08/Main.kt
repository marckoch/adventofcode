package year2019.day08

fun main() {
    part1(SAMPLE, width = 3, height = 2).let { println(it) }
    part1(INPUT, width = 25, height = 6).let { println(it) }

    // part2(SAMPLE, width = 3, height = 2) no valid image
    part2(INPUT, width = 25, height = 6)
}

fun part1(input: String, width: Int, height: Int): Int {
    val layers = input.chunked(width * height)

    val minLayer = layers.minBy { it.count { c -> c == '0' } }

    val ones = minLayer.count { it == '1' }
    val twos = minLayer.count { it == '2' }
    return ones * twos
}

fun part2(input: String, width: Int, height: Int) {
    val layers = input.chunked(width * height)

    val image = layers.reduce { acc, layer ->
        acc.zip(layer).map { (a, b) ->
            when (a) {
                '2' -> b // 2 is transparent, so take value from next layer
                else -> a// 0 or 1, so keep value (here we could abort the zip for this 'pixel'?)
            }
        }.joinToString("")
    }

    image
        .replace('0', ' ')
        .replace('1', '*')
        .chunked(width)
        .forEach { println(it) }
}
