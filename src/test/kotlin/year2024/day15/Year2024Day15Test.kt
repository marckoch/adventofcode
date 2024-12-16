package year2024.day15

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Year2024Day15Test {

    @Test
    fun part1() {
        assertThat(AOC2024D15(SAMPLE).solve()).isEqualTo(10092)
        assertThat(AOC2024D15(SAMPLE2).solve()).isEqualTo(2028)
        assertThat(AOC2024D15(INPUT).solve()).isEqualTo(1371036)
    }

    @Test
    fun part2() {
        assertThat(AOC2024D15(SAMPLE).solve2()).isEqualTo(9021)
    }

}
