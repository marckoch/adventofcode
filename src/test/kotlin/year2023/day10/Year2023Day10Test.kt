package year2023.day10

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Year2023Day10Test {

    @Test
    fun `part 1 sample 1`() {
        assertThat(part1(SAMPLE1)).isEqualTo(4)
    }

    @Test
    fun `part 1 sample 2`() {
        assertThat(part1(SAMPLE2)).isEqualTo(8)
    }

    @Test
    fun `part 1`() {
        assertThat(part1(INPUT)).isEqualTo(7107)
    }
}
