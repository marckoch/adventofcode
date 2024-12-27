package year2015.day14

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Year2015Day14Test {

    @Test
    fun part1() {
        assertThat(AOC2015D14(SAMPLE, 1000).solve1()).isEqualTo(1120)
        assertThat(AOC2015D14(INPUT, 2503).solve1()).isEqualTo(2640)
    }

    @Test
    fun part2() {
        assertThat(AOC2015D14(SAMPLE, 1000).solve2()).isEqualTo(689)
        assertThat(AOC2015D14(INPUT, 2503).solve2()).isEqualTo(1102)
    }
}
