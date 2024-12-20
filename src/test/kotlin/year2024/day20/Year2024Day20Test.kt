package year2024.day20

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Year2024Day20Test {

    @Test
    fun part1() {
        assertThat(AOC2024D20(SAMPLE).solve(2, 2, 2)).isEqualTo(44)
        assertThat(AOC2024D20(INPUT).solve(100, 2, 2)).isEqualTo(1358)
    }

    @Test
    fun part2() {
        assertThat(AOC2024D20(SAMPLE).solve(50, 2, 20)).isEqualTo(285)
        assertThat(AOC2024D20(INPUT).solve(100, 2, 20)).isEqualTo(1005856)
    }
}
