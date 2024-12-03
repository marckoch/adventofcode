package year2024.day03

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Year2024Day03Test {

    @Test
    fun part1() {
        assertThat(part1(SAMPLE)).isEqualTo(161)
        assertThat(part1(INPUT)).isEqualTo(170778545)

        assertThat(part2(SAMPLE2)).isEqualTo(48)
        assertThat(part2(INPUT)).isEqualTo(82868252)
    }
}
