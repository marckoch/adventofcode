package year2016.day03

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Year2016Day03Test {

    @Test
    fun part1() {
        assertThat(AOC2016D03(SAMPLE).solve1()).isEqualTo(0)
        assertThat(AOC2016D03(INPUT).solve1()).isEqualTo(1050)
    }

    @Test
    fun part2() {
        assertThat(AOC2016D03(SAMPLE).solve2()).isEqualTo(0)
        assertThat(AOC2016D03(INPUT).solve2()).isEqualTo(1921)
    }
}
