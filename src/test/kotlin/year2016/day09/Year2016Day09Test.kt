package year2016.day09

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Year2016Day09Test {

    @Test
    fun part1() {
        assertThat(AOC2016D09(INPUT).solve1()).isEqualTo(74532)
    }

    @Test
    fun part2() {
        assertThat(AOC2016D09(INPUT).solve2()).isEqualTo(11558231665)
    }
}
