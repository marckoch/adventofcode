package year2024.day10

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Year2024Day10Test {

    @Test
    fun part1() {
        assertThat(AOC2024D10(SAMPLE1).solvePart1()).isEqualTo(1)
        assertThat(AOC2024D10(SAMPLE2).solvePart1()).isEqualTo(2)
        assertThat(AOC2024D10(SAMPLE3).solvePart1()).isEqualTo(4)
        assertThat(AOC2024D10(SAMPLE4).solvePart1()).isEqualTo(3)
        assertThat(AOC2024D10(SAMPLE5).solvePart1()).isEqualTo(36)
        assertThat(AOC2024D10(INPUT).solvePart1()).isEqualTo(796)
    }

    @Test
    fun part2() {
        assertThat(AOC2024D10(SAMPLE_2_1).solvePart2()).isEqualTo(3)
        assertThat(AOC2024D10(SAMPLE_2_2).solvePart2()).isEqualTo(13)
        assertThat(AOC2024D10(SAMPLE_2_3).solvePart2()).isEqualTo(227)
        assertThat(AOC2024D10(SAMPLE5).solvePart2()).isEqualTo(81)
        assertThat(AOC2024D10(INPUT).solvePart2()).isEqualTo(1942)
    }
}
