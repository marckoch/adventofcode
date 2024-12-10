package year2024.day09

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Year2024Day09Test {

    @Test
    fun part1() {
        assertThat(AOC2024D09(SAMPLE).solvePart1()).isEqualTo(1928)
        assertThat(AOC2024D09(INPUT).solvePart1()).isEqualTo(6337921897505)
    }

    @Test
    fun part2() {
        assertThat(AOC2024D09(SAMPLE).solvePart2()).isEqualTo(2858)
        assertThat(AOC2024D09(INPUT).solvePart2()).isEqualTo(6362722604045)
    }
}
