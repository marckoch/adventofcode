package year2017.day01

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Year2017Day01Test {

    @Test
    fun part1() {
        assertThat(AOC2017D01(INPUT).solve1()).isEqualTo(1182)
    }

    @Test
    fun part2() {
        assertThat(AOC2017D01(INPUT).solve2()).isEqualTo(1152)
    }
}
