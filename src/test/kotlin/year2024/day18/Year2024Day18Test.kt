package year2024.day18

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Year2024Day18Test {

    @Test
    fun part1() {
        assertThat(AOC2024D18(SAMPLE).solve(7, 7, 12)).isEqualTo(22)
        assertThat(AOC2024D18(INPUT).solve(71, 71, 1024)).isEqualTo(336)
    }

    @Test
    fun part2() {
        assertThat(AOC2024D18(SAMPLE).solve2(7, 7, 12)).isEqualTo("6,1")
        assertThat(AOC2024D18(INPUT).solve2(71, 71, 1024)).isEqualTo("24,30")
    }

}
