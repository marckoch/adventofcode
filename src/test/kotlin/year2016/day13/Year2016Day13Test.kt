package year2016.day13

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Year2016Day13Test {

    @Test
    fun part1() {
        assertThat(AOC2016D13(SAMPLE).solve1()).isEqualTo(11)
        assertThat(AOC2016D13(INPUT).solve1()).isEqualTo(82)
    }

    @Test
    fun part2() {
        assertThat(AOC2016D13(SAMPLE).solve2()).isEqualTo(151)
        assertThat(AOC2016D13(INPUT).solve2()).isEqualTo(138)
    }
}
