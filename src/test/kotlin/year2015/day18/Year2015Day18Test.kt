package year2015.day18

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Year2015Day18Test {

    @Test
    fun part1() {
        assertThat(AOC2015D18(SAMPLE, 4).solve1()).isEqualTo(4)
        assertThat(AOC2015D18(INPUT, 100).solve1()).isEqualTo(1061)
    }

    @Test
    fun part2() {
        assertThat(AOC2015D18(SAMPLE, 5).solve2()).isEqualTo(17)
        assertThat(AOC2015D18(INPUT, 100).solve2()).isEqualTo(1006)
    }
}
