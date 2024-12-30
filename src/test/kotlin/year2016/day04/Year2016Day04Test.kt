package year2016.day04

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNull
import org.junit.jupiter.api.Test

class Year2016Day04Test {

    @Test
    fun part1() {
        assertThat(AOC2016D04(SAMPLE).solve1()).isEqualTo(1514)
        assertThat(AOC2016D04(INPUT).solve1()).isEqualTo(158835)
    }

    @Test
    fun part2() {
        assertThat(AOC2016D04(SAMPLE).solve2()).isEqualTo("null")
        assertThat(AOC2016D04(INPUT).solve2()).isEqualTo("993")
    }
}
