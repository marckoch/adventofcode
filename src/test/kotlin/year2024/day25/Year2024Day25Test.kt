package year2024.day25

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Year2024Day25Test {

    @Test
    fun part1() {
        assertThat(AOC2024D25(SAMPLE).solve()).isEqualTo(3)
        assertThat(AOC2024D25(INPUT).solve()).isEqualTo(3077)
    }
}
