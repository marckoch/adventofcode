package year2015.day17

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Year2015Day17Test {

    @Test
    fun part1() {
        assertThat(AOC2015D17(SAMPLE, 25).solve1()).isEqualTo(4)
        assertThat(AOC2015D17(INPUT, 150).solve1()).isEqualTo(4372)
    }

    @Test
    fun part2() {
        assertThat(AOC2015D17(SAMPLE, 25).solve2()).isEqualTo(3)
        assertThat(AOC2015D17(INPUT, 150).solve2()).isEqualTo(4)
    }
}
