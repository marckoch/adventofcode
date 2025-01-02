package year2016.day15

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Year2016Day15Test {

    @Test
    fun part1() {
        assertThat(AOC2016D15(SAMPLE).solve1()).isEqualTo(5)
        assertThat(AOC2016D15(INPUT).solve1()).isEqualTo(317371)
    }

    @Test
    fun part2() {
        assertThat(AOC2016D15(SAMPLE).solve2()).isEqualTo(85)
        assertThat(AOC2016D15(INPUT).solve2()).isEqualTo(2080951)
    }
}
