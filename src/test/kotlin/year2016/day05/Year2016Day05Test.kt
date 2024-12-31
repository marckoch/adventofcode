package year2016.day05

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNull
import org.junit.jupiter.api.Test

class Year2016Day05Test {

    @Test
    fun part1() {
        assertThat(AOC2016D05(SAMPLE).solve1()).isEqualTo("18f47a30")
        assertThat(AOC2016D05(INPUT).solve1()).isEqualTo("f97c354d")
    }

    @Test
    fun part2() {
        assertThat(AOC2016D05(SAMPLE).solve2()).isEqualTo("05ace8e3")
        assertThat(AOC2016D05(INPUT).solve2()).isEqualTo("863dde27")
    }
}
