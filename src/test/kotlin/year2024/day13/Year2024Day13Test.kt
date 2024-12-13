package year2024.day13

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Year2024Day13Test {

    @Test
    fun part1() {
        assertThat(AOC2024D13(SAMPLE).solve()).isEqualTo(480)
        assertThat(AOC2024D13(INPUT).solve()).isEqualTo(39748)
    }

    @Test
    fun part2() {
        assertThat(AOC2024D13(SAMPLE).solve(10000000000000L)).isEqualTo(875318608908)
        assertThat(AOC2024D13(INPUT).solve(10000000000000L)).isEqualTo(74478585072604)
    }
}
