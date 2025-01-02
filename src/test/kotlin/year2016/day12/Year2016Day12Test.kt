package year2016.day12

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Year2016Day12Test {

    @Test
    fun part1() {
        assertThat(AOC2016D12(SAMPLE).solve1()).isEqualTo(42)
        assertThat(AOC2016D12(INPUT).solve1()).isEqualTo(318117)
    }

    @Test
    fun part2() {
        assertThat(AOC2016D12(SAMPLE).solve2()).isEqualTo(42)
        assertThat(AOC2016D12(INPUT).solve2()).isEqualTo(9227771)
    }
}
