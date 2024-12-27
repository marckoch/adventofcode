package year2015.day21

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Year2015Day21Test {

    @Test
    fun part1() {
        assertThat(AOC2015D21(INPUT).solve1()).isEqualTo(78)
    }

    @Test
    fun part2() {
        assertThat(AOC2015D21(INPUT).solve2()).isEqualTo(148)
    }
}
