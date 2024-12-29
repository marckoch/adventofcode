package year2015.day24

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Year2015Day24Test {

    @Test
    fun part1() {
        assertThat(AOC2015D24(SAMPLE).solve1()).isEqualTo(99)
        assertThat(AOC2015D24(INPUT).solve1()).isEqualTo(11266889531)
    }

    @Test
    fun part2() {
        assertThat(AOC2015D24(SAMPLE).solve2()).isEqualTo(44)
        assertThat(AOC2015D24(INPUT).solve2()).isEqualTo(77387711)
    }
}
