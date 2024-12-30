package year2015.day22

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Year2015Day22Test {

    @Test
    fun part1() {
        assertThat(AOC2015D22("").solve1()).isEqualTo(1824)
    }

    @Test
    fun part2() {
        assertThat(AOC2015D22("").solve2()).isEqualTo(1937)
    }
}