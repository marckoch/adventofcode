package year2023.day11

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Year2023Day11Test {

    @Test
    fun `part 1 sample 1`() {
        assertThat(part1(SAMPLE_1_1)).isEqualTo(374)
    }

    @Test
    fun `part 1`() {
        assertThat(part1(INPUT)).isEqualTo(9274989)
    }
}
