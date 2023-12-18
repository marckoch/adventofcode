package year2023.day16

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Year2023Day16Test {

    @Test
    fun `part1 sample`() {
        assertThat(part1(SAMPLE)).isEqualTo(46)
    }

    @Test
    fun `part1 input`() {
        assertThat(part1(INPUT)).isEqualTo(7870)
    }

    @Test
    fun `part2 sample`() {
        assertThat(part2(SAMPLE)).isEqualTo(51)
    }

    @Test
    fun `part2 input`() {
        assertThat(part2(INPUT)).isEqualTo(8143)
    }
}
