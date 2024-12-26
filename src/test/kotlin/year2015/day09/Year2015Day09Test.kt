package year2015.day09

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Year2015Day09Test {

    @Test
    fun part1() {
        assertThat(AOC2015D09(SAMPLE).solve1()).isEqualTo(605)
        assertThat(AOC2015D09(INPUT).solve1()).isEqualTo(251)
    }

    @Test
    fun part2() {
        assertThat(AOC2015D09(SAMPLE).solve2()).isEqualTo(982)
        assertThat(AOC2015D09(INPUT).solve2()).isEqualTo(898)
    }
}
