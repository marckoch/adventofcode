package year2024.day22

fun main() {
    AOC2024D22(SAMPLE).solve().also(::println) // 37327623
    AOC2024D22(INPUT).solve().also(::println) // 20068964552

    AOC2024D22(SAMPLE).solve2().also(::println) // 37327623
}

class AOC2024D22(val input: String) {
    fun solve(): Long {
        val newSecrets = input.lines().map {
            generateSequence(it.toInt()) { s -> next(s) }.elementAt(2000)
        }
        return newSecrets.fold(0L) { acc, value -> acc + value }
    }

    fun solve2(): Long {
        val g = generateSequence(123) { next(it) }.map { it % 10 }
        val s = g.take(10)

        val newSecrets = input.lines().map {
            generateSequence(it.toInt()) { s -> next(s) }.elementAt(10)
        }
        return newSecrets.fold(0L) { acc, value -> acc + value }
    }

    fun next(secret: Int): Int {
        val step1 = (secret * 64).mix(secret).prune()
        val step2 = (step1 / 32).mix(step1).prune()
        val step3 = (step2 * 2048).mix(step2).prune()
        return step3
    }

    private fun Int.mix(secret: Int): Int = this xor secret
    private fun Int.prune(): Int = this.mod(16777216)
}
