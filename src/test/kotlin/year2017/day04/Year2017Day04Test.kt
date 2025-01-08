package year2017.day04

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Year2017Day04Test {

    @Test
    fun part1() {
        assertThat(AOC2017D04(INPUT).solve1()).isEqualTo(455)
    }

    @Test
    fun part2() {
        assertThat(AOC2017D04(INPUT).solve2()).isEqualTo(186)
    }
}
