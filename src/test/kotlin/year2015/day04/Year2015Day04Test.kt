package year2015.day04

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Year2015Day04Test {

    @Test
    fun part1() {
        assertThat(AOC2015D04(INPUT).solve1()).isEqualTo(282749)
    }

    @Test
    fun part2() {
        assertThat(AOC2015D04(INPUT).solve2()).isEqualTo(9962624)
    }
}
