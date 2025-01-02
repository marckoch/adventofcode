package year2016.day14

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Year2016Day14Test {

    @Test
    fun part1() {
        assertThat(AOC2016D14(SAMPLE).solve1()).isEqualTo(22728)
        assertThat(AOC2016D14(INPUT).solve1()).isEqualTo(23890)
    }

    @Test
    fun part2() {
        assertThat(AOC2016D14(SAMPLE).solve2()).isEqualTo(22551)
        assertThat(AOC2016D14(INPUT).solve2()).isEqualTo(22696)
    }
}
