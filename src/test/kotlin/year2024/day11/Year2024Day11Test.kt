package year2024.day11

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Year2024Day11Test {

    @Test
    fun part1() {
        assertThat(AOC2024D11(SAMPLE).solve(1)).isEqualTo(3)
        assertThat(AOC2024D11(SAMPLE).solve(2)).isEqualTo(4)
        assertThat(AOC2024D11(SAMPLE).solve(3)).isEqualTo(5)
        assertThat(AOC2024D11(SAMPLE).solve(4)).isEqualTo(9)
        assertThat(AOC2024D11(SAMPLE).solve(5)).isEqualTo(13)
        assertThat(AOC2024D11(SAMPLE).solve(6)).isEqualTo(22)
        assertThat(AOC2024D11(SAMPLE).solve(25)).isEqualTo(55312)

        assertThat(AOC2024D11(INPUT).solve(25)).isEqualTo(231278)
    }

    @Test
    fun part2() {
        assertThat(AOC2024D11(INPUT).solve(75)).isEqualTo(274229228071551)
    }
}
