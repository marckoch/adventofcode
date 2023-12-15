package year2023.day13

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Year2023Day13Test {

    @Test
    fun `part1 sample`() {
        assertThat(part1(SAMPLE)).isEqualTo(405)
    }

    @Test
    fun `part1 input`() {
        assertThat(part1(INPUT)).isEqualTo(35232)
    }

    @Test
    fun `part2 sample`() {
        assertThat(part2(SAMPLE)).isEqualTo(400)
    }

    @Test
    fun `part2 input`() {
        assertThat(part2(INPUT)).isEqualTo(37982)
    }
}
