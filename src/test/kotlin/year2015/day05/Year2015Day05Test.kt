package year2015.day05

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Year2015Day05Test {

    @Test
    fun part1() {
        assertThat(AOC2015D05(INPUT).solve1()).isEqualTo(258)
    }

    @Test
    fun part2() {
        assertThat(AOC2015D05(INPUT).solve2()).isEqualTo(53)
    }
}
