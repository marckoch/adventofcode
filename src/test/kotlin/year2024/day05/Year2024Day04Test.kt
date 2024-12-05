package year2024.day05

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Year2024Day05Test {

    @Test
    fun part1() {
        assertThat(part1(SAMPLE)).isEqualTo(143)
        assertThat(part1(INPUT)).isEqualTo(4185)

        assertThat(part2(SAMPLE)).isEqualTo(123)
        assertThat(part2(INPUT)).isEqualTo(4480)
    }
}
