package year2023.day09

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Year2023Day09Test {

    @Test
    fun `part 1 samples`() {
        assertThat(part1(SAMPLE)).isEqualTo(114)
    }

    @Test
    fun `part 1`() {
        assertThat(part1(INPUT)).isEqualTo(2105961943)
    }

    @Test
    fun `part 2 samples`() {
        assertThat(part2(SAMPLE)).isEqualTo(2)
    }

    @Test
    fun `part 2`() {
        assertThat(part2(INPUT)).isEqualTo(1019)
    }
}
