package year2024.day06

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Year2024Day06Test {

    @Test
    fun part1() {
        assertThat(AOC2024D06(SAMPLE).solvePart1()).isEqualTo(41)
        assertThat(AOC2024D06(INPUT).solvePart1()).isEqualTo(5030)
    }

    @Test
    fun part2() {
        assertThat(AOC2024D06(SAMPLE).solvePart2()).isEqualTo(6)
        assertThat(AOC2024D06(INPUT).solvePart2()).isEqualTo(1928)
    }
}
