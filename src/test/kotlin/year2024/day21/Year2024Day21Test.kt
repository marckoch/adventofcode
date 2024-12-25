package year2024.day21

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Year2024Day21Test {

    @Test
    fun part1() {
        assertThat(AOC2024D21(SAMPLE).solve()).isEqualTo(126384)
        assertThat(AOC2024D21(INPUT).solve()).isEqualTo(1358)
    }
}
