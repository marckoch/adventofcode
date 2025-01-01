package year2016.day10

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Year2016Day10Test {

    @Test
    fun part1() {
        assertThat(AOC2016D10(INPUT).solve1()).isEqualTo("86")
    }

    @Test
    fun part2() {
        assertThat(AOC2016D10(INPUT).solve2()).isEqualTo(22847)
    }
}
