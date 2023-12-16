package year2023.day14

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Year2023Day14Test {

    @Test
    fun `part1 sample`() {
        assertThat(part1(SAMPLE)).isEqualTo(136)
    }

    @Test
    fun `part1 input`() {
        assertThat(part1(INPUT)).isEqualTo(106990)
    }
}
