package year2024.day01

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Year2024Day01Test {

    @Test
    fun part1() {
        assertThat(AOC2024D01(SAMPLE).solvePart1()).isEqualTo(11)
        assertThat(AOC2024D01(INPUT).solvePart1()).isEqualTo(2164381)
    }

    @Test
    fun part2() {
        assertThat(AOC2024D01(SAMPLE).solvePart2()).isEqualTo(31)
        assertThat(AOC2024D01(INPUT).solvePart2()).isEqualTo(20719933)
    }
}
