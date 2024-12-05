package year2024.day05

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Year2024Day05Test {

    @Test
    fun part1() {
        assertThat(AOC2024D05(SAMPLE).solvePart1()).isEqualTo(143)
        assertThat(AOC2024D05(INPUT).solvePart1()).isEqualTo(4185)
    }

    @Test
    fun part2() {
        assertThat(AOC2024D05(SAMPLE).solvePart2()).isEqualTo(123)
        assertThat(AOC2024D05(INPUT).solvePart2()).isEqualTo(4480)
    }
}
