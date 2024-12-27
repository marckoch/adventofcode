package year2015.day15

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Year2015Day15Test {

    @Test
    fun part1() {
        assertThat(AOC2015D15(SAMPLE).solve1()).isEqualTo(62842880)
        assertThat(AOC2015D15(INPUT).solve1()).isEqualTo(21367368)
    }

    @Test
    fun part2() {
        assertThat(AOC2015D15(SAMPLE).solve2()).isEqualTo(57600000)
        assertThat(AOC2015D15(INPUT).solve2()).isEqualTo(1766400)
    }
}
