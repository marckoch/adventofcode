package year2024.day07

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Year2024Day07Test {

    @Test
    fun part1() {
        assertThat(AOC2024D07(SAMPLE).solve(false)).isEqualTo(3749)
        assertThat(AOC2024D07(INPUT).solve(false)).isEqualTo(4364915411363)
    }

    @Test
    fun part2() {
        assertThat(AOC2024D07(SAMPLE).solve(true)).isEqualTo(11387)
        assertThat(AOC2024D07(INPUT).solve(true)).isEqualTo(38322057216320)
    }
}
