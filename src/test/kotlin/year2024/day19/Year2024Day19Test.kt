package year2024.day19

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Year2024Day19Test {

    @Test
    fun part1() {
        assertThat(AOC2024D19(SAMPLE).solve1()).isEqualTo(6)
        assertThat(AOC2024D19(INPUT).solve1()).isEqualTo(226)
    }

    @Test
    fun part2() {
        assertThat(AOC2024D19(SAMPLE).solve2()).isEqualTo(16)
        assertThat(AOC2024D19(INPUT).solve2()).isEqualTo(601201576113503)
    }
}
