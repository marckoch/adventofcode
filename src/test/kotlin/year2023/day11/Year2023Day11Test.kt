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

    @Test
    fun `part 2 sample 1 factor 2`() {
        assertThat(part2(SAMPLE_1_1, 2)).isEqualTo(374)
    }

    @Test
    fun `part 2 sample 1 factor 10`() {
        assertThat(part2(SAMPLE_1_1, 10)).isEqualTo(1030)
    }

    @Test
    fun `part 2 sample 1 factor 100`() {
        assertThat(part2(SAMPLE_1_1, 100)).isEqualTo(8410)
    }

    @Test
    fun `part 2 factor 2`() {
        assertThat(part2(INPUT, 2)).isEqualTo(9274989)
    }

    @Test
    fun `part 2 factor 1_000_000`() {
        assertThat(part2(INPUT, 1_000_000)).isEqualTo(357134560737)
    }
}
