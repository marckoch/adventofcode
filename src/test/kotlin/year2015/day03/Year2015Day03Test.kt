package year2015.day03

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Year2015Day03Test {

    @Test
    fun part1() {
        assertThat(AOC2015D03(INPUT).solve1()).isEqualTo(2572.toString())
    }

    @Test
    fun part2() {
        assertThat(AOC2015D03(INPUT).solve2()).isEqualTo(2631.toString())
    }
}
