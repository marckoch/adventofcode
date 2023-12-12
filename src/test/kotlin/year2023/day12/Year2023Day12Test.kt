package year2023.day12

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Year2023Day12Test {

    @Test
    fun `part1 sample 1`() {
        assertThat(bruteForce(SAMPLE1_1)).isEqualTo(6)
    }

    @Test
    fun `part1 sample 2`() {
        assertThat(bruteForce(SAMPLE1_2)).isEqualTo(21)
    }

    @Test
    fun `part1 input`() {
        assertThat(bruteForce(INPUT)).isEqualTo(7350)
    }
}
