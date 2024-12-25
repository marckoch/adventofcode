package year2015.day01

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Year2015Day01Test {

    @Test
    fun part1() {
        assertThat(AOC2015D01(INPUT).solve1()).isEqualTo(138)
    }

    @Test
    fun part2() {
        assertThat(AOC2015D01(INPUT).solve2()).isEqualTo(1771)
    }
}
