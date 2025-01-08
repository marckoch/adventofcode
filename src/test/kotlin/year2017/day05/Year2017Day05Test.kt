package year2017.day05

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Year2017Day05Test {

    @Test
    fun part1() {
        assertThat(AOC2017D05(SAMPLE).solve1()).isEqualTo(5)
        assertThat(AOC2017D05(INPUT).solve1()).isEqualTo(373543)
    }

    @Test
    fun part2() {
        assertThat(AOC2017D05(SAMPLE).solve2()).isEqualTo(10)
        assertThat(AOC2017D05(INPUT).solve2()).isEqualTo(27502966)
    }
}
