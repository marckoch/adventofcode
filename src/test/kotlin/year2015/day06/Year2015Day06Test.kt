package year2015.day06

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Year2015Day06Test {

    @Test
    fun part1() {
        assertThat(AOC2015D06(INPUT).solve1()).isEqualTo(377891)
    }

    @Test
    fun part2() {
        assertThat(AOC2015D06(INPUT).solve2()).isEqualTo(14110788)
    }
}
