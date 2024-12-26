package year2015.day10

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Year2015Day10Test {

    @Test
    fun part1() {
        assertThat(AOC2015D10("1321131112").solve1()).isEqualTo(492982)
    }

    @Test
    fun part2() {
        assertThat(AOC2015D10("1321131112").solve2()).isEqualTo(6989950)
    }
}
