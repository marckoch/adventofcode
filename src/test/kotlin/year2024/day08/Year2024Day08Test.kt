package year2024.day08

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Year2024Day08Test {

    @Test
    fun part1() {
        assertThat(AOC2024D08(SAMPLE).solvePart1()).isEqualTo(14)
        assertThat(AOC2024D08(INPUT).solvePart1()).isEqualTo(364)
    }

    @Test
    fun part2() {
        assertThat(AOC2024D08(SAMPLE).solvePart2()).isEqualTo(34)
        assertThat(AOC2024D08(INPUT).solvePart2()).isEqualTo(1231)
    }
}
