package year2015.day23

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Year2015Day23Test {

    @Test
    fun part1() {
        assertThat(AOC2015D23(SAMPLE).solve1()).isEqualTo(0)
        assertThat(AOC2015D23(INPUT).solve1()).isEqualTo(255)
    }

    @Test
    fun part2() {
        assertThat(AOC2015D23(SAMPLE).solve2()).isEqualTo(0)
        assertThat(AOC2015D23(INPUT).solve2()).isEqualTo(334)
    }
}
