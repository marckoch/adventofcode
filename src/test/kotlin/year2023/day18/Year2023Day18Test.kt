package year2023.day18

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Year2023Day18Test {

    @Test
    fun `part1 sample`() {
        assertThat(part1(SAMPLE)).isEqualTo(62)
    }

    @Test
    fun `part1 input`() {
        assertThat(part1(INPUT)).isEqualTo(70026)
    }
}
