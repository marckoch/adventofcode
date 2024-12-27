package year2015.day13

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Year2015Day13Test {

    @Test
    fun part1() {
        assertThat(AOC2015D13(SAMPLE).solve1()).isEqualTo(330)
        assertThat(AOC2015D13(INPUT).solve1()).isEqualTo(709)
    }

    @Test
    fun part2() {
        assertThat(AOC2015D13(SAMPLE).solve2()).isEqualTo(286)
        assertThat(AOC2015D13(INPUT).solve2()).isEqualTo(668)
    }
}
