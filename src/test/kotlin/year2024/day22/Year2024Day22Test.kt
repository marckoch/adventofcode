package year2024.day22

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Year2024Day22Test {

    @Test
    fun part1() {
        assertThat(AOC2024D22(SAMPLE).solve()).isEqualTo(37327623)
        assertThat(AOC2024D22(INPUT).solve()).isEqualTo(20068964552)
    }

    @Test
    fun part2() {
    }
}
