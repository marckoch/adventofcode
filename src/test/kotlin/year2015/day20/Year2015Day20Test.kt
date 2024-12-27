package year2015.day20

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Year2015Day20Test {

    @Test
    fun part1() {
        assertThat(AOC2015D20(SAMPLE).solve1()).isEqualTo(6)
        assertThat(AOC2015D20(INPUT).solve1()).isEqualTo(665280)
    }

    @Test
    fun part2() {
        assertThat(AOC2015D20(SAMPLE).solve2()).isEqualTo(6)
        assertThat(AOC2015D20(INPUT).solve2()).isEqualTo(705600)
    }
}
