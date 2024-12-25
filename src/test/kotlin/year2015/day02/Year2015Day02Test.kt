package year2015.day02

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Year2015Day02Test {

    @Test
    fun part1() {
        assertThat(AOC2015D02(SAMPLE).solve1()).isEqualTo(101.toString())
        assertThat(AOC2015D02(INPUT).solve1()).isEqualTo(1588178.toString())
    }

    @Test
    fun part2() {
        assertThat(AOC2015D02(SAMPLE).solve2()).isEqualTo(48.toString())
        assertThat(AOC2015D02(INPUT).solve2()).isEqualTo(3783758.toString())
    }
}
