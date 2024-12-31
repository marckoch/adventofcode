package year2016.day06

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNull
import org.junit.jupiter.api.Test

class Year2016Day06Test {

    @Test
    fun part1() {
        assertThat(AOC2016D06(SAMPLE).solve1()).isEqualTo("easter")
        assertThat(AOC2016D06(INPUT).solve1()).isEqualTo("mshjnduc")
    }

    @Test
    fun part2() {
        assertThat(AOC2016D06(SAMPLE).solve2()).isEqualTo("advent")
        assertThat(AOC2016D06(INPUT).solve2()).isEqualTo("apfeeebz")
    }
}
