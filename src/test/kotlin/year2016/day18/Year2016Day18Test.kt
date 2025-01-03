package year2016.day18

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Year2016Day18Test {

    @Test
    fun part1() {
        assertThat(AOC2016D18(SAMPLE).solve1()).isEqualTo(38)
        assertThat(AOC2016D18(INPUT).solve1()).isEqualTo(1926)
    }

    @Test
    fun part2() {
        assertThat(AOC2016D18(SAMPLE).solve2()).isEqualTo(1935478)
        assertThat(AOC2016D18(INPUT).solve2()).isEqualTo(19986699)
    }
}
