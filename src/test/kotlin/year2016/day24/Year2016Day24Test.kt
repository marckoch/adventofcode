package year2016.day24

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Year2016Day24Test {

    @Test
    fun part1() {
        assertThat(AOC2016D24(SAMPLE).solve1()).isEqualTo(14)
        assertThat(AOC2016D24(INPUT).solve1()).isEqualTo(498)
    }

    @Test
    fun part2() {
        assertThat(AOC2016D24(SAMPLE).solve2()).isEqualTo(20)
        assertThat(AOC2016D24(INPUT).solve2()).isEqualTo(812) // too high :-(
    }
}
