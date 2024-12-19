package year2024.day16

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Year2024Day16Test {

    @Test
    fun part1() {
        assertThat(AOC2024D16(SAMPLE1).solve()).isEqualTo(7036)
        assertThat(AOC2024D16(SAMPLE2).solve()).isEqualTo(11048)
        assertThat(AOC2024D16(INPUT).solve()).isEqualTo(114476)
    }

    @Test
    fun part2() {
    }
}
