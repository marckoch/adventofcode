package year2024.day22

fun main() {
    AOC2024D22(SAMPLE).solve().also(::println) // 37327623
    AOC2024D22(INPUT).solve().also(::println) // 20068964552
}

class AOC2024D22(val input: String) {
    fun solve(): Long {
        val newSecrets = input.lines().map {
            generateSequence(it.toInt()) { s -> next(s) }.elementAt(2000)
        }
        return newSecrets.fold(0L) { acc, value -> acc + value }
    }

    fun next(secret: Int): Int {
        val step1 = mix(64 * secret, secret).let { prune(it) }
        val step2 = mix(step1 / 32, step1).let { prune(it) }
        val step3 = mix(step2 * 2048, step2).let { prune(it) }
        return step3
    }

    private fun mix(value: Int, secret: Int): Int = value xor secret
    private fun prune(secret: Int): Int =  secret.mod(16777216)
}
