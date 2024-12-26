package year2015.day08

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Year2015Day08Test {

    @Test
    fun part1() {
        assertThat(AOC2015D08(SAMPLE).solve1()).isEqualTo(12)
        assertThat(AOC2015D08(INPUT).solve1()).isEqualTo(1371)
    }

    @Test
    fun part2() {
        assertThat(AOC2015D08(SAMPLE).solve2()).isEqualTo(19)
        assertThat(AOC2015D08(INPUT).solve2()).isEqualTo(2117)
    }
}
