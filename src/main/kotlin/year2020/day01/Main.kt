package year2020.day01

fun main() {
    part1(SAMPLE)
    part1(INPUT)

    part2(SAMPLE)
    part2(INPUT)
}

fun part1(input: String) {
    val numbers = input.lines().map { l -> l.toInt() }

    for (n1 in numbers) {
        for (n2 in numbers) {
            if (n1 > n2 && n1 + n2 == 2020) {
                println(n1 * n2)
            }
        }
    }
}

fun part2(input: String) {
    val numbers = input.lines().map { l -> l.toInt() }

    for (n1 in numbers) {
        for (n2 in numbers) {
            for (n3 in numbers) {
                if (n1 > n2 && n2 > n3 && n1 + n2 + n3 == 2020) {
                    println(n1 * n2 * n3)
                }
            }
        }
    }
}