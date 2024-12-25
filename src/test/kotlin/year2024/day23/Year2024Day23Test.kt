package year2024.day23

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Year2024Day23Test {

    @Test
    fun part1() {
        assertThat(AOC2024D23(SAMPLE).solve()).isEqualTo(7)
        assertThat(AOC2024D23(INPUT).solve()).isEqualTo(1149)
    }
}
