package year2016.day01

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Year2016Day01Test {

    @Test
    fun part1() {
        assertThat(AOC2016D01(INPUT).solve1()).isEqualTo(301)
    }

    @Test
    fun part2() {
        assertThat(AOC2016D01(SAMPLE4).solve2()).isEqualTo(4)
        assertThat(AOC2016D01(INPUT).solve2()).isEqualTo(130)
    }
}
