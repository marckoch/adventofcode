package year2024.day04

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Year2024Day04Test {

    @Test
    fun part1() {
        assertThat(AOC2024D04(SAMPLE).solvePart1()).isEqualTo(4)
        assertThat(AOC2024D04(SAMPLE2).solvePart1()).isEqualTo(18)
        assertThat(AOC2024D04(INPUT).solvePart1()).isEqualTo(2545)
    }

    @Test
    fun part2() {
        assertThat(AOC2024D04(SAMPLE2).solvePart2()).isEqualTo(9)
        assertThat(AOC2024D04(INPUT).solvePart2()).isEqualTo(1886)
    }
}
