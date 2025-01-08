package year2017.day02

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Year2017Day02Test {

    @Test
    fun part1() {
        assertThat(AOC2017D02(SAMPLE).solve1()).isEqualTo(18)
        assertThat(AOC2017D02(INPUT).solve1()).isEqualTo(21845)
    }

    @Test
    fun part2() {
        assertThat(AOC2017D02(SAMPLE2).solve2()).isEqualTo(9)
        assertThat(AOC2017D02(INPUT).solve2()).isEqualTo(191)
    }
}
