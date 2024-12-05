package year2024.day02

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Year2024Day02Test {

    @Test
    fun part1() {
        assertThat(AOC2024D02(SAMPLE).solvePart1()).isEqualTo(2)
        assertThat(AOC2024D02(INPUT).solvePart1()).isEqualTo(680)
    }

    @Test
    fun part2() {
        assertThat(AOC2024D02(SAMPLE).solvePart2()).isEqualTo(4)
        assertThat(AOC2024D02(INPUT).solvePart2()).isEqualTo(710)
    }
}
