package year2024.day03

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Year2024Day03Test {

    @Test
    fun part1() {
        assertThat(AOC2024D03(SAMPLE).solvePart1()).isEqualTo(161)
        assertThat(AOC2024D03(INPUT).solvePart1()).isEqualTo(170778545)
    }

    @Test
    fun part2() {
        assertThat(AOC2024D03(SAMPLE2).solvePart2()).isEqualTo(48)
        assertThat(AOC2024D03(INPUT).solvePart2()).isEqualTo(82868252)
    }
}
