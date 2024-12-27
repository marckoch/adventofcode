package year2015.day16

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Year2015Day16Test {

    @Test
    fun part1() {
        assertThat(AOC2015D16(INPUT).solve1()).isEqualTo(103)
    }

    @Test
    fun part2() {
        assertThat(AOC2015D16(INPUT).solve2()).isEqualTo(405)
    }
}
