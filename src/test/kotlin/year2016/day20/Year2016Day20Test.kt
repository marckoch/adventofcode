package year2016.day20

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Year2016Day20Test {

    @Test
    fun part1() {
        assertThat(AOC2016D20(SAMPLE).solve1()).isEqualTo(3U)
        assertThat(AOC2016D20(INPUT).solve1()).isEqualTo(22887907U)
    }

    @Test
    fun part2() {
        assertThat(AOC2016D20(SAMPLE).solve2()).isEqualTo(4294967288U)
        assertThat(AOC2016D20(INPUT).solve2()).isEqualTo(109U)
    }
}
