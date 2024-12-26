package year2015.day07

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Year2015Day07Test {

    @Test
    fun part1() {
        assertThat(AOC2015D07(INPUT).solve1()).isEqualTo(46065)
    }

    @Test
    fun part2() {
        assertThat(AOC2015D07(INPUT).solve2()).isEqualTo(14134)
    }
}
